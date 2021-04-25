package com.yc.tx.controllers;

import com.yc.tx.bean.Accounts;
import com.yc.tx.service.AccountService;
import com.yc.tx.vo.AccountVO;
import com.yc.tx.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: testspring
 * @description:  控制层
 * @author: 作者
 * @create: 2021-04-24 20:52
 */
@Controller  //表明是控制层
@Slf4j      //自动注入日志 lonbok
public class AccountsController {

    @Autowired
    private AccountService accountService;

    //最底层最核心的映射   请求的路径             支持的请求的方法 GET、POST
    @RequestMapping(value = "/openAccounts",method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody  //返回的值就会变为 json
    ResultVO openAccounts(AccountVO accountVO){
        log.debug("用户请求开户，存入" + accountVO.getMoney());
        ResultVO rv = new ResultVO();
        try {
            Accounts a = new Accounts();
            double money = 1;
            if( accountVO.getMoney() != null && accountVO.getMoney() > 0){
                money= accountVO.getMoney();
            }
            Integer id = accountService.openAccount( a,money );
            a.setAccountId( id );
            a.setBalance( money );

            rv.setCode(1);
            rv.setData(a);
        }catch (Exception ex){
            ex.printStackTrace();
            rv.setCode(0);
            rv.setMsg(ex.getMessage());
        }

        return rv;
    }

}
