import java.util.ArrayList;
import java.util.LinkedList;

public class Album {

    private String name;
    private String artist;

    private SongList songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new SongList();
    }

    public static class SongList {
        private ArrayList<Song> songs;

        private SongList() {
            songs = new ArrayList<>();
        }

        private boolean add(Song song) {
            if (songs.contains(song)) {
                return false;
            }
            songs.add(song);

            return true;
        }

        private Song findSong(String title) {
            for (Song song : songs) {
                if (song.getTitle().equals(title)) {
                    return song;
                }
            }

            return null;
        }

        private Song findSong(int trackNumber) {

            int index = trackNumber - 1;
            if ((index > 0) && (index < songs.size())) {
                return songs.get(index);
            }
            return null;
        }
    }

    public boolean addSong(String title, double duration) {


        if (songs.findSong(title) == null) {
            songs.add(new Song(title, duration));
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
        if ((index >= 0) && (index <= songs.songs.size())) {
            playList.add(songs.songs.get(index));
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