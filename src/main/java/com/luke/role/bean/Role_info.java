package com.luke.role.bean;

import java.util.List;

/***
 * com.luke.role.bean
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
public class Role_info {
    private int role_id;
    private String role_name;

    private List<Module_info> module_infoList;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public List<Module_info> getModule_infoList() {
        return module_infoList;
    }

    public void setModule_infoList(List<Module_info> module_infoList) {
        this.module_infoList = module_infoList;
    }

    @Override
    public String toString() {
        return "Role_info{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                ", module_infoList=" + module_infoList +
                '}';
    }
}
