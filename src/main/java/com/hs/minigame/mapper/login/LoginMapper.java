package com.hs.minigame.mapper.login;

import com.hs.minigame.vo.UsersVO;
import org.apache.ibatis.annotations.*;

@Mapper()
public interface LoginMapper {

    @Select("select user_no, user_id, user_pw, user_name, user_nickname, user_money, user_email from users where user_id =#{id}")
    UsersVO selectUser(String id);


    @Insert("insert into users(user_id, user_pw,user_name,user_nickname,user_money,user_role,user_avatar_img,user_email)"+
            "values (#{user_id},#{user_pw},#{user_name},#{user_nickname},#{user_money},#{user_role},#{user_avatar_img},#{user_email})"
    )
    public void insertUser(UsersVO users);


    @Delete("DELETE FROM record WHERE user_id = #{userId}")
    void deleteUserFromRecord(@Param("userId") String userId);

    @Delete("delete from users where user_id = #{userId}")
    void deleteUser(@Param("userId") String userId);

    @Update("update users set user_id=#{users.user_id},user_pw=#{users.user_pw},user_name=#{users.user_name},user_nickname=#{users.user_nickname},user_email=#{users.user_email}  WHERE user_id = #{originalId}")
    boolean updateUser(@Param("originalId") String originalId, @Param("users") UsersVO users);

    @Select("SELECT user_id FROM users WHERE user_name = #{user_name} AND user_email = #{user_email}")
    String findUserId(@Param("user_name") String name, @Param("user_email") String email);


}

