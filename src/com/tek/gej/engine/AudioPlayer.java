package com.tek.gej.engine;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * This is an example class that demonstrates how to play back an audio file
 * using the Clip in Java Sound API, slightly edited by RedstoneTek
 * @author www.codejava.net
 */
public class AudioPlayer implements LineListener{
	
    /**
     * this flag indicates whether the playback completes or not.
     */
    public boolean playCompleted;
    
    Clip audioClip;
    
    Thread t;
     
    /**
     * Play a given audio file.
     * @param audioFilePath Path of the audio file.
     */
    public void play(String audioFilePath) {
        t = new Thread(new PlayAudio(audioFilePath, this));
        t.start();
    }
    
    public void loop(String audioFilePath) {
    	t = new Thread(new LoopAudio(audioFilePath, this));
    	t.start();
    }
    
    @SuppressWarnings("deprecation")
	public void stop() {
    	audioClip.stop();
    	audioClip.close();
    	t.stop();
    }
     
    @Override
    public void update(LineEvent event) {
    	
    }
	
}
