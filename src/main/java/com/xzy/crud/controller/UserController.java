package com.xzy.crud.controller;

import com.xzy.crud.common.PageBean;
import com.xzy.crud.common.Result;
import com.xzy.crud.pojo.User;
import com.xzy.crud.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    // 新增用户
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    // 修改用户
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    // 根据id删除用户
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable("id") Long id) {
        userService.delete(id);
        return Result.success();
    }

    // 批量删除
    @DeleteMapping("deleteByIds/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids) {
        userService.delete(ids);
        return Result.success();
    }

    // 根据id查询用户
    @GetMapping("/{id}")
    public Result<User> findById(@PathVariable Long id) {
        return Result.success(userService.findById(id));
    }

    // 查询所有用户
    @GetMapping("/getAll")
    public Result<List<User>> findAll() {
        return Result.success(userService.findAll());
    }

    // 原生的分页查询
    @GetMapping("/page01")
    public Result page01(@RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "5") Integer pageSize) {

        //调用业务层分页查询功能
        PageBean pageBean = userService.page01(page, pageSize);
        //响应
        Result<PageBean> result = Result.success(pageBean);
        return result;
    }

    // 使用pagehelper进行条件分页查询
    @GetMapping("/page02")
    public Result page02(@RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "5") Integer pageSize,
                         Long id, String name, Integer age, String status, String sex, String address, String phone,
                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createTime,
                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime) {

        //调用业务层分页查询功能
        PageBean pageBean = userService.page02(page, pageSize, id, name, age, status, sex, address, phone, createTime, updateTime);
        //响应
        Result<PageBean> result = Result.success(pageBean);
        return result;
    }

}
