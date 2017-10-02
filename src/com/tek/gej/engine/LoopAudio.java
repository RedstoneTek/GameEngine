package com.tek.gej.engine;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class LoopAudio implements Runnable{
	
	String audioFilePath;
	AudioPlayer lineListener;
	
	public LoopAudio(String audioFilePath, AudioPlayer lineListener) {
		this.audioFilePath = audioFilePath;
		this.lineListener = lineListener;
	}
	
	public void run() {
        File audioFile = new File(audioFilePath);
        
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
 
            AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(Clip.class, format);
 
            lineListener.audioClip = (Clip) AudioSystem.getLine(info);
 
            lineListener.audioClip.addLineListener(lineListener);
 
            lineListener.audioClip.open(audioStream);
             
            lineListener.audioClip.start();
             
            while (!lineListener.playCompleted) {
                // wait for the playback completes
                try {
                    Thread.sleep(1000);
                    if(!lineListener.audioClip.isRunning()) {
                    	break;
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
             
            lineListener.audioClip.close();
            
            run();
             
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
	}
	
}
