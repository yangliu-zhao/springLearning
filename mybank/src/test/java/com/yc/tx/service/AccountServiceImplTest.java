package com.yc.tx.service;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpTypes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)  //spring整合junit的注解
@SpringBootTest    //Springboot的注解， 引用了springboot的测试功能    springboot有自动装载，自动配置机制
//@ContextConfiguration(classes = AppConfig.class)
public class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Test   //开户
    //@Transactional    //1>放在业务里面是事务注解  2>在junit中是回滚:在测试完后，数据不会插入数据库，用于恢复现场
    public void testOpenAccount() {
//        Integer accountid = accountService.openAccount(new Accounts(),1);
//        System.out.println(accountid);
//        Assert.assertNotNull(accountid);
    }

    @Test   //存钱
    public void testDeposite() {
//        Accounts a = new Accounts();
//        a.setAccountId(22);
//        Accounts aa = accountService.deposite(a,100, OpTypes.deposite.getName(),null);
//        System.out.println(aa);
    }

    @Test  //取钱
    public void testWithdraw() {
//        Accounts a = new Accounts();
//        a.setAccountId(22);
//        Accounts aa = accountService.withdraw(a,999, OpTypes.withdraw.getName(),null);
//        System.out.println(aa);
    }

    @Test     //转账
    public void testTransfer() {
//        Accounts out = new Accounts();  //出
//        out.setAccountId(22);
//
//        Accounts inA = new Accounts();  //入
//        inA.setAccountId(20);
//
//        this.accountService.transfer(inA,out,1);
    }

    @Test   //查看余额
    public void testShowBalance() {
        Accounts a = new Accounts();
        a.setAccountId(6);

        this.accountService.showBalance(a);
        System.out.println(a);
    }

    @Test   //查看日志
    public void testFindById() {
    }
}