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


//       int result = loginMapper.updateUser(users);
//        return result == 1;
    }

    public boolean loginCheck(HttpSession session) {
        UsersVO user = (UsersVO) session.getAttribute("users");
        if (user == null) return false;
        return true;
    }

}
