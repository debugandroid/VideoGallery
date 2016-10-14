package com.debugandroid.VideoGallery;

/**
 * Created by Pawan on 4/8/2016.
 */
public class VideoItem {
    String _ID;
    String ARTIST;
    String TITLE;
    String DATA;
    String DISPLAY_NAME;
    String DURATION;
    public VideoItem(String _ID, String ARTIST, String TITLE,
                     String DATA, String DISPLAY_NAME, String DURATION){

        this._ID=_ID;
        this.ARTIST=ARTIST;
        this.TITLE=TITLE;
        this.DATA=DATA;
        this.DISPLAY_NAME=DISPLAY_NAME;
        this.DURATION=DURATION;
    };
    public VideoItem(){

    }
    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String getARTIST() {
        return ARTIST;
    }

    public void setARTIST(String ARTIST) {
        this.ARTIST = ARTIST;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    public String getDISPLAY_NAME() {
        return DISPLAY_NAME;
    }

    public void setDISPLAY_NAME(String DISPLAY_NAME) {
        this.DISPLAY_NAME = DISPLAY_NAME;
    }

    public String getDURATION() {
        return DURATION;
    }

    public void setDURATION(String DURATION) {
        this.DURATION = DURATION;
    }


}
