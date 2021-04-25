package com.yc.tx.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: testspring
 * @description:   页面往controller层请求的参数包装传参
 * @author: 作者
 * @create: 2021-04-24 20:38
 */
@Data
public class AccountVO implements Serializable {

    private Integer accountId;
    private Double money;
    private Integer inAccountId;

}
