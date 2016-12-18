package com.wizered67.game;

import com.badlogic.gdx.audio.Music;

/**
 * Used to set the current music to be played to ensure that only one is played at a time.
 * Also allows pausing the music and resuming it later.
 * @author Adam Victor
 */
public class MusicManager {
    /** The Music object currently being played. */
    private Music currentMusic;
    /** The name of the music currently being played. Used to tell if music has changed. */
    private String currentMusicName;
    /** Whether the music being played has been paused. */
    private boolean paused;
    /** Initialize MusicManager with no music being played. */
    public MusicManager() {
        currentMusic = null;
        currentMusicName = "";
        paused = false;
    }

    /** Plays the Music object MUSIC named NAME. If it was already playing but paused, resume it.
     * If different music was playing before, stop it. Iff LOOPS, the music will continue to loop.
     */
    public void playMusic(Music music, String name, boolean loops) {
        if (currentMusicName.equals(name)) {
            if (paused) {
                resumeMusic();
            }
            return;
        }
        paused = false;
        stopMusic();
        currentMusic = music;
        currentMusicName = name;
        music.play();
        setLooping(loops);
    }
    /** Stops the music currently being played and resets currentMusic and currentMusicName. */
    public void stopMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusicName = "";
            currentMusic = null;
        }
    }
    /** Resume the music currently being played. */
    public void resumeMusic() {
        if (currentMusic != null) {
            currentMusic.play();
        }
    }
    /** Pause the music currently being played. */
    public void pauseMusic() {
        if (currentMusic != null) {
            currentMusic.pause();
            paused = true;
        }
    }
    /** Sets whether the music should loop. */
    public void setLooping(boolean loop) {
        if (currentMusic != null) {
            currentMusic.setLooping(loop);
        }
    }
    /** Sets the volume of the music playing. */
    public void setVolume(float volume) {
        if (currentMusic != null) {
            currentMusic.setVolume(volume);
        }
    }
    /** Returns the name of the music playing. */
    public String getCurrentMusicName() {
        return currentMusicName;
    }
    /** Returns a Music object for the music playing. */
    public Music getCurrentMusic() {
        return currentMusic;
    }

}
