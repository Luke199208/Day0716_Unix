package com.luke.role.service.impl;


import com.luke.role.bean.Module_info;
import com.luke.role.bean.RolePage;
import com.luke.role.bean.Role_info;
import com.luke.role.mapper.RoleMapper;
import com.luke.role.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/***
 * com.luke.role.service.impl
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
@Service("RoleA")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;


    @Override
    public RolePage findAll() {
        RolePage rolePage = new RolePage();
        rolePage.setTotal(roleMapper.getCount());
        List<Role_info> role_infos = roleMapper.findAll(rolePage);
        for (Role_info role_info : role_infos) {
            List<Module_info> module_infos = roleMapper.findModuleById(role_info);
            role_info.setModule_infoList(module_infos);
        }
        rolePage.setList(role_infos);
        return rolePage;
    }

    @Override
    public RolePage findRoleByLimit(RolePage rolePage) {
        rolePage.setTotal(roleMapper.getCount());
        List<Role_info> role_infos = roleMapper.findAll(rolePage);
        for (Role_info role_info : role_infos) {
            List<Module_info> module_infos = roleMapper.findModuleById(role_info);
            role_info.setModule_infoList(module_infos);
        }
        rolePage.setList(role_infos);
        return rolePage;
    }

    @Override
    public List<Module_info> findModule() {
        List<Module_info> module_infos = roleMapper.findModule();
        return module_infos;
    }

    @Override
    public boolean add(Map<String, String> map) {
        String role_name =  map.get("role_name");
        Role_info role_info = roleMapper.findRoleByName(role_name);
        if (role_info == null){
            int flag = 0;
            roleMapper.insertRole(role_name);
            Role_info roleInfo = roleMapper.findRoleByName(role_name);
            for (String s : map.keySet()) {
                if (!s.equals("role_name")){
                    flag =roleMapper.insertRole_Module(roleInfo.getRole_id(),s);
                }
            }
            return flag == 1;
        }
        return false;
    }

    @Override
    public Role_info findRoleById(String id) {

        return roleMapper.findRoleById(id);
    }

    @Override
    public boolean modi(Map<String,String> map) {
        String role_name =  map.get("role_name");
        String role_id =  map.get("role_id");
        Role_info role_info = roleMapper.findRoleByNameAndId(role_id,role_name);
        if (role_info == null){
            int flag = 0;
            roleMapper.updateRole(role_id,role_name);
            roleMapper.deleteOldRMById(role_id);
            Role_info roleInfo = roleMapper.findRoleByName(role_name);
            for (String s : map.keySet()) {
                if (!s.equals("role_name")&&!s.equals("role_id")){
                    flag =roleMapper.insertRole_Module(roleInfo.getRole_id(), s);
                }
            }
            return flag == 1;
        }
        return false;
    }


}
