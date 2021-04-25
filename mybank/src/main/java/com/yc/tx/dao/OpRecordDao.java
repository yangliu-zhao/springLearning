package com.yc.tx.dao;

import com.yc.tx.bean.OpRecord;

import java.util.List;

public interface OpRecordDao {

    //添加，日志
    public void saveOpRecord(OpRecord opRecord);

    //查询所有的ORecord对象
    public List<OpRecord> findAll();

    //自查对应的用户的OpRecord对象
    public List<OpRecord> findByAccountid(int accountid);

}
