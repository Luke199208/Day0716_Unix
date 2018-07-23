package com.luke.cost.mapper;

import com.luke.cost.bean.Cost;
import com.luke.cost.bean.CostPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * com.luke.cost.mapper
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
@Repository
public interface CostMapper {

    List<Cost> findAll(CostPage costPage);

    int getCount();

    Cost finddetail(String cost_id);

    Cost findCostByName(Cost cost);

    int insertCost(Cost cost);

    int updateStatus(@Param("id") String id, @Param("date") String date);

    int deleteCostById(String id);

    int modifyCost(Cost cost);
    //排序
    List<Cost> findAllAscSortByBD();
    List<Cost> findAllDecSortByBD();
    List<Cost> findAllAscSortByBC();

    List<Cost> findAllDecSortByBC();
}
