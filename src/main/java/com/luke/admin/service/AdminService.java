package com.luke.admin.service;

import com.luke.admin.bean.Admin;
import com.luke.admin.bean.AdminPage;
import com.luke.role.bean.Module_info;
import com.luke.role.bean.Role_info;

import java.util.List;
import java.util.Map;

/***
 * com.luke.admin.service
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
public interface AdminService {
    AdminPage findByLimit();

    AdminPage ConditionQueryLimit(AdminPage adminPage);

    List<Module_info> findAllModule();

    List<Role_info> findAllRole();

    Admin findByCode(String admin_code);

    boolean add(Map<String, String> map);

    Admin findAdminById(String id);

    boolean modi(Map<String, String> map);
}
