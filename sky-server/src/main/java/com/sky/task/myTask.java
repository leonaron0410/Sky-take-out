package com.sky.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
public class myTask {

    // @Scheduled(cron = "0/5 * * * * ? ")
    public void test() {
        log.info("定时任务执行了");
    }
}
