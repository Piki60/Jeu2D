package jeu.main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound
{
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() 
    {
        soundURL[0] = this.getClass().getResource("res/sound/BlueBoyAdventure.wav");
        soundURL[1] = this.getClass().getResource("res/sound/coin.wav"            );
        soundURL[2] = this.getClass().getResource("res/sound/powerup.wav"         );
        soundURL[3] = this.getClass().getResource("res/sound/unlock.wav"          );
        soundURL[4] = this.getClass().getResource("res/sound/fanfare.wav"         );
        soundURL[5] = this.getClass().getResource("res/sound/BlueBoyAdventure.wav");
        soundURL[6] = this.getClass().getResource("res/sound/BlueBoyAdventure.wav");
    }
 
    public void setFile(int cpt)
    {
        try {
            
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[cpt]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {e.printStackTrace();}

    }

    public void play()
    {
        clip.start();
    }

    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void stop()
    {
        clip.stop();
    }
}
