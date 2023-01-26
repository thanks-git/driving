package com.driving.mis.service;

import com.driving.common.util.PageUtils;
import com.driving.mis.pojo.RoleEntity;

import java.util.ArrayList;
import java.util.HashMap;

public interface RoleService {
    public ArrayList<HashMap> searchAllRole();

    public HashMap searchById(int id);

    public PageUtils searchRoleByPage(HashMap param);

    public int insert(RoleEntity role);

    public ArrayList<Integer> searchUserIdByRoleId(int roleId);

    public int update(RoleEntity role);

    public int deleteRoleByIds(Integer[] ids);
}