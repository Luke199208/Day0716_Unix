package com.luke.service.controller;

import com.luke.account.bean.Account;
import com.luke.cost.bean.Cost;
import com.luke.service.bean.Host;
import com.luke.service.bean.ServiceBean;
import com.luke.service.bean.ServicePage;
import com.luke.service.service.impl.Service_serviceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/***
 * com.luke.service.controller
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
@Controller
@RequestMapping("/service")
public class ServiceController {

    @Resource
    private Service_serviceImpl service;

    //初始分页
    @RequestMapping("/findAll.do")
    public String findAll(Model model){

        ServicePage servicePage = service.findServiceByLimit();

        model.addAttribute("servicePage",servicePage);

        return "service/service_list";
    }

    //条件分页查询
    @RequestMapping("/ConditionQueryByLimit.do")
    public String ConditionQueryByLimit(ServicePage page, Model model){

        ServicePage servicePage = service.ConditionQueryByLimit(page);

        if (servicePage!= null){
            model.addAttribute("servicePage",servicePage);
        }else {
            model.addAttribute("msg","未查询到符合条件的数据");
        }

        return "service/service_list";
    }

    //add
    @RequestMapping("/addT.do")
     public String addT(Model model){
        List<Cost> costs = service.findAddCost();
        List<Host> hosts = service.findAddHost();
        model.addAttribute("costs",costs);
        model.addAttribute("hosts",hosts);
         return "service/service_add";
     }

     //findAccount
    @RequestMapping(value = "/findAccount.do",method = RequestMethod.POST)
    @ResponseBody
    public Account findAccount(@RequestParam String idcard_no){
        Account account = service.findAccount(idcard_no);

        if (account != null){
            account.setFlag("1");
            return account;
        }else {
            Account account1 = new Account();
            account1.setFlag("2");
            return account1;
        }
    }

    @RequestMapping("/add.do")
    public String add(ServiceBean serviceBean,Model model){
        if (serviceBean.getLogin_passwd().equals(serviceBean.getRelogin_passwd())){
            service.insertService(serviceBean);
            return "redirect:findAll.do";
        }else {
            model.addAttribute("msg","保存失败!两次密码不同");
            model.addAttribute("flag",true);
        }
        return "service/service_add";
    }

    //showDetail
    @RequestMapping("/showDetail.do")
    public String showDetail(@RequestParam String id,Model model){
        ServiceBean serviceBean = service.findDetail(id);
        model.addAttribute("serviceBean",serviceBean);
        return "service/service_detail";
    }

    //updateStatus
    @RequestMapping("/updateStatus.do")
    public String updateStatus(@RequestParam String id,Model model){
        boolean flag = service.updateStatus(id);
        if (flag){
            return "redirect:findAll.do";
        }
        model.addAttribute("msg","修改失败");
        return "service/service_list";
    }

    //del
    @RequestMapping("/del.do")
    public String del(@RequestParam String id,Model model){
        boolean flag = service.del(id);
        if (flag){
            return "redirect:findAll.do";
        }
        model.addAttribute("msg","修改失败");
        return "service/service_list";
    }
}
