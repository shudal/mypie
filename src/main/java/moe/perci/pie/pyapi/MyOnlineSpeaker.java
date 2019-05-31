package moe.perci.pie.pyapi;

import moe.perci.pie.config.PythonConfig;
import moe.perci.pie.model.Saying;
import moe.perci.pie.model.service.SayingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;

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
        String theCommand = "sudo python3 " + PythonConfig.basePythonDir + "SaySometing.py " + sayingId;
        System.out.println(theCommand);
        Process process = Runtime.getRuntime().exec(theCommand);
        process.waitFor();
    }
}
