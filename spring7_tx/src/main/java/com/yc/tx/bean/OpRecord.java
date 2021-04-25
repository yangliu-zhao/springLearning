package com.yc.tx.bean;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-20 00:36
 */
@Data
public class OpRecord {
    private Integer id;
    private Integer accountid;
    private Double opmoney;
    private Timestamp optime;
    private String optype;
    private String transferid;
}
