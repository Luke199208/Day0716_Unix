package com.luke.admin.bean;

import com.luke.abstract_bean.Page;

/***
 * com.luke.admin.bean
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
public class AdminPage extends Page{

    private String module_name;
    private String role_name;

    @Override
    public String toString() {
        return "AdminPage{" +
                "module_name='" + module_name + '\'' +
                ", role_name='" + role_name + '\'' +
                "} " + super.toString();
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
