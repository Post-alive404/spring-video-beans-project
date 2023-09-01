package com.epam.rd.autotasks.confbeans.video;

import java.time.LocalDateTime;

/**
 * @author Denys Parshutkin
 * @version 1.0.0
 */
public class VideoStudioImpl implements VideoStudio{
    private final String nameFranchise;
    private LocalDateTime dateRelease;
    private final int periodReleaseYear;
    private int part = 1;

    public VideoStudioImpl(String nameFranchise, LocalDateTime dateRelease, int periodReleaseYear) {
        this.nameFranchise = nameFranchise;
        this.dateRelease = dateRelease;
        this.periodReleaseYear = periodReleaseYear;
    }

    @Override
    public Video produce() {
        String nameMovie = nameFranchise + " " + part;
        Video video = new Video(nameMovie, dateRelease);
        part++;
        dateRelease = dateRelease.plusYears(periodReleaseYear);

        return video;
    }
}
