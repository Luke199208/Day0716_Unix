package com.luke.cost.controller;

import com.luke.cost.bean.Cost;
import com.luke.cost.bean.CostPage;
import com.luke.cost.service.CostService;
import com.luke.cost.service.impl.CostServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/***
 * com.luke.cost.controller
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
@Controller
@RequestMapping("/cost")
public class CostController {

    @Resource(name = "CostA")
    private CostService service;

    //初始分页
    @RequestMapping("/findAll.do")
    public String findAll(Model model){
        CostPage page = service.findAll();
        model.addAttribute("costPage",page);
        return "cost/cost_list";
    }

    //点击页码查询
    @RequestMapping("/findCostByLimit.do")
    public String findCostByLimit(@RequestParam String currentPage, Model model){

        CostPage page = new CostPage();
        page.setCurrentPage(Integer.parseInt(currentPage));
        CostPage costPage = service.findCostByLimit(page);

        model.addAttribute("costPage",costPage);

        return "cost/cost_list";
    }


    @RequestMapping("/finddetail.do")
    public String finddetail(@RequestParam String id , Model model){
        Cost cost = service.finddetail(id);
        model.addAttribute("cost",cost);
        return "cost/cost_detail";
    }

    @RequestMapping("/addCost.do")
    public String addCost(){
        return "cost/cost_add";
    }

    @RequestMapping("/addCostT.do")
    public String addCostT( Cost cost,Model model){
        System.out.println(cost);
        boolean flag = service.insertCost(cost);
        if (flag){
            CostPage costPage = service.findAll();
            model.addAttribute("costPage",costPage);
            return "cost/cost_list";
        }else {
            model.addAttribute("msg","保存失败，资费名称重复！");
            model.addAttribute("flag",true);
        }
        return "cost/cost_add";
    }

    @RequestMapping("/updateStatus.do")
    public String updateStatus(@RequestParam String id , Model model){
        System.out.println(id);
        boolean flag = service.updateStatus(id);
        CostPage costPage = service.findAll();
        model.addAttribute("costPage",costPage);
        model.addAttribute("msg","成功启用");
        return "cost/cost_list";
    }

    @RequestMapping("/deleteCostById.do")
    public String deleteCostById(@RequestParam String id , Model model){
        System.out.println(id);
        boolean flag = service.deleteCostById(id);
        CostPage costPage = service.findAll();
        model.addAttribute("costPage",costPage);
        model.addAttribute("msg","删除成功");
        return "cost/cost_list";
    }

    @RequestMapping("/modifyCostT.do")
    public String modifyCostT(@RequestParam String id, Model model){
        Cost cost = service.finddetail(id);
        model.addAttribute("cost",cost);
        return "cost/cost_modi";
    }

    @RequestMapping("/modifyCost.do")
    public String modifyCost(Cost cost, Model model){
        System.out.println(cost);
        boolean flag = service.modifyCost(cost);
        if (flag){
            return "redirect:findAll.do";
        }else {
            model.addAttribute("msg","保存失败，资费名称重复！");
            model.addAttribute("flag",true);
        }
        return "cost/cost_modi";
    }

    @RequestMapping("/findAllAscSortByBD.do")
    public String findAllAscSortByBD(Model model){
        List<Cost> costs = service.findAllAscSortByBD();
        model.addAttribute("costs",costs);
        return "cost/cost_list";
    }

    @RequestMapping("/findAllDecSortByBD.do")
    public String findAllDecSortByBD(Model model){
        List<Cost> costs = service.findAllDecSortByBD();
        model.addAttribute("costs",costs);
        return "cost/cost_list";
    }

    @RequestMapping("/findAllAscSortByBC.do")
    public String findAllAscSortByBC(Model model){
        List<Cost> costs = service.findAllAscSortByBC();
        model.addAttribute("costs",costs);
        return "cost/cost_list";
    }

    @RequestMapping("/findAllDecSortByBC.do")
    public String findAllDecSortByBC(Model model){
        List<Cost> costs = service.findAllDecSortByBC();
        model.addAttribute("costs",costs);
        return "cost/cost_list";
    }

}
