package com.corebank.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.corebank.entity.User;
import com.corebank.util.HibernateUtil;

public class UserDao {
    
private Transaction transaction;
    public void saveUser(User user){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
          transaction =  session.beginTransaction();
          session.save(user);
          transaction.commit();
          session.close();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public User getUserByEmailAndPassword(String email, String password){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
          Query<User> query =   session.createQuery("from User where email = :userEmail AND password = :userPassword", User.class);
          query.setParameter("userEmail", email);
          query.setParameter("userPassword", password);
          return query.uniqueResult();
        }
    }

    public User getUserByUserId(int id){
      try(Session session = HibernateUtil.getSessionFactory().openSession()){
        return  session.get(User.class, id);
        //  query.uniqueResult();
      }
    }


}
