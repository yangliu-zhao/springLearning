package com.yc.dao;

import com.yc.tx.AppConfig;
import com.yc.tx.bean.Accounts;
import com.yc.tx.dao.AccountsDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TestDao {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AccountsDao accountsDao;

    @Test   //测试数据源
    public void testDataSource() throws SQLException {
        Assert.assertNotNull(dataSource);
        System.out.println(dataSource.getConnection());
    }

    @Test   //测试daoImpl
    public void testAccountsDaoImpl() throws SQLException {
        Assert.assertNotNull(accountsDao);

    }

    @Test   //开户
    public void testOpenAccounts() {
        Accounts accounts = new Accounts();
        accounts.setBalance(10.0);
        int accountid = accountsDao.saveAccount(accounts);
        System.out.println("开户成功，新开户头id为："+accountid);

    }

    @Test   //查户头
    public void testFindAll() {
        List<Accounts> list = this.accountsDao.findAll();
        System.out.println(list);
    }

    @Test   //根据id查户头
    public void testFindById() {
        Accounts a = this.accountsDao.findAccount(4);
        System.out.println(a);
    }

}
