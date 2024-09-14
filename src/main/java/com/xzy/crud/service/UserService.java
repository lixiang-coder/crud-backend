package com.xzy.crud.service;

import com.xzy.crud.common.PageBean;
import com.xzy.crud.pojo.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    void add(User user);

    void update(User user);

    void delete(Long id);

    void delete(List<Integer> ids);

    User findById(Long id);

    List<User> findAll();

    PageBean page01(Integer page, Integer pageSize);

    PageBean page02(Integer page, Integer pageSize, Long id, String name, Integer age, String status, String sex,
                    String address, String phone, LocalDateTime createTime, LocalDateTime updateTime);
}
