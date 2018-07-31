package com.luke.role.controller;

import com.luke.role.bean.Module_info;
import com.luke.role.bean.RolePage;
import com.luke.role.bean.Role_info;
import com.luke.role.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/***
 * com.luke.role.controller
 * dllo
 * 18/7/24
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
@RequestMapping("/role")
public class RoleController {

    @Resource(name = "RoleA")
    private RoleService service;

    @RequestMapping("/findAll.do")
    public String findAll(Model model){
        RolePage rolePage =  service.findAll();
        model.addAttribute("rolePage",rolePage);
        return "role/role_list";
    }

    @RequestMapping("/findRoleByLimt.do")
    public String findRoleByLimt(@RequestParam String currentPage, Model model){
        RolePage rolePage = new RolePage();
        rolePage.setCurrentPage(Integer.parseInt(currentPage));
        rolePage = service.findRoleByLimit(rolePage);
        model.addAttribute("rolePage",rolePage);
        return "role/role_list";
    }

    //add
    @RequestMapping("/ToAdd.do")
    public String toAdd(Model model){
        List<Module_info> module_infoList = service.findModule();
        model.addAttribute("list",module_infoList);
        return "role/role_add";
    }

    @RequestMapping("/roleAdd.do")
    @ResponseBody
    public String add(@RequestBody Map<String,String> map){
        String role_name =map.get("role_name");
//        for (String s : map.keySet()) {
//            System.out.println(s+"----->"+map.get(s));
//        }
        for (String s : map.keySet()) {
            System.out.println(s+"----->"+map.get(s));
        }
        if(map.size()>1 && !role_name.isEmpty() && !role_name .equals("")){
            boolean flag = service.add(map);
            if (flag){
                return "添加成功!";
            }else {
                return "角色已被创建";
            }
        }else {
            return "请重新填写";
        }
    }

    @RequestMapping("/ToModi.do")
    public String ToModi(@RequestParam String id, Model model){
        Role_info role_info = service.findRoleById(id);
        List<Module_info> module_infoList = service.findModule();
        model.addAttribute("role",role_info);
        model.addAttribute("list",module_infoList);
        return "role/role_modi";
    }

    @RequestMapping("/roleModi.do")
    @ResponseBody
    public String modi(@RequestBody Map<String,String> map){
        String role_name =map.get("role_name");
//        for (String s : map.keySet()) {
//            System.out.println(s+"----->"+map.get(s));
//        }
        if(map.size()>2 && !role_name.isEmpty() && !role_name.equals("")){
            boolean flag = service.modi(map);
            if (flag){
                return "修改成功!";
            }else {
                return "角色已被创建";
            }
        }else {
            return "请重新填写";
        }
    }



}
