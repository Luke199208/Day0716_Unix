package com.luke.service.service;

import com.luke.account.bean.Account;
import com.luke.cost.bean.Cost;
import com.luke.service.bean.Host;
import com.luke.service.bean.ServiceBean;
import com.luke.service.bean.ServicePage;

import java.util.List;

/***
 * com.luke.service.service
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
public interface Service_sercvice {

    ServicePage findServiceByLimit();

    ServicePage ConditionQueryByLimit(ServicePage page);

    Account findAccount(String idcard_no);

    boolean insertService(ServiceBean serviceBean);

    List<Cost> findAddCost();

    List<Host> findAddHost();

    ServiceBean findDetail(String id);

    boolean updateStatus(String id);

    boolean del(String id);
}
