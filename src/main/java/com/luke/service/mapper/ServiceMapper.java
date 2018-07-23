package com.luke.service.mapper;

import com.luke.account.bean.Account;
import com.luke.cost.bean.Cost;
import com.luke.service.bean.Host;
import com.luke.service.bean.ServiceBean;
import com.luke.service.bean.ServicePage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * com.luke.service.mapper
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
@Repository
public interface ServiceMapper {

    int getCount();

    List<ServiceBean> findServiceByLimit(ServicePage servicePage);

    int getConditionQueryCount(ServicePage page);

    List<ServiceBean> ConditionQueryByLimit(ServicePage page);

    Account findAccount(String idcard_no);

    int insertService(ServiceBean serviceBean);

    List<Cost> findAddCost();

    List<Host> findAddHost();

    Cost findSelectedCost(String name);

    ServiceBean findDetail(String id);

    ServiceBean findServiceById(String id);

    int updateStatus(@Param("id") String id,@Param("service_status") String service_status);

    int del(String id);
}
