package com.corebank.services;

import com.corebank.dao.TransactionsDao;
import com.corebank.entity.Transactions;

public class TransactionService {

    TransactionsDao transactionsDao;

    public TransactionService(){
        transactionsDao = new TransactionsDao();
    }
    
    public void saveTransaction(Transactions transactions){
        transactionsDao.saveTransaction(transactions);
    }
}
