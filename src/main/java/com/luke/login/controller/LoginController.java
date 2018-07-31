package com.luke.login.controller;

import com.luke.admin.bean.Admin;
import com.luke.admin.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
@RequestMapping("/login")
public class LoginController {

    @Resource(name = "AdminA")
    private AdminService adminService;

    @RequestMapping(value = "/login.do")
    public String login(){
            return "main";

    }

    @RequestMapping(value = "/validateCode.do",method = RequestMethod.POST)
    @ResponseBody
    public String validateCode(@RequestParam String admin_code){
        System.out.println(admin_code);
        Admin admin = adminService.findByCode(admin_code);
        if (admin!= null){
            return "true";
        }
        return "false" ;
    }

    @RequestMapping(value = "/validatePasswd.do",method = RequestMethod.POST)
    @ResponseBody
    public String validatePasswd(@RequestParam String admin_code, @RequestParam String admin_password){
        System.out.println(admin_code + admin_password);
        Admin admin = adminService.findByCodePasswd(admin_code,admin_password);
        if (admin!=null){
            return "true";
        }
        return "false" ;
    }


    @RequestMapping(value = "/validateVcode.do",method = RequestMethod.POST)
    @ResponseBody
    public String validateVcode(@RequestParam String verifyCode, HttpServletRequest request){
        System.out.println(verifyCode);
        String vcode = (String) request.getSession().getAttribute("vCode");
        System.out.println(vcode);
        boolean c = verifyCode.equalsIgnoreCase(vcode);
        return String.valueOf(c);
    }



}
