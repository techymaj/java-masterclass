import java.util.ArrayList;
import java.util.LinkedList;

public class Album {

    private String name;
    private String artist;

    private SongList songs;

    public Album(String name, String artist, SongList songs) {
        this.name = name;
        this.artist = artist;
        this.songs = new SongList();
    }

    public static class SongList {
        private ArrayList<Song> songs1;

        private SongList() {
            this.songs1 = new ArrayList<Song>();
        }

        private boolean add(Song song) {
            if (songs1.contains(song)) {
                return false;
            }
            songs1.add(song);

            return true;
        }

        private Song findSong(String title) {
            for (Song song : songs1) {
                if (song.getTitle().equals(title)) {
                    return song;
                }
            }

            return null;
        }

        private Song findSong(int trackNumber) {

            int index = trackNumber - 1;
            if ((index > 0) && (index < songs1.size())) {
                return songs1.get(index);
            }
            return null;
        }
    }

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    public boolean addSong(String title, double duration) {


        if (songs.findSong(title) == null) {
            songs.songs1.add(new Song(title, duration));
            return true;
        }
        return false;
    }

    // private Song findSong(String title) {

    //     for (Song checkedSong : songs.songList) {
    //         if (checkedSong.getTitle().equals(title)) {
    //             return checkedSong;
    //         }
    //     }
    //     return null;
    // }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {

        int index = trackNumber - 1;
        if ((index >= 0) && (index <= songs.songs1.size())) {
            playList.add(songs.songs1.get(index));
            return true;
        }
        return false;
    }


    public boolean addToPlayList(String title, LinkedList<Song> playList) {

        Song checkedSong = songs.findSong(title);
        if (checkedSong != null) {
            playList.add(checkedSong);
            return true;
        }
        return false;
    }
}