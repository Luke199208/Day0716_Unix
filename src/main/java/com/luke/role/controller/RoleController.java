package com.luke.role.controller;

import com.luke.role.bean.RolePage;
import com.luke.role.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

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

}
