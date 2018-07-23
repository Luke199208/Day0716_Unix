package com.luke.service.service.impl;

import com.luke.account.bean.Account;
import com.luke.cost.bean.Cost;
import com.luke.service.bean.Host;
import com.luke.service.bean.ServiceBean;
import com.luke.service.bean.ServicePage;
import com.luke.service.mapper.ServiceMapper;
import com.luke.service.service.Service_sercvice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/***
 * com.luke.service.service.impl
 * dllo
 * 18/7/21
 *             ,%%%%%%%%,
 *           ,%%/\%%%%/\%%
 *          ,%%%\c "" J/%%%
 * %.       %%%%/ 0  0 \%%%
 * `%%.     %%%%    _  |%%%
 *  `%%     `%%%%(__Y__)%%'
 *  //       ;%%%%`\-/%%%'
 * ((       /  `%%%%%%%'
 *  \\    .'     '%%%'|    攻
 *   \\  /       \  | |    城
 *    \\/         ) | |    湿
 *     \         /_ | |__
 *     (___________))))))) 
 *
 *       我湿一吼  BUG无有                        
 */
@Service
public class Service_serviceImpl implements Service_sercvice {

    @Resource
    private ServiceMapper mapper;

    @Override
    public ServicePage findServiceByLimit() {

        ServicePage servicePage = new ServicePage();
        servicePage.setTotal(mapper.getCount());
        List<ServiceBean> serviceBeanList = mapper.findServiceByLimit(servicePage);
        servicePage.setList(serviceBeanList);
        return servicePage;
    }

    @Override
    public ServicePage ConditionQueryByLimit(ServicePage page) {
        if (page.getService_status().equals("-1")){
            page.setService_status("");
        }
        page.setTotal(mapper.getConditionQueryCount(page));
        List<ServiceBean> serviceBeanList = mapper.ConditionQueryByLimit(page);
        page.setList(serviceBeanList);
        return page;
    }

    @Override
    public Account findAccount(String idcard_no) {
        Account account = mapper.findAccount(idcard_no);
        return account;
    }

    @Override
    public boolean insertService(ServiceBean serviceBean) {
        //获得创建时间
        Date date = new Date();
        String dateFormat = String.valueOf(DateFormat.getDateInstance().format(date));
        serviceBean.setCreate_date(dateFormat);

        //获取cost_id
        String cost_id = mapper.findSelectedCost(serviceBean.getName()).getCost_id();
        System.out.println(cost_id);
        serviceBean.setCost_id(cost_id);

        int flag = mapper.insertService(serviceBean);
        return flag == 1;
    }

    @Override
    public List<Cost> findAddCost() {
        return mapper.findAddCost();
    }

    @Override
    public List<Host> findAddHost() {
        return mapper.findAddHost();
    }

    @Override
    public ServiceBean findDetail(String id) {
        return mapper.findDetail(id);
    }

    @Override
    public boolean updateStatus(String id) {
        ServiceBean serviceBean = mapper.findServiceById(id);
        if (serviceBean.getService_status().equals("0")){
            int flag = mapper.updateStatus(id,"1");
            return flag == 1 ;
        }
        if (serviceBean.getService_status().equals("1")){
            int flag = mapper.updateStatus(id,"0");
            return flag == 1 ;
        }
        return false;
    }

    @Override
    public boolean del(String id) {
        int flag = mapper.del(id);
        return flag == 1;
    }
}
