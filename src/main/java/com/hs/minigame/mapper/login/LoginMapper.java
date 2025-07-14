package com.hs.minigame.mapper.login;

import com.hs.minigame.vo.UsersVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper()
public interface LoginMapper {

    @Select("select user_no, user_id, user_pw, user_name, user_nickname, user_money from users where user_id =#{id}")


    UsersVO selectUser(String id);

    @Insert("insert into users(user_id, user_pw,user_name,user_nickname,user_money,user_role,user_avatar_img)"+
            "values (#{user_id},#{user_pw},#{user_name},#{user_nickname},#{user_money},#{user_role},#{user_avatar_img})"
    )
    int insertUser(UsersVO user);
}

