package com.dm.pattern.adapter;

public class AudioPlayer implements IMediaPlayer {

    private MediaPlayerAdapter mediaPlayerAdapter;

    public AudioPlayer(MediaPlayerAdapter mediaPlayerAdapter) {
        this.mediaPlayerAdapter = mediaPlayerAdapter;
    }

    @Override
    public void play() {
        mediaPlayerAdapter.play();
    }
}
