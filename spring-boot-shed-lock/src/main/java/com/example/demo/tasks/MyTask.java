package com.example.demo.tasks;

import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyTask {

    private Integer valueConcurrency = 0;

    @Scheduled(fixedRate = 50)
    @SchedulerLock(name = "something", lockAtMostFor = "1m", lockAtLeastFor = "30s")
    public void doSomething() {
        System.out.println("task doSomething started");
        LockAssert.assertLocked();

        valueConcurrency += 1;

        System.out.println("value: " + valueConcurrency);
        System.out.println("task doSomething finished");
        System.out.println("");
        System.out.println();
    }
}
