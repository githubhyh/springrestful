package com.dm.pattern.adapter;

public class MediaPlayer implements IMediaPlayer {
    @Override
    public void play() {
        System.out.println("media player is playing music");
    }
}
