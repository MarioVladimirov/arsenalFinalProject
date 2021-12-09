package com.example.arsenalfinalproject.service.schedules;
import com.example.arsenalfinalproject.service.StatsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;





@Component
public class ResetStatsAfterOneMonth {


    public final StatsService statsService;


    public ResetStatsAfterOneMonth(StatsService statsService) {
        this.statsService = statsService;
    }

    @Scheduled(cron= "0 0 1 * * SUN")
    public void resetStatsOneMonth() {

       statsService.resetHistory();

    }


}
