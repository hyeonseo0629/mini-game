package com.hs.minigame.mapper.login;

import com.hs.minigame.vo.UsersVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper()
public interface LoginMapper {

    @Select("select user_id, user_pw, user_name, user_nickname, user_money from users where user_id =#{id}")
    LoginVO selectUser(String id);

}

