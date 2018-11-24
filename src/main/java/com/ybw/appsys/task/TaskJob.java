package com.ybw.appsys.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author ybw
 * @createDate 2018/11/24
 * @desc spring 定时器
 **/
@Component
public class TaskJob {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 定时任务  @Scheduled 注解 fixedRate = 5000 多久执行一次
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("[" + Thread.currentThread().getName() + "]" + "现在时间：" + dateFormat.format(new Date()));
    }
}
