package com.hs.minigame.mapper.login;

import com.hs.minigame.vo.UsersVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper()
public interface LoginMapper {

    @Select("select user_id, user_pw, user_name from users where user_id =#{id}")
    UsersVO selectUser(String id);
    
    void insertUser(UsersVO users);
}

