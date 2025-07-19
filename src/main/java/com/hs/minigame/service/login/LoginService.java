package com.hs.minigame.service.login;

import com.hs.minigame.mapper.login.LoginMapper;
import com.hs.minigame.vo.UsersVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;

    public UsersVO getUser(String id) {
        return loginMapper.selectUser(id);
    }

    public boolean registerUser(UsersVO users) {
        if (loginMapper.selectUser(users.getUser_id()) != null) {
            return false;
        }
        loginMapper.insertUser(users);
        return true;
    }

    @Transactional
    public void deleteUser(String userId) {
        System.out.println("deleteUser is called with ID: " + userId);
        loginMapper.deleteUser(userId);
        System.out.println("Deleting user with ID: " + userId);
    }

    public void deleteUserFromRecord(String userId) {
        System.out.println("deleteUserFromRecord is called with ID: " + userId);
        loginMapper.deleteUserFromRecord(userId);
    }

    public boolean updateUser(String originalId, UsersVO users) {
        System.out.println("updateUser is called with original ID: " + originalId + ", new ID: " + users.getUser_id());
        return loginMapper.updateUser(originalId, users);
    }

    public String findUserId(String name, String email) {
        return loginMapper.findUserId(name, email);
    }

    public boolean loginCheck(HttpSession session) {
        UsersVO user = (UsersVO) session.getAttribute("users");
        if (user == null) return false;
        return true;
    }

    public String findUserPw(String userId, String userName, String userEmail, String newPw) {

        UsersVO user = loginMapper.resetUserPw(userId, userName, userEmail); //유저 정보 조회

        if (user != null) {
            loginMapper.changeUserPw(userId, newPw);//비번 변경 처리
            return "비밀번호를 성공적으로 변경했습니다.";
        } else {
            return "일치하는 정보가 없습니다.";
        }
    }
}

