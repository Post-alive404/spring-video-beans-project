package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.VideoStudio;
import com.epam.rd.autotasks.confbeans.video.VideoStudioImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDateTime;

/**
 * @author Denys Parshutkin
 * @version 1.0.0
 */
@Configuration
@PropertySource("classpath:configResources.properties")
public class ChannelWithVideoStudioConfig {
    @Value("${configRes.franchise}")
    private String nameFranchise;

    @Bean
    public VideoStudio videoStudio(){
        return new VideoStudioImpl(nameFranchise, LocalDateTime.of(2001, 10, 18, 10, 0), 2);
    }

    @Bean
    public Channel channel(){
        Channel channel = new Channel();
        for (int i = 0; i < 8; i++) {
            channel.addVideo(videoStudio().produce());
        }
        return channel;
    }
}
