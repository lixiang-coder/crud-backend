package com.xzy.crud.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xzy.crud.common.PageBean;
import com.xzy.crud.mapper.UserMapper;
import com.xzy.crud.pojo.User;
import com.xzy.crud.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public void add(User user) {
        // 补充属性
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.add(user);
    }

    @Override
    public void update(User user) {
        // 补充属性
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    // 根据id删除员工
    @Override
    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    // 批量删除
    @Override
    public void delete(List<Integer> ids) {
        userMapper.deleteByIds(ids);
    }

    @Override
    public User findById(Long id) {
        User user = userMapper.findById(id);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userMapper.findAll();
        return userList;
    }

    /**
     * 原生的分页查询
     *
     * @param page     页码
     * @param pageSize 每页展示记录数
     * @return
     */
    @Override
    public PageBean page01(Integer page, Integer pageSize) {
        // 1.获取总记录数
        Long count = userMapper.count();

        //2、获取分页查询结果列表
        Integer start = (page - 1) * pageSize; //计算起始索引 , 公式: (页码-1)*页大小
        List<User> userList = userMapper.page(start, pageSize);

        //3、封装PageBean对象
        PageBean pageBean = new PageBean(count, userList);
        return pageBean;
    }

    @Override
    public PageBean page02(Integer page, Integer pageSize, Long id, String name, Integer age, String status, String sex,
                           String address, String phone, LocalDateTime createTime, LocalDateTime updateTime) {
        // 设置分页参数
        PageHelper.startPage(page, pageSize);
        // 执行分页查询
        List<User> empList = userMapper.list(id, name, age, status, sex, address, phone, createTime, updateTime);
        // 获取分页结果
        Page<User> p = (Page<User>) empList;
        //封装PageBean
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }


}
