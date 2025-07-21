package com.hs.minigame.mapper.login;

import com.hs.minigame.vo.UsersVO;
import org.apache.ibatis.annotations.*;

@Mapper()
public interface LoginMapper {

    @Select("select * from users where user_id =#{id}")
    public UsersVO selectUser(String id);

    @Insert("insert into users(user_id, user_pw,user_name,user_nickname,user_money,user_role,user_avatar_img,user_email)" +
            "values (#{user_id},#{user_pw},#{user_name},#{user_nickname},#{user_money},#{user_role},#{user_avatar_img},#{user_email})")
    public void insertUser(UsersVO users);

    @Delete("delete from record where user_id = #{userId}")
    public void deleteUserFromRecord(@Param("userId") String userId);

    @Delete("delete from users where user_id = #{userId}")
    public void deleteUser(@Param("userId") String userId);

    @Update("update users set user_id=#{users.user_id},user_pw=#{users.user_pw},user_name=#{users.user_name},user_nickname=#{users.user_nickname},user_email=#{users.user_email},user_role=#{users.user_role,jdbcType=VARCHAR},user_avatar_img=#{users.user_avatar_img,jdbcType=VARCHAR}  WHERE user_id = #{originalId}")
    public boolean updateUser(@Param("originalId") String originalId, @Param("users") UsersVO users);

    @Select("SELECT user_id FROM users WHERE user_name = #{user_name} AND user_email = #{user_email}")
    public String findUserId(@Param("user_name") String name, @Param("user_email") String email);

    //유저 조회
    @Select("SELECT * FROM users WHERE user_id = #{user_id} AND user_name = #{user_name} AND user_email = #{user_email}")
    public UsersVO resetUserPw(@Param("user_id") String userId,
                               @Param("user_name") String userName,
                               @Param("user_email") String userEmail);

    // 비밀번호 업데이트
    @Update("UPDATE users SET user_pw = #{newPw} WHERE user_id = #{user_id}")
    public void changeUserPw(@Param("user_id") String userId, @Param("newPw") String newPw);
}

