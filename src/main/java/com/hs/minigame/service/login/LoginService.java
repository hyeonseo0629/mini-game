package com.hs.minigame.service.login;

import com.hs.minigame.mapper.login.LoginMapper;
import com.hs.minigame.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
   private LoginMapper loginMapper; //의존성 주입

       public LoginVO getUser(String id){//mapper를 통해 db 사용자 조회
           return loginMapper.selectUser(id);
       }





}
