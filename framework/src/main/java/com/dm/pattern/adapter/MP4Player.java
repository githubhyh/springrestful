package com.dm.pattern.adapter;

public class MP4Player implements IAdvanceMediaPlayer {
    @Override
    public void playMP4() {
        System.out.println("mp4 player is playing music");
    }

    @Override
    public void playVLC() {

    }
}
