package com.luke.admin.mapper;

import com.luke.admin.bean.Admin;
import com.luke.admin.bean.AdminPage;
import com.luke.role.bean.Module_info;
import com.luke.role.bean.Role_info;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * com.luke.admin.mapper
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
@Repository
public interface AdminMapper {

    /*----------------------page---------------------*/
    int getCount();

    int getCountByCondition(AdminPage adminPage);

    List<Admin> findAdminByLimit(AdminPage adminPage);

    //List<Admin> findAllAdmin();

    //List<Admin> findAdminByRoleModule(@Param("admin_id") String admin_id, @Param("role_name")String role_name,@Param("module_name") String module_name);

    List<Admin> ConditionQueryLimit(AdminPage adminPage);

    List<Role_info> findRoleById(Admin admin);

    List<Module_info> findAllModule();

    /*------------------------add-----------------------*/
    List<Role_info> findAllRole();

    Admin findByCode(String admin_code);

    int insertAdmin(Admin admin);

    int insertAdmin_role(@Param("admin_id") String admin_id,@Param("role_id") String s);

    /*------------------------modi-------------------------------*/

    Admin findAdminById(String admin_id);

    int updateAdmin(Admin admin);

    void deleteAdminRoleById(String admin_id);
}
