package com.corebank.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.corebank.entity.Transactions;
import com.corebank.util.HibernateUtil;

import java.util.List;

public class TransactionsDao {

    public void saveTransaction(Transactions transactionEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(transactionEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Transactions getTransactionById(Long transactionId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Transactions.class, transactionId);
        }
    }

    public List<Transactions> getAllTransactions() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Transactions> query = session.createQuery("FROM Entity", Transactions.class);
            return query.getResultList();
        }
    }

    public List<Transactions> getTransactionsByAccountId(Long accountId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Transactions> query = session.createQuery("FROM Transactions WHERE fromAccountId = :accountId OR toAccountId = :accountId", Transactions.class);
            query.setParameter("accountId", accountId);
            return query.getResultList();
        }
    }

    public void updateTransaction(Transactions transactionEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(transactionEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteTransaction(Long transactionId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Transactions transactionEntity = session.get(Transactions.class, transactionId);
            if (transactionEntity != null) {
                session.delete(transactionEntity);
                System.out.println("Transaction deleted successfully!");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
