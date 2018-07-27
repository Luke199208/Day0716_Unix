package com.luke.admin.service.impl;

import com.luke.admin.bean.Admin;
import com.luke.admin.bean.AdminPage;
import com.luke.admin.mapper.AdminMapper;
import com.luke.admin.service.AdminService;
import com.luke.role.bean.Module_info;
import com.luke.role.bean.Role_info;
import com.luke.role.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/***
 * com.luke.admin.service.impl
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
@Service("AdminA")
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper mapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public AdminPage findByLimit() {
        AdminPage adminPage = new AdminPage();
        adminPage.setTotal(mapper.getCount());
        List<Admin> admins = mapper.findAdminByLimit(adminPage);
        for (Admin admin : admins) {
            List<Role_info> role_infos = mapper.findRoleById(admin);
            admin.setRole_infoList(role_infos);
        }
        adminPage.setList(admins);
        return adminPage;
    }

    @Override
    public AdminPage ConditionQueryLimit(AdminPage adminPage) {
        if (adminPage.getModule_name().equals("All")) {
            adminPage.setModule_name("");
        }
//        adminPage.setTotal(mapper.getCountByCondition(adminPage));
//        List<Admin> admins = mapper.ConditionQueryLimit(adminPage);
//        for (Admin admin : admins) {
//            List<Role_info> role_infos = mapper.findRoleById(admin);
//            admin.setRole_infoList(role_infos);
//        }
//        int count = mapper.getCount();
//        List<Admin> admins = mapper.findAllAdmin();
//        for (int i = 0; i < admins.size(); i++) {
//            List<Admin> adminList = mapper.findAdminByRoleModule(admins.get(i).getAdmin_id()
//                    , adminPage.getRole_name()
//                    , adminPage.getModule_name());
//            if (adminList.size() > 0) {
//                List<Role_info> role_infos = mapper.findRoleById(admins.get(i));
//                admins.get(i).setRole_infoList(role_infos);
//            } else {
//                count -= 1;
//                admins.remove(i);
//            }
//        }
        adminPage.setTotal(mapper.getCountByCondition(adminPage));
        List<Admin> admins = mapper.ConditionQueryLimit(adminPage);
        for (Admin admin : admins) {
            List<Role_info> role_infos = mapper.findRoleById(admin);
            admin.setRole_infoList(role_infos);
        }
        adminPage.setList(admins);
        return adminPage;
    }

    @Override
    public List<Module_info> findAllModule() {
        return mapper.findAllModule();
    }

    /*-------------------------------------------add-----------------------------------------*/
    @Override
    public List<Role_info> findAllRole() {
        return mapper.findAllRole();
    }

    @Override
    public Admin findByCode(String admin_code) {
        return mapper.findByCode(admin_code);
    }

    @Override
    public boolean add(Map<String, String> map) {
        Admin admin = new Admin();
        for (String s : map.keySet()) {
            if (s.equals("admin_name")) admin.setAdmin_name(map.get(s));
            if (s.equals("admin_code")) admin.setAdmin_code(map.get(s));
            if (s.equals("admin_password")) admin.setAdmin_password(map.get(s));
            if (s.equals("admin_telephone")) admin.setAdmin_telephone(map.get(s));
            if (s.equals("admin_email")) admin.setAdmin_email(map.get(s));
        }
        //获得创建时间
        Date date = new Date();
        String dateFormat = String.valueOf(DateFormat.getDateInstance().format(date));
        admin.setEnrolldate(dateFormat);
        int flag = mapper.insertAdmin(admin);
        if (flag == 1) {
            int i = 0;
            admin = mapper.findByCode(admin.getAdmin_code());
            for (String s : map.keySet()) {
                if (!s.equals("admin_name") &&
                        !s.equals("admin_code") &&
                        !s.equals("admin_password") &&
                        !s.equals("admin_telephone") &&
                        !s.equals("admin_email")&&
                        !s.equals("reAdmin_password")) {
                    i = mapper.insertAdmin_role(admin.getAdmin_id(), s);
                }
            }
            return i==1;
        }
        return false;
    }

    /*--------------------------------------modi-----------------------------------------*/
    @Override
    public Admin findAdminById(String id) {
        return mapper.findAdminById(id);
    }

    @Override
    public boolean modi(Map<String, String> map) {
        Admin admin = new Admin();
        for (String s : map.keySet()) {
            if (s.equals("admin_code")) admin.setAdmin_code(map.get(s));
            if (s.equals("admin_name")) admin.setAdmin_name(map.get(s));
            if (s.equals("admin_telephone")) admin.setAdmin_telephone(map.get(s));
            if (s.equals("admin_email")) admin.setAdmin_email(map.get(s));
        }

        int flag = mapper.updateAdmin(admin);
        if (flag == 1){
            int i = 0 ;
            admin = mapper.findByCode(map.get("admin_code"));

            mapper.deleteAdminRoleById(admin.getAdmin_id());
            for (String s : map.keySet()) {
                if (!s.equals("admin_name") &&
                        !s.equals("admin_code") &&
                        !s.equals("admin_telephone") &&
                        !s.equals("admin_email")) {
                    i = mapper.insertAdmin_role(admin.getAdmin_id(), s);
                }
            }
            return i == 1 ;
        }
        return false;
    }
}
