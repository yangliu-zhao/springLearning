package com.yc.dao;

import com.yc.tx.AppConfig;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.dao.OpRecordDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-24 15:07
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TestOpRecordDao {

    @Autowired
    private OpRecordDao opRecordDao;

//    @Test
//    public void testSave(){
//        OpRecord opRecord = new OpRecord();
//        opRecord.setAccountid(1);
//        opRecord.setOpmoney(1.0);
//        opRecord.setOptype(OpTypes.deposite.getName()); //用枚举做这个值（约束值）不易出错
//        opRecord.setOptime(new Timestamp(new Date().getTime())); //这里是一个Timestamp， new Date().getTime()取得一个long
//        opRecord.setTransferid(" "); //简单的存钱操作，没有transferid
//        opRecordDao.saveOpRecord(opRecord);
//    }

    @Test
    public void testFindAll() {
        List<OpRecord> list = opRecordDao.findAll();
        Assert.assertNotEquals(0,list.size());
        System.out.println(list);
    }

    @Test
    public void testFindByAccountId() {
        List<OpRecord> list = opRecordDao.findByAccountid(1);
        Assert.assertNotEquals(0,list.size());
        System.out.println(list);
    }
}

