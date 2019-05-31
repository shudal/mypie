package moe.perci.pie.api;

import moe.perci.pie.config.CodeConfig;
import moe.perci.pie.model.User;
import moe.perci.pie.model.service.UserService;
import moe.perci.pie.util.MyEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("login")
    public HashMap<String, Object> login(HttpServletRequest request, HttpSession httpSession) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "-1");
        result.put("msg", "");
        result.put("data", "");

        try {
            String username     = request.getParameter("username");
            String password     = request.getParameter("password");

            String encryptedPassword = MyEncrypt.sha1(password);

            System.out.println(username);
            System.out.println(password);
            User user = userService.findUserByUsernameAndStatus(username, CodeConfig.STATUS_USER_NORMAL);
            if (user == null) {
                result.put("msg", "user_not_exist");
                return result;
            }
            if (user.getPassword().equals(encryptedPassword)) {
                result.put("code", 1);
                result.put("msg", "OK");

                // 写入Session
                httpSession.setAttribute("id", user.getId());
                httpSession.setAttribute("nickname", user.getNickname());
                httpSession.setAttribute("role", user.getRole());

                HashMap<String, Object> data = new HashMap<>();
                data.put("nickname", user.getNickname());
                result.put("data", data);

                result.put("code", 1);
                result.put("msg", "OK");
            } else {
                result.put("msg", "password_wrong");
            }
        } catch (Exception e) {
            result.put("msg", e.getMessage());
        }
        return result;
    }
}
