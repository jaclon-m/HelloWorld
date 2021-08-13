package com.jaclon.mistakesOfBuz.productionready;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/8/12 14:36
 */
@Component
@Slf4j
public class UserServiceHealthIndicator implements HealthIndicator {

    @Resource
    private RestTemplate restTemplate;
    /**
     * Return an indication of health.
     *
     * @return the health for
     */
    @Override
    public Health health() {
        long begin = System.currentTimeMillis();
        long userId = 1L;
        User  user = null;
        try {
            user = restTemplate.getForObject("http://localhost:45678/user?userId=" + userId,User.class);
            if(user != null && user.getUserId() == userId){
                return Health.up()
                        .withDetail("user",user)
                        .withDetail("took",System.currentTimeMillis() - begin)
                        .build();
            }else {
                return Health.down().withDetail("took",System.currentTimeMillis() - begin).build();
            }
        }catch (Exception e){
            log.warn("health check failed",e);
            return Health.down().withDetail("took",System.currentTimeMillis() - begin).build();
        }
    }
}
