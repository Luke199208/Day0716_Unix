package com.luke.role.mapper;

import com.luke.role.bean.RolePage;
import com.luke.role.bean.Role_info;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * com.luke.role.mapper
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
@Repository
public interface RoleMapper {

    List<Role_info> findAll(RolePage rolePage);

    int getRoleCount();
}
