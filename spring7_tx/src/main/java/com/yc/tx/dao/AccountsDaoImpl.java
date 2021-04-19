package com.yc.tx.dao;

import com.yc.tx.bean.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-18 19:47
 */
//托管该类
@Repository
public class AccountsDaoImpl implements AccountsDao{

    //jdbc操作模版  newJdbcTemplate时注入数据源dataSource   导入jar包
    private JdbcTemplate jdbcTemplate;

    //注入 dataSource
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int saveAccount(Accounts account) {
        String sql = "insert into accounts(balance) values ( ? )";
        //利用KeyHolder来获取新增的这条数据的id
        KeyHolder keyHolder = new GeneratedKeyHolder();    //KeyHolder :生成键的保存器

        jdbcTemplate.update(connection -> {     //lanbda表达式    就是一种匿名内部类的写法
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"accountid"});
            ps.setDouble(1, account.getBalance());
            return ps;
        }, keyHolder);

        //上面代码的解释  传统写法   方案一：用匿名内部类书写
//        jdbcTemplate.update(new PreparedStatementCreator() {   //匿名内部类接口
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement pstmt = connection.prepareStatement(sql,new String[]{"accountid"});  //第二个参数是返回这个字段生成的值(id值)
//                pstmt.setDouble(1,account.getBalance());
//                return pstmt;
//            }
//        }, keyHolder);

        // keyHolder.getKey() now contains the generated key
        return ((BigInteger) keyHolder.getKey()).intValue();

    }

    @Override    //更新
    public Accounts updateAccount(Accounts account) {
        String sql = "update accounts set balance=? where accountid=?";
        this.jdbcTemplate.update(sql ,account.getBalance(),account.getAccountId());
        return account;
    }

    @Override
    public Accounts findAccount(int accountid) {
        String sql = "select * from accounts where accountid=?";
        return this.jdbcTemplate.queryForObject(sql,
                (resultSet, rowNum) -> {
                    Accounts a = new Accounts();
                    a.setAccountId(resultSet.getInt("accountid"));
                    a.setBalance(resultSet.getDouble("balance"));
                    return a;
                }, accountid);
    }

    @Override
    public List<Accounts> findAll() {
        String sql = "select * from accounts";
        List<Accounts> list = this.jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            System.out.println("当前取的是第" + rowNum + "的数据");
            Accounts a = new Accounts();
            a.setAccountId(resultSet.getInt("accountid"));
            a.setBalance(resultSet.getDouble("balance"));
            return a;
        });

//        List<Accounts> list = this.jdbcTemplate.query(sql, new RowMapper<Accounts>() {
//            @Override     //jdbcTemplate底层自动完成结果集的循环    lanbda表达式避免了循环代码
//            public Accounts mapRow(ResultSet resultSet, int rowNum) throws SQLException { //每次执行完都会回调mapRow
//                System.out.println("当前取的是第" + rowNum + "的数据");
//                Accounts a = new Accounts();
//                a.setAccountId(resultSet.getInt("accountid"));
//                a.setBalance(resultSet.getDouble("balance"));
//                return a;
//            }
//        });

        return list;
    }

    @Override
    public void delete(int accountid) {
        String sql = "delete from accounts where accountid=?";
        this.jdbcTemplate.update(sql, accountid);
    }
}
