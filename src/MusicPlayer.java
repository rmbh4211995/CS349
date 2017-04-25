import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class MusicPlayer {

    private AudioInputStream stream, stream2;
    private AudioFormat format, format2;
    private DataLine.Info info, info2;
    private Clip clip, clip2;
    public MusicPlayer() {

        try {

            stream = AudioSystem.getAudioInputStream(
                    this.getClass().getResource("Clubs.wav"));
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playHit() {
        try {
            stream = AudioSystem.getAudioInputStream(
                    this.getClass().getResource("jab.wav"));
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        clip.loop(0);
    }

    public void playWin() {
        try {
            stream = AudioSystem.getAudioInputStream(
                    this.getClass().getResource("grunt.wav"));
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            stream2 = AudioSystem.getAudioInputStream(
                    this.getClass().getResource("applause.wav"));
            format2 = stream2.getFormat();
            info2 = new DataLine.Info(Clip.class, format2);
            clip2 = (Clip) AudioSystem.getLine(info2);
            clip2.open(stream2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        clip.loop(0);
        clip2.loop(0);
    }

}
