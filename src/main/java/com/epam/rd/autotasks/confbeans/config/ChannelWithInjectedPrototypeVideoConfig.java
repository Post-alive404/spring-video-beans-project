package com.epam.rd.autotasks.confbeans.config;

import com.epam.rd.autotasks.confbeans.video.Channel;
import com.epam.rd.autotasks.confbeans.video.Video;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Denys Parshutkin
 * @version 1.0.0
 */
@Configuration
@PropertySource("classpath:configResources.properties")
public class ChannelWithInjectedPrototypeVideoConfig {
    @Value("${configRes.channelRelease}")
    private String channelRelease;
    private LocalDateTime dateRelease = LocalDateTime.of(2010, 10, 1, 10, 0);


    @Bean
    @Scope(scopeName = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Channel channel(){
        Channel channel = new Channel();
        for (int i = 0; i < 2; i++) {
            channel.addVideo(video());
            channel.addVideo(otherVideo());
            channel.addVideo(anotherOtherVideo());
        }
        channel.addVideo(video());
        return channel;
    }


    @Bean
    @Primary
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Video video() {
        return getChannelVideo();
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Video otherVideo() {
        return getChannelVideo();
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Video anotherOtherVideo() {
        return getChannelVideo();
    }

    private Video getChannelVideo() {
        String nameMovie = channelRelease;
        Video video = new Video(nameMovie, dateRelease);
        int periodReleaseDay = 1;
        dateRelease = dateRelease.plusDays(periodReleaseDay);
        return video;
    }

}
