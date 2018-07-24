package com.luke.role.service.impl;

import com.luke.role.bean.RolePage;
import com.luke.role.bean.Role_info;
import com.luke.role.mapper.RoleMapper;
import com.luke.role.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    private RoleMapper mapper;

    @Override
    public RolePage findAll() {
        RolePage rolePage = new RolePage();
        rolePage.setTotal(mapper.getRoleCount());
        List<Role_info> role_infos = mapper.findAll(rolePage);
        rolePage.setList(role_infos);
        return rolePage;
    }

    @Override
    public RolePage findRoleByLimit(RolePage rolePage) {
        rolePage.setTotal(mapper.getRoleCount());
        List<Role_info> role_infos = mapper.findAll(rolePage);
        rolePage.setList(role_infos);
        return rolePage;
    }
}
