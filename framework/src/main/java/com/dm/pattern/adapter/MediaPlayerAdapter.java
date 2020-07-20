package com.dm.pattern.adapter;

/**
 * @author hu.yuhao
 * 通过依赖实现适配
 * */
public class MediaPlayerAdapter implements IMediaPlayer {
    private IAdvanceMediaPlayer advanceMediaPlayer;

    public MediaPlayerAdapter(IAdvanceMediaPlayer advanceMediaPlayer) {
        this.advanceMediaPlayer = advanceMediaPlayer;
    }


    @Override
    public void play() {
        advanceMediaPlayer.playMP4();
    }
}
