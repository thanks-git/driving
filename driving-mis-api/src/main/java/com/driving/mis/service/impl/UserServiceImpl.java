package com.driving.mis.service.impl;

import com.driving.common.util.PageUtils;
import com.driving.mis.dao.UserDao;
import com.driving.mis.pojo.UserEntity;
import com.driving.mis.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Value("${wx.app-id}")
    private String appId;

    @Value("${wx.app-secret}")
    private String appSecret;

    @Resource
    private UserDao userDao;

    @Override
    public Set<String> searchUserPermissions(int userId) {
        Set<String> permissions = userDao.searchUserPermissions(userId);
        return permissions;
    }

    @Override
    public HashMap searchById(int userId) {
        HashMap map = userDao.searchById(userId);
        return map;
    }

    @Override
    public HashMap searchUserSummary(int userId) {
        HashMap map = userDao.searchUserSummary(userId);
        return map;
    }

    @Override
    public ArrayList<HashMap> searchAllUser() {
        ArrayList<HashMap> list = userDao.searchAllUser();
        return list;
    }

    @Override
    public Integer login(Map param) {
        return userDao.login(param);
    }

    @Override
    public int updatePassword(Map param) {
        return userDao.updatePassword(param);
    }

    @Override
    public PageUtils searchUserByPage(Map param) {
        ArrayList<HashMap> list = userDao.searchUserByPage(param);
        long count = userDao.searchUserCount(param);
        int start = (Integer) param.get("start");
        int length = (Integer) param.get("length");
        PageUtils pageUtils = new PageUtils(list, count, start, length);
        return pageUtils;
    }

    @Override
    public int insert(UserEntity user) {
        return userDao.insert(user);
    }

    @Override
    public int update(Map param) {
        return userDao.update(param);
    }

    @Override
    public int deleteUserByIds(Integer[] ids) {
        return userDao.deleteUserByIds(ids);
    }

    @Override
    public ArrayList<String> searchUserRoles(int userId) {
        ArrayList<String> list = userDao.searchUserRoles(userId);
        return list;
    }

    @Override
    public HashMap searchNameAndDept(int userId) {
        HashMap map = userDao.searchNameAndDept(userId);
        return map;
    }
}