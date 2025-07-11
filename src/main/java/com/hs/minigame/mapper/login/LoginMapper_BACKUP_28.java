package com.hs.minigame.mapper.login;

import com.hs.minigame.vo.UsersVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper()
public interface LoginMapper {

<<<<<<< HEAD
    @Select("select * from users where user_id =#{id}")
=======
    @Select("select user_id, user_pw, user_name, user_nickname, user_money from users where user_id =#{id}")

>>>>>>> fc866169a10f35db525e31a37f9d772e5b7ff9bc
    UsersVO selectUser(String id);


    @Insert("insert into users(user_id, user_pw,user_name,user_nickname,user_money,user_role,user_avatar_img)"+
            "values (#{user_id},#{user_pw},#{user_name},#{user_nickname},#{user_money},#{user_role},#{user_avatar_img})"
    )
    int insertUser(UsersVO user);
}

