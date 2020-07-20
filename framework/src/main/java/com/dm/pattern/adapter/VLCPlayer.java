package com.dm.pattern.adapter;

public class VLCPlayer implements IAdvanceMediaPlayer {
    @Override
    public void playMP4() {

    }

    @Override
    public void playVLC() {
        System.out.println("VLC player is playing music");
    }
}
