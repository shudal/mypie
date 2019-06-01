package moe.perci.pie;

import moe.perci.pie.config.MetaConfig;
import moe.perci.pie.pyapi.MyOnlineSpeaker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PieApplication {

    public static void main(String[] args) {
        SpringApplication.run(PieApplication.class, args);

        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        MetaConfig.metaConfig.init();
        MyOnlineSpeaker.myOnlineSpeaker.init();
    }
}
