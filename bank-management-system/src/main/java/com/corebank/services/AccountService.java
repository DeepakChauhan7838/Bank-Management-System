package com.corebank.services;

import com.corebank.dao.AccountDao;
import com.corebank.entity.Account;

public class AccountService {
    AccountDao accountDao;

    public AccountService() {
        accountDao = new AccountDao();
    }

    public Account getAccountByAccountNo(String account_no){
        return accountDao.getAccountByAccountNumber(account_no);
    }

    public void updateAccount(Account account){
        accountDao.updateAccount(account);
    }

}
