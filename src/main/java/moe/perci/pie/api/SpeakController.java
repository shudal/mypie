package moe.perci.pie.api;

import moe.perci.pie.config.CodeConfig;
import moe.perci.pie.model.Saying;
import moe.perci.pie.model.User;
import moe.perci.pie.model.service.SayingService;
import moe.perci.pie.pyapi.MyOnlineSpeaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.HashMap;

@RestController
@RequestMapping("speak")
public class SpeakController {
    @Autowired
    SayingService sayingService;

    @GetMapping("something")
    public HashMap<String, Object> saySomething(HttpServletRequest request, HttpSession httpSession) {
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

            System.out.println(request.getParameter("content"));
            System.out.println(request.getParameter("vol"));
            BigInteger nowTime = new BigInteger(Calendar.getInstance().getTimeInMillis() + "");

            Saying saying = new Saying();
            saying.setContent(request.getParameter("content"));
            saying.setVol(Integer.parseInt(request.getParameter("vol")));
            saying.setSpeed(Integer.parseInt(request.getParameter("speed")));
            saying.setPit(Integer.parseInt(request.getParameter("pit")));
            saying.setCreateTime(nowTime);

            if (request.getParameter("per").equals("度丫丫")) {
                saying.setPer(4);
            } else if (request.getParameter("per").equals("度逍遥")) {
                saying.setPer(3);
            } else if (request.getParameter("per").equals("男声")) {
                saying.setPer(1);
            } else {
                saying.setPer(0);
            }

            saying.setStatus(CodeConfig.STATUS_SAYING_NOT_SAID);
            sayingService.save(saying);


            saying = sayingService.findSayingByCreateTime(nowTime);
            MyOnlineSpeaker.myOnlineSpeaker.say(saying.getId());
            result.put("code", 1);
            result.put("msg", "OK");
        } catch (Exception e) {
            result.put("msg", e.getMessage());
            e.printStackTrace();
        }

        return result;
    }
}
