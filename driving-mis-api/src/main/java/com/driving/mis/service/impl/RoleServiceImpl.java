package com.driving.mis.service.impl;

import com.driving.common.util.PageUtils;
import com.driving.mis.dao.RoleDao;
import com.driving.mis.pojo.RoleEntity;
import com.driving.mis.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    @Override
    public ArrayList<HashMap> searchAllRole() {
        ArrayList<HashMap> list = roleDao.searchAllRole();
        return list;
    }

    @Override
    public HashMap searchById(int id) {
        HashMap map = roleDao.searchById(id);
        // String defaultPermissions = MapUtil.getStr(map, "defaultPermissions");
        // String permissions = MapUtil.getStr(map, "permissions");
        // map.replace("defaultPermissions", JSONUtil.parseArray("defaultPermissions"));
        // map.replace("permissions", JSONUtil.parseArray("permissions"));
        return map;
    }

    @Override
    public PageUtils searchRoleByPage(HashMap param) {
        ArrayList<HashMap> list = roleDao.searchRoleByPage(param);
        long count = roleDao.searchRoleCount(param);
        int start = (Integer) param.get("start");
        int length = (Integer) param.get("length");
        PageUtils pageUtils = new PageUtils(list, count, start, length);
        return pageUtils;
    }

    @Override
    public int insert(RoleEntity role) {
        int rows = roleDao.insert(role);
        return rows;
    }

    @Override
    public ArrayList<Integer> searchUserIdByRoleId(int roleId) {
        ArrayList<Integer> list = roleDao.searchUserIdByRoleId(roleId);
        return list;
    }

    @Override
    public int update(RoleEntity role) {
        int rows = roleDao.update(role);
        return rows;
    }

    @Override
    public int deleteRoleByIds(Integer[] ids) {
        if (!roleDao.searchCanDelete(ids)) {
            throw new HxdsException("无法删除关联用户的角色");
        }
        int rows = roleDao.deleteRoleByIds(ids);
        return rows;
    }
}
