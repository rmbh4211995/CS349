import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class MusicPlayer {
      
    private AudioInputStream	stream, stream2;
    static AudioInputStream     stream3, stream4;
    private AudioFormat         format;
    private DataLine.Info       info;
    private Clip				clip, clip2; 
    static Clip                 clip3, clip4;         
	
	public MusicPlayer(){
    
	    try {
	        
	    	stream = AudioSystem.getAudioInputStream(this.getClass().getResource("Clubs.wav"));
	    	format = stream.getFormat();
	    	info = new DataLine.Info(Clip.class, format);
	        clip = (Clip) AudioSystem.getLine(info);
	        clip.open(stream);
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void play(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void playHit()
	{
	  try {
      stream2 = AudioSystem.getAudioInputStream(this.getClass().getResource("jab.wav"));
      clip2 = AudioSystem.getClip();
      clip2.open(stream2);
  }
  catch (Exception e) {
      e.printStackTrace();
  }
	  clip2.loop(0);
	}
	public void playWin()
  {
    try {
      stream3 = AudioSystem.getAudioInputStream(this.getClass().getResource("grunt.wav"));
      clip3 = AudioSystem.getClip();
      clip3.open(stream3);
      stream4 = AudioSystem.getAudioInputStream(this.getClass().getResource("applause.wav"));
      clip4 = AudioSystem.getClip();
      clip4.open(stream4);
  }
  catch (Exception e) {
      e.printStackTrace();
  }
    clip3.loop(0);
    clip4.loop(0);
  }
	
}
