package com.dm.pattern.adapter;

public class Test {
    public static void main(String[] args) {
        MediaPlayerAdapter playerAdapter = new MediaPlayerAdapter(new MP4Player());
        AudioPlayer audioPlayer = new AudioPlayer(playerAdapter);
        audioPlayer.play();
    }
}
