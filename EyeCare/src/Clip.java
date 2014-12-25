import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;


public class Clip {
	
/*	URL url;
	AudioClip clip;*/
	
	
	public void play(String resource){
		URL url = Clip.class.getResource(resource);
		AudioClip clip = Applet.newAudioClip(url);
		clip.play();
	}
	

	
}
