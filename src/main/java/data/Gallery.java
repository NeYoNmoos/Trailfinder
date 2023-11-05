package data;

import java.sql.Blob;

public class Gallery {

    private String _picture_id;
    private String _route_id;
    private Blob picture;

    public Gallery(String _picture_id, String _route_id, Blob picture) {
        this._picture_id = _picture_id;
        this._route_id = _route_id;
        this.picture = picture;
    }

    public String get_picture_id() {
        return _picture_id;
    }

    public void set_picture_id(String _picture_id) {
        this._picture_id = _picture_id;
    }

    public String get_route_id() {
        return _route_id;
    }

    public void set_route_id(String _route_id) {
        this._route_id = _route_id;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }
}
