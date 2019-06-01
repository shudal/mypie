package moe.perci.pie.api;

import moe.perci.pie.config.CodeConfig;
import moe.perci.pie.config.MetaConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
public class MetaController {
    @GetMapping("meta")
    public HashMap<String, Object> getMeta(HttpServletRequest request, HttpSession httpSession) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "-1");
        result.put("msg", "");
        result.put("data", "");

        try {
            result.put("data", MetaConfig.metaConfig.metas);
            result.put("code" ,1);
            result.put("msg", "OK");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", e.getMessage());
        }
        return result;
    }

    @PostMapping("meta")
    public HashMap<String, Object> getVideos(HttpServletRequest request, HttpSession httpSession) {
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

            if (request.getParameter("basePythonDir") != null) {
                MetaConfig.metaConfig.setMetaByMetaKey("basePythonDir", request.getParameter("basePythonDir"));
            }
            if (request.getParameter("baseVideoSrc") != null) {
                MetaConfig.metaConfig.setMetaByMetaKey("baseVideoSrc", request.getParameter("baseVideoSrc"));
            }

            result.put("code", 1);
            result.put("msg", "OK");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("msg", e.getMessage());
        }
        return result;
    }
}
