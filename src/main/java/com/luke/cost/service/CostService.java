package com.luke.cost.service;

import com.luke.cost.bean.Cost;
import com.luke.cost.bean.CostPage;

import java.util.List;

/***
 * com.luke.cost.service
 * dllo
 * 18/7/16
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
public interface CostService {

    CostPage findAll();

    CostPage findCostByLimit(CostPage page);

    Cost finddetail(String id);

    //Cost findCostByName(Cost cost);

    boolean insertCost(Cost cost);

    boolean updateStatus(String id);

    boolean deleteCostById(String id);

    boolean modifyCost(Cost cost);

    //排序
    List<Cost> findAllAscSortByBD();
    List<Cost> findAllDecSortByBD();
    List<Cost> findAllAscSortByBC();
    List<Cost> findAllDecSortByBC();
}
