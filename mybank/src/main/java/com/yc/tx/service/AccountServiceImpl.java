package com.yc.tx.service;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.dao.AccountsDao;
import com.yc.tx.dao.OpRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * @program: testspring
 * @description: 业务实现类
 * @author: 作者
 * @create: 2021-04-22 19:37
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,
        isolation = Isolation.DEFAULT,
        readOnly = false,
        timeout = 100,
        rollbackForClassName = {"RuntimeException"})
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountsDao accountsDao;
    @Autowired
    private OpRecordDao opRecordDao;

    @Override   //开户
    public Integer openAccount(Accounts account, double money) {
        account.setBalance(money);
        int i = accountsDao.saveAccount(account);
        return i;  //TODO:没有将日志加进去
    }

    @Override   //存
    public Accounts deposite(Accounts account, double money, String optype, String transferid) {
        Accounts a = this.showBalance(account);

        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(a.getAccountId());
        opRecord.setOpmoney(money);
        opRecord.setOptype(optype);
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));
        if(transferid == null){
            opRecord.setTransferid(" ");
        }else{
            opRecord.setTransferid(transferid);
        }
        opRecordDao.saveOpRecord(opRecord);

        a.setBalance(a.getBalance() + money);
        accountsDao.updateAccount(a);
        return a;
    }

    @Override   //取
    @Transactional
    public Accounts withdraw(Accounts account, double money, String optype, String transferid) {
        Accounts a = this.showBalance(account);

        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(a.getAccountId());
        opRecord.setOpmoney(money);
        opRecord.setOptype(optype);
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));
        if(transferid == null){
            opRecord.setTransferid(" ");
        }else{
            opRecord.setTransferid(transferid);
        }
        opRecordDao.saveOpRecord(opRecord);

        a.setBalance(a.getBalance() - money);
        accountsDao.updateAccount(a);
        return a;
    }

    @Override
    public Accounts transfer(Accounts inAccount, Accounts outAccount, double money) {
        String uid = UUID.randomUUID().toString();   //转账流水
        this.deposite(inAccount, money, OpTypes.deposite.getName(), uid); //存钱，流水日志改为deposite
        Accounts newAccounts = this.withdraw(outAccount, money, OpTypes.withdraw.getName(), uid); //取钱，日志流水为withdraw
        return newAccounts;
    }

    @Override
    @Transactional(readOnly = true)
    public Accounts showBalance(Accounts account) {
        return accountsDao.findAccount(account.getAccountId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpRecord> findById(Accounts account) {
        return opRecordDao.findByAccountid(account.getAccountId());
    }
}
