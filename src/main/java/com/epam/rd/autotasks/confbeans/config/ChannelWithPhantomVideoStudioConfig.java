package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

/**
 * @author Denys Parshutkin
 * @version 1.0.0
 */
@Configuration
@PropertySource("classpath:configResources.properties")
public class ChannelWithPhantomVideoStudioConfig {
    @Value("${configRes.franchise}")
    private String nameFranchise;
    private LocalDateTime dateRelease = LocalDateTime.of(2001, 10, 18, 10, 0);
    private int part = 1;


    @Bean
    public Channel channel(){
        Channel channel = new Channel();
        for (int i = 0; i < 8; i++) {
            channel.addVideo(franchiseVideo());
        }
        return channel;
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Video franchiseVideo(){
        return getFranchiseVideo();
    }

    private Video getFranchiseVideo() {
        int periodReleaseYear = 2;
        String nameMovie = nameFranchise + " " + part;
        Video video = new Video(nameMovie, dateRelease);
        part++;
        dateRelease = dateRelease.plusYears(periodReleaseYear);
        return video;
    }
}
