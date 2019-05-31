package moe.perci.pie;

import moe.perci.pie.pyapi.MyOnlineSpeaker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PieApplication {

    public static void main(String[] args) {
        SpringApplication.run(PieApplication.class, args);

        try {
            init();
        } catch (Exception e) {

        }
    }

    public static void init() {
        MyOnlineSpeaker.myOnlineSpeaker.init();
    }
}
