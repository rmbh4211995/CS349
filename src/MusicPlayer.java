import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {
      
    private AudioInputStream   stream;   
    private Clip          clip;         
	
	public MusicPlayer(){
    
	    try {
	        File file = new File("C:/Users/Jochs01/workspace/BoxingGame/src/Clubs.wav");
	        stream = AudioSystem.getAudioInputStream(file);
	        clip = AudioSystem.getClip();
	        clip.open(stream);
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void play(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
}
