package com.luke.admin.controller;

import com.luke.admin.bean.Admin;
import com.luke.admin.bean.AdminPage;
import com.luke.admin.service.AdminService;
import com.luke.role.bean.Module_info;
import com.luke.role.bean.Role_info;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/***
 * com.luke.admin.controller
 * dllo
 * 18/7/26
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
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "AdminA")
    private AdminService service;

    @RequestMapping("/findAll.do")
    public String findAll(Model model){
        AdminPage adminPage = service.findByLimit();
        List<Module_info> moduleInfos = service.findAllModule();
        model.addAttribute("moduleList",moduleInfos);
        model.addAttribute("adminPage",adminPage);
        return "admin/admin_list";
    }

    @RequestMapping("/ConditionQueryLimit.do")
    public String ConditionQueryLimit(AdminPage adminPage,Model model){
        //System.out.println(adminPage+"******----*******");
        adminPage = service.ConditionQueryLimit(adminPage);
        List<Module_info> moduleInfos = service.findAllModule();
        model.addAttribute("moduleList",moduleInfos);
        System.out.println(adminPage);
        if (adminPage != null){
            model.addAttribute("adminPage",adminPage);
        }else {
            model.addAttribute("msg","没有查到相关结果");
        }
        return "admin/admin_list";
    }

    @RequestMapping("/toAdd.do")
    public String toAdd(Model model){
        List<Role_info> roleInfoList = service.findAllRole();
        model.addAttribute("roleInfoList",roleInfoList);
        return "admin/admin_add";
    }

    @RequestMapping(value = "/addAdmin.do",method = RequestMethod.POST)
    @ResponseBody
    public String addAdmin(@RequestBody Map<String,String> map){
//        for (String s : map.keySet()) {
//            System.out.println(s + "--->" + map.get(s));
//        }
        String x = judge1(map);
        if (x != null) return x;
        if (map.size()>6){
            boolean flag = service.add(map);
            if (flag) return "添加成功";
        }
        return "至少勾选一个角色";
    }

    @RequestMapping("/toModi")
    public String toModi(@RequestParam String id,Model model){
        Admin admin = service.findAdminById(id);
        List<Role_info> roleInfoList = service.findAllRole();
        model.addAttribute("roleInfoList",roleInfoList);
        model.addAttribute("admin",admin);
        return "admin/admin_modi";
    }

    @RequestMapping(value = "/modi.do",method = RequestMethod.POST)
    @ResponseBody
    public String modi(@RequestBody Map<String,String> map){
//        for (String s : map.keySet()) {
//            System.out.println(s + "-----" + map.get(s));
//        }
        String x = judge2(map);
        if (x != null) return x;
        if (map.size()>4){
            boolean flag = service.modi(map);
            if (flag) return "修改成功";
        }
        return "请至少选择一个角色";
    }

    private String judge2(@RequestBody Map<String, String> map) {
        if(map.get("admin_name") == null || map.get("admin_name").equals(""))  return "姓名不能为空";
        if(map.get("admin_telephone") == null || map.get("admin_telephone").equals(""))  return "电话不能为空";
        if(map.get("admin_email") == null || map.get("admin_email").equals(""))  return "email不能为空";
        return null;
    }

    private String judge1(@RequestBody Map<String, String> map) {

        if (map.get("admin_name")== null || map.get("admin_name").equals("")){

            return "姓名不能为空!";
        }
        if (map.get("admin_code")!= null && !map.get("admin_code").equals("")){

            Admin admin = service.findByCode(map.get("admin_code"));
            if (admin!= null) return "账号已被注册";
        }else {
            return "登录账号不能为空!";
        }
        if (map.get("admin_password")== null || map.get("admin_password").equals("")){

            return "密码不能为空!";
        }
        if (map.get("reAdmin_password")!= null && !map.get("reAdmin_password").equals("")){

            if (!map.get("reAdmin_password").equals(map.get("admin_password"))) return "两次密码不同!";
        }else {
            return "确认密码不能为空!";
        }
        if (map.get("admin_telephone")== null || map.get("admin_telephone").equals("")){

            return "电话不能为空!";
        }
        if (map.get("admin_email")== null || map.get("admin_email").equals("")){

            return "email不能为空!";
        }
        return null;
    }
}
