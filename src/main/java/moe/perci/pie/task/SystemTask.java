package moe.perci.pie.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SystemTask {
    // 每晚十一点关机
    @Scheduled(cron="0 0 23 * * *")
    public void shutdown() {
        try {
            String theCommand = "shutdown -h now";
            Process process = Runtime.getRuntime().exec(theCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
