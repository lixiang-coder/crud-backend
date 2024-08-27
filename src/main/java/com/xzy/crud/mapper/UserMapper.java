package com.xzy.crud.mapper;


import com.xzy.crud.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;


@Mapper
public interface UserMapper {

    // 新增
    @Insert("insert into user (id,name,age,status,sex,address,phone,create_time,update_Time) values " +
            "(#{id},#{name},#{age},#{status},#{sex},#{address},#{phone},#{createTime},#{updateTime})")
    void add(User user);

    // 修改
    void update(User user);

    // 删除
    @Delete("delete from user where id = #{id} ")
    void deleteById(Long id);

    void deleteByIds(List<Integer> ids);

    @Select("select * from user where id = #{id}")
    User findById(Long id);

    @Select("select * from user")
    List<User> findAll();

    //获取总记录数
    @Select("select count(*) from user")
    Long count();

    //原生的分页查询
    @Select("select * from user limit #{start}, #{pageSize}")
    List<User> page(Integer start, Integer pageSize);

    // 使用pagehelper进行条件分页查询
    List<User> list(Long id, String name, Integer age, String status, String sex, String address, String phone,
                    LocalDateTime createTime, LocalDateTime updateTime);
}
