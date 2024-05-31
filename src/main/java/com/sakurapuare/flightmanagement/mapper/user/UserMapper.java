package com.sakurapuare.flightmanagement.mapper.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakurapuare.flightmanagement.pojo.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    default User findUserByUsername(String username) {
        return this.selectOne(
                new QueryWrapper<User>()
                        .eq("username", username));
    }

    default User findUserByUsernameAndPassword(String username, String password) {
        return this.selectOne(
                new QueryWrapper<User>()
                        .eq("username", username)
                        .eq("password", password));
    }

    default List<User> findUserListByUsernameAndPassword(String username, String password) {
        return this.selectList(
                new QueryWrapper<User>()
                        .eq("username", username)
                        .eq("password", password));
    }

}
