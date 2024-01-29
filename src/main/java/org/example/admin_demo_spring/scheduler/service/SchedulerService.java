package org.example.admin_demo_spring.scheduler.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchedulerService {



	@Scheduled(cron = "0 0 6 * * ?") // 매일 오전 6시에 실행
	@SchedulerLock(name = "TaskScheduler_scheduledTask",
		lockAtLeastFor = "PT5M", lockAtMostFor = "PT14M")
	public void AptiToTenPercentAptInfo() {
		System.out.println("****************************************************************************");
		System.out.println("실행");
		System.out.println("****************************************************************************");
		//xpVoiceTenPercentInfoService.aptITenPercentInfoRegister(new ArrayList<>());
	}
	// 매일 9시00분-9시55분, 18시00분-18시55분 사이에 1분 간격으로 실행
	//@Scheduled(cron = "0 0/1 9,18 * * *")
}
