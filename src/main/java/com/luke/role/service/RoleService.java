package com.luke.role.service;

import com.luke.role.bean.Module_info;
import com.luke.role.bean.RolePage;
import com.luke.role.bean.Role_info;

import java.util.List;
import java.util.Map;

/***
 * com.luke.role.service
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
public interface RoleService {
    RolePage findAll();

    RolePage findRoleByLimit(RolePage rolePage);

    List<Module_info> findModule();

    boolean add(Map<String, String> map);

    Role_info findRoleById(String id);

    boolean modi(Map<String, String> map);
}
