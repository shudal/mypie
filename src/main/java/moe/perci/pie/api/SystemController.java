package moe.perci.pie.api;

import moe.perci.pie.config.CodeConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("system")
public class SystemController {
    @PostMapping("shutdown")
    public HashMap<String, Object> shutdown(HttpServletRequest request, HttpSession httpSession) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "-1");
        result.put("msg", "");
        result.put("data", "");

        try {
            // 鉴权。
            String role = httpSession.getAttribute("role").toString();
            if (!role.equals(CodeConfig.ROLE_ADMIN + "")) {
                result.put("msg", "permission_denied");
                return result;
            }

            String theCommand = "shutdown -h now";
            Process process = Runtime.getRuntime().exec(theCommand);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", e.getMessage());
        }

        return result;
    }

    @PostMapping("reboot")
    public HashMap<String, Object> reboot(HttpServletRequest request, HttpSession httpSession) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "-1");
        result.put("msg", "");
        result.put("data", "");

        try {
            // 鉴权。
            String role = httpSession.getAttribute("role").toString();
            if (!role.equals(CodeConfig.ROLE_ADMIN + "")) {
                result.put("msg", "permission_denied");
                return result;
            }

            String theCommand = "reboot";
            Process process = Runtime.getRuntime().exec(theCommand);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", e.getMessage());
        }

        return result;
    }


}
