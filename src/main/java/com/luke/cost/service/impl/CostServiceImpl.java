package com.luke.cost.service.impl;

import com.luke.cost.bean.Cost;
import com.luke.cost.bean.CostPage;
import com.luke.cost.mapper.CostMapper;
import com.luke.cost.service.CostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/***
 * com.luke.cost.service.impl
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
@Service//("CostServiceImpl")
public class CostServiceImpl implements CostService {

    @Resource
    private CostMapper mapper ;

    @Override
    public CostPage findAll() {
        CostPage costPage = new CostPage();
        costPage.setTotal(mapper.getCount());
        List<Cost> costs = mapper.findAll(costPage);
        costPage.setList(costs);
        return costPage;
    }

    @Override
    public CostPage findCostByLimit(CostPage page) {
        page.setTotal(mapper.getCount());
        List<Cost> costs = mapper.findAll(page);
        page.setList(costs);
        return page;
    }

    @Override
    public Cost finddetail(String id) {
        return mapper.finddetail(id);
    }

    @Override
    public boolean insertCost(Cost cost) {

        if (!cost.getName().equals("")) {
            //查询是否名称重复
            Cost cost1 = mapper.findCostByName(cost);

            //获得创建时间
            Date date = new Date();
            String dataFormat = String.valueOf(DateFormat.getDateInstance().format(date));
            //System.out.println(dataFormat);

            //根据资费类型设定不通值
            if (cost.getCost_type().equals("1")) {
                cost.setCreatime(dataFormat);
                cost.setStatus("2");
                cost.setBase_duration(null);
                cost.setUnit_cost(null);
            }
            if (cost.getCost_type().equals("2")) {
                cost.setCreatime(dataFormat);
                cost.setStatus("2");
            }
            if (cost.getCost_type().equals("3")) {
                cost.setCreatime(dataFormat);
                cost.setStatus("2");
                cost.setBase_duration(null);
                cost.setBase_cost(null);
            }
            //System.out.println(cost);
            if (cost1 == null) {
                int flag = mapper.insertCost(cost);
                return flag == 1;
            }
        }
        return false;
    }

    @Override
    public boolean updateStatus(String id) {
        //获得开启时间
        Date date = new Date();
        String dataFormat = String.valueOf(DateFormat.getDateInstance().format(date));
        int flag = mapper.updateStatus(id,dataFormat);
        return flag == 1;
    }

    @Override
    public boolean deleteCostById(String id) {
        int flag = mapper.deleteCostById(id);
        return flag==1;
    }

    @Override
    public boolean modifyCost(Cost cost) {
            int flag = mapper.modifyCost(cost);
            return flag == 1;

    }

    @Override
    public List<Cost> findAllAscSortByBD() {
        return mapper.findAllAscSortByBD();
    }

    @Override
    public List<Cost> findAllDecSortByBD() {
        return mapper.findAllDecSortByBD();
    }

    @Override
    public List<Cost> findAllAscSortByBC() {
        return mapper.findAllAscSortByBC();
    }

    @Override
    public List<Cost> findAllDecSortByBC() {
        return mapper.findAllDecSortByBC();
    }


}
