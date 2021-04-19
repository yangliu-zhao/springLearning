package com.yc.tx.bean;

import lombok.Data;

//重复get、set方法，太麻烦，引入一个工具lombok
@Data
public class Accounts {
    private Integer accountId;
    private Double balance;

}
