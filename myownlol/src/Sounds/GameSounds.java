package Sounds;

import javafx.scene.media.AudioClip;

public class GameSounds {
	
	private static AudioClip hurt = new AudioClip(GameSounds.class.getResource(
			"/media/herderHurt.mp3").toString());
	private static AudioClip baa = new AudioClip(GameSounds.class.getResource(
			"/media/baa.mp3").toString());
	private static AudioClip theme = new AudioClip(GameSounds.class
			.getResource("/media/SheepHerdder.mp3").toString());

	public static void playBaa() {
		baa.play();
	}

	public static void stopBaa() {
		baa.stop();
	}

	public static void playTheme() {
		theme.play();
	}

	public static void stopTheme() {
		theme.stop();
	}

	public static void playHurt() {
		hurt.play();
	}

	public static void stopHurt() {
		hurt.stop();
	}
}
