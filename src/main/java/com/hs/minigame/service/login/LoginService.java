package com.hs.minigame.service.login;


import com.hs.minigame.mapper.login.LoginMapper;
import com.hs.minigame.vo.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;

    public UsersVO getUser(String id){
        return loginMapper.selectUser(id);
    }
    public boolean registerUser(UsersVO users){
        if(loginMapper.selectUser(users.getUser_id())!=null){
            return false;
        }
        loginMapper.insertUser(users);
        return true;
    }
}
