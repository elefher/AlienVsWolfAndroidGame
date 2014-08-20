package com.kilobolt.framework;

import android.app.Activity;

import com.elefher.wolfhuntergame.CreatePlayer;

public interface Game {

    public Audio getAudio();

    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getInitScreen();
}
