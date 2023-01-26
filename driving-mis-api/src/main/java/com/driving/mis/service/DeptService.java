package com.driving.mis.service;

import com.driving.common.util.PageUtils;
import com.driving.mis.pojo.DeptEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface DeptService {
    public ArrayList<HashMap> searchAllDept();

    public PageUtils searchDeptByPage(Map param);

    public HashMap searchById(int id);

    public int insert(DeptEntity dept);

    public int update(DeptEntity dept);

    public int deleteDeptByIds(Integer[] ids);
}
