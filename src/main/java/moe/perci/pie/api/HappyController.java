package moe.perci.pie.api;

import moe.perci.pie.config.MetaConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class HappyController {
    @GetMapping("video")
    public HashMap<String, Object> getVideos(HttpServletRequest request, HttpSession httpSession) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "-1");
        result.put("msg", "");
        result.put("data", "");

        try {
            int pageSize    = Integer.parseInt(request.getParameter("pageSize"));
            int pageNumber  = Integer.parseInt(request.getParameter("pageNumber"));
            String subClassName = request.getParameter("class");

            File file = new File(MetaConfig.metaConfig.metas.get("baseVideoSrc") + subClassName);
            File[] files = file.listFiles();

            List<String> vs = new ArrayList<>();
            for (int i=0; i<files.length; i++) {
                if (files[i].isFile()) {
                    vs.add(files[i].getName());
                }
            }
            System.out.println(vs.size());
            HashMap<String, Object> data = new HashMap<>();
            data.put("totalPages", (vs.size()%pageSize) ==0 ? vs.size()/pageSize : vs.size()/pageSize + 1);

            List<String> myvs = new ArrayList<>();
            int iBegin = pageSize*(pageNumber-1);
            int iEnd   = iBegin + pageSize;
            for (int i=iBegin; i<vs.size() && i<iEnd; i++) {
                myvs.add(vs.get(i));
            }

            data.put("videos", myvs);
            result.put("data", data);

            result.put("code", 1);
            result.put("msg", "OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
