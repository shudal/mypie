package moe.perci.pie.pyapi;

import moe.perci.pie.config.MetaConfig;
import moe.perci.pie.model.service.SayingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MyOnlineSpeaker {
    public static MyOnlineSpeaker myOnlineSpeaker;

    @Autowired
    SayingService sayingService;

    @PostConstruct
    public void init() {
        myOnlineSpeaker = this;
        myOnlineSpeaker.sayingService = this.sayingService;
    }

    public void say(int sayingId) throws Exception{
        String theCommand = "sudo python3 " + MetaConfig.metaConfig.metas.get("basePythonDir") + "SaySometing.py " + sayingId;
        System.out.println(theCommand);
        Process process = Runtime.getRuntime().exec(theCommand);
        process.waitFor();
    }
}
