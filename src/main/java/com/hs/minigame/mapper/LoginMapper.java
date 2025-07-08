package com.hs.minigame.mapper;

import com.hs.minigame.vo.LoginVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper()
public interface LoginMapper {

    @Select("select user_id, user_pw, user_name from users where user_id =#{id}")
    LoginVO selectUser(String id);

}

