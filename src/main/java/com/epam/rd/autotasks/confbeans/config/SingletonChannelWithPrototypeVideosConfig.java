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
public class SingletonChannelWithPrototypeVideosConfig {
    @Value("${configRes.nameVideo1}")
    private String nameVideo1;
    @Value("${configRes.nameVideo2}")
    private String nameVideo2;
    @Value("${configRes.nameVideo3}")
    private String nameVideo3;

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Video video1(){
        return new Video(nameVideo1, LocalDateTime.of(2020, 10, 10, 10, 10));
    }
    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Video video2(){
        return new Video(nameVideo2, LocalDateTime.of(2020, 10, 10, 10, 11));
    }
    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Video video3(){
        return new Video(nameVideo3, LocalDateTime.of(2020, 10, 10, 10, 12));
    }

    @Bean
    public Channel channel(){
        var channel = new Channel();
        channel.addVideo(video1());
        channel.addVideo(video2());
        channel.addVideo(video3());
        return channel;
    }
}
