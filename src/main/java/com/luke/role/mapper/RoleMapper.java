package com.luke.role.mapper;

import com.luke.role.bean.Module_info;
import com.luke.role.bean.RolePage;
import com.luke.role.bean.Role_info;
import org.apache.ibatis.annotations.Param;
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

    int getCount();

    List<Module_info> findModuleById(Role_info role_info);

    List<Module_info> findModule();

    Role_info findRoleByName(String role_name);

    void insertRole(String role_name);

    int insertRole_Module(@Param("role_id") String role_id, @Param("module_id") String module_id);

    Role_info findRoleById(String role_id);

    void updateRole(@Param("role_id") String role_id, @Param("role_name") String role_name);

    void deleteOldRMById(String role_id);

    Role_info findRoleByNameAndId(@Param("role_id")String role_id, @Param("role_name")String role_name);
}
