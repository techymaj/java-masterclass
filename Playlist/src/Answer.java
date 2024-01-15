import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
    // write code here
    private String name;
    private String artist;
    private ArrayList<Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<Song>();
    }

    public boolean addSong(String songTitle, double duration) {
        // if song added

        if (findSong(songTitle) == null) {
            songs.add(new Song(songTitle, duration));
            return true;
        }

        return false;
    }

    private Song findSong(String songTitle) {
        // if song exists
        for (var track : songs) {
            if (track.getTitle().equals(songTitle)) {
                return track;
            }
        }

        return null; // if not
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playlist) {
        // if it exists and was added successfuly, true
        int index = trackNumber - 1;

        if ((index >= 0) && (index <= songs.size())) {
            playlist.add(songs.get(index));
            return true;
        }

        return false;
    }

    public boolean addToPlayList(String songTitle, LinkedList<Song> playlist) {
        // if it exists and was added successfuly, true
        var track = findSong(songTitle);
        if (track != null) {
            playlist.add(track);
            return true;
        }

        return false;
    }
}