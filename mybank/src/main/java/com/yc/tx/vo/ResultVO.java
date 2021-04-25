package com.yc.tx.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: testspring
 * @description:  响应的参数，用VO对象格式来包装响应的值
 * @author: 作者
 * @create: 2021-04-24 20:40
 */
@Data
public class ResultVO<T> implements Serializable {

    private Integer code; //响应码
    private T data; //数据
    private String msg; //错误信息
}
