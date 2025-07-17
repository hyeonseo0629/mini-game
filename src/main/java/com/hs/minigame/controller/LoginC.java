package com.hs.minigame.controller;

import com.hs.minigame.mapper.login.LoginMapper;
import com.hs.minigame.service.login.LoginService;
import com.hs.minigame.service.shop.ShopService;
import com.hs.minigame.vo.ShopItemsVO;
import com.hs.minigame.vo.UsersVO;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginC {

    private static final int BASE_AVATAR_ID = 30;

    @Autowired
    private LoginService loginService; //새로운 service마다 의존성 필요

    @Autowired
    private ShopService shopService;
    private LoginMapper loginMapper;
    @Autowired
    private HttpServletResponse httpServletResponse;

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String pw, HttpSession session, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        // 로그인 제어 / db에서 조회
        UsersVO users = loginService.getUser(id);
        String referer = request.getHeader("Referer");
        //"Referer" : 사용자가 이 요청을 보내기 전 머물렀던 페이지URL


        System.out.println("DEBUG - 아바타 이미지: " + users.getUser_avatar_img());

        if (users == null) {
            System.out.println("id 불일치");
            redirectAttributes.addFlashAttribute("alert","id 불일치");
            redirectAttributes.addFlashAttribute("content", "game/game_menu.jsp");
            return "redirect:/main_page";
        } else if (users.getUser_pw().equals(pw)) {

            //현재 세션 완전 제거 -> 이전 사용자의 세션을 클리어한다
            session.invalidate();

            //새로운 세션 객체를 강제로 생성(true = 세션이 없으면 강제로 만들어라 라는 뜻)
            session = request.getSession(true);


            //.trim은 문자열 양 끝의 공백 제거,(null이거나 공백만 있는 문자열이면 user의 아바타에 기본아바타를 설정해줘)
            if (users.getUser_avatar_img() == null || users.getUser_avatar_img().trim().isEmpty()) {
                users.setUser_avatar_img("base_avatar.webp");
            }

            //새로 생성한 세션에 로그인 한 사용자 정보를 다시 담음
            //로그인할 때 DB에서 다시 가져오기 때문에 가장 최신 인벤토리 상태가 반영된다
            session.setAttribute("users", users);

            //인벤토리
            List<ShopItemsVO> inventoryItems = shopService.getInventory(users.getUser_no());
            session.setAttribute("inventoryItems", inventoryItems);

            //기본 아바타 설정 작업(구매하지 않더라도 인벤토리에 기본아바타가 있게끔)
            //DB에 기록하는게 아니라 단순히 세션에 넣는 작업 뿐
            boolean hasAvatar = inventoryItems.stream()
                    .anyMatch(item -> item.getItem_id().equals(String.valueOf(BASE_AVATAR_ID)));

            if (!hasAvatar) {
                ShopItemsVO baseAvatar = shopService.getItemById(BASE_AVATAR_ID);
                inventoryItems.add(0,baseAvatar);
            }



            System.out.println("로그인 성공");
            redirectAttributes.addFlashAttribute("alert","로그인 성공");
            redirectAttributes.addFlashAttribute("content", "game/game_menu.jsp");
            redirectAttributes.addFlashAttribute("user", users);
            return "redirect:" + (referer != null ? referer : "/default_page");
            //삼항연산자 활용 : 조건 ? 참일 때 값(referer) : 거짓일 때 값("/default_page")
            // referer 값있으면 그 페이지로 / 없으면 "/default_page"로
        } else {
            System.out.println("pw 불일치");
            redirectAttributes.addFlashAttribute("alert","pw 불일치");
            redirectAttributes.addFlashAttribute("content", "game/game_menu.jsp");
            return "redirect:/main_page";
        }
//        if (users == null) {model용
//            System.out.println("id 불일치");
//            model.addAttribute("alert","id 불일치");
//            model.addAttribute("content", "game/game_menu.jsp");
//            return "main_page";
//        } else if (users.getUser_pw().equals(pw)) {
//            session.setAttribute("users", users);
//            System.out.println("로그인 성공");
//            model.addAttribute("alert","로그인 성공");
//            model.addAttribute("content", "game/game_menu.jsp");
//            model.addAttribute("user", users);
//            return "main_page";
//        } else {
//        System.out.println("pw 불일치");
//           model.addAttribute("alert","pw 불일치");
//            model.addAttribute("content", "game/game_menu.jsp");
//            return "main_page";
//        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {

        session.invalidate();
        redirectAttributes.addFlashAttribute("alert2","로그아웃");
        redirectAttributes.addFlashAttribute("content", "game/game_menu.jsp");
        return "redirect:/main_page";

//        session.invalidate();model용
//        model.addAttribute("alert2","로그아웃");
//        model.addAttribute("content", "game/game_menu.jsp");
//        return "main_page";
    }
    @GetMapping("/login")
    public String showLoginPage() {
        return "login/login";
    }
    @PostMapping("/sign")
    public String sign(@ModelAttribute UsersVO users,RedirectAttributes redirectAttributes) {

        if(!isValidId(users.getUser_id())){
        redirectAttributes.addFlashAttribute("alert","아이디는 6~12자, 소문자+숫자만 가능합니다.");
        return "redirect:/main_page";
        }
        if(!isValidPassword(users.getUser_pw())){
        redirectAttributes.addFlashAttribute("alert","비밀번호는 8자 이상, 대문자/숫자/특수문자를 포함해야 합니다.");
        return "redirect:/main_page";
        }

        users.setUser_money(5000);
        users.setUser_role("USER");
        users.setUser_avatar_img("base_avatar.webp");

        boolean success = loginService.registerUser(users);

       if(success) {
           redirectAttributes.addFlashAttribute("alert","회원 가입 성공");
       } else {
           redirectAttributes.addFlashAttribute("alert","회원 가입 실패");
       }
       //redirectAttributes.addFlashAttribute("content", "game/game_menu.jsp");
       return  "redirect:/main_page";
    }

    public boolean isValidId(String id) {
        // 소문자+숫자, 6~12자
        String pattern = "^[a-z0-9]{6,12}$";
        return id != null && id.matches(pattern);
    }
    public boolean isValidPassword(String pw) {
        // 8자 이상, 대문자, 숫자, 특수문자
        String pattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$";
        return pw != null && pw.matches(pattern);
    }


    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("user_id") String userId, HttpSession session, RedirectAttributes redirectAttributes ) {
        System.out.println("Controller: Attempting to delete user with ID: " + userId);
        try {
          try {
              loginService.deleteUserFromRecord(userId);
          } catch (Exception e) {
              System.out.println("record 임시 테이블 없음 /삭제 실패 → 무시하고 진행: " + e.getMessage());
          }
          System.out.println("Calling deleteUser");
          loginService.deleteUser(userId);
          session.invalidate();
          redirectAttributes.addFlashAttribute("alert", "회원 탈퇴가 완료되었습니다.");
          return "redirect:/main_page";
      } catch (Exception e) {
          e.printStackTrace();
          redirectAttributes.addFlashAttribute("alert", "회원 탈퇴에 실패했습니다.");
          return "redirect:/main_page";
      }
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute UsersVO users,@RequestParam("originalId") String originalId,RedirectAttributes redirectAttributes,HttpSession session) {

        if (users.getUser_pw() == null || users.getUser_pw().trim().isEmpty()) {
            UsersVO originalUser = loginService.getUser(originalId);
            users.setUser_pw(originalUser.getUser_pw());
        }

     boolean updateSuccess = loginService.updateUser(originalId,users);

     if(updateSuccess) {
         redirectAttributes.addFlashAttribute("alert","회원 정보가 수정되었습니다.");
         session.setAttribute("users", users);
     }else{
         redirectAttributes.addFlashAttribute("alert","수정에 실패하였습니다. 다시 시도해주세요.");
     }
        return "redirect:/main_page";
    }
    @PostMapping("/findId")
    @ResponseBody
        public String findId(@RequestParam String user_name,@RequestParam String user_email){
      String userId = loginService.findUserId(user_name,user_email);
      if(userId != null){
          return "당신의 ID :" + userId;
      }else{
          return"일치하는 정보가 없습니다.";
      }
    }

    @PostMapping("/findPw")
    @ResponseBody
    public String findPw(@RequestParam String user_id,@RequestParam String user_name,@RequestParam String user_email,@RequestParam("new_pw") String newPw) {
     String result = loginService.findUserPw(user_id,user_name,user_email,newPw);
     return result;
   }
   //세션 완료 알람 (보류)
//    @GetMapping("/checksession")
//    @ResponseBody
//    public ResponseEntity<Map<String,String>>checksession(HttpSession session){
//       Map<String,String> response = new HashMap<>();
//
//       if(session.getAttribute("users")==null){
//           response.put("status","expired");
//       }else{
//           response.put("status","active");
//       }
//       return ResponseEntity.ok(response);
//    }
}
