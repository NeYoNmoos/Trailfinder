package data;

import java.util.Date;

public class Comment {


    private String _comment_id;
    private String _route_id;
    private String _author_id;
    private String _comment;
    private String _attributes_id;
    private Date _date;

    public Comment(String _comment_id, String _route_id, String _author_id, String _comment, String _attributes_id, Date _date) {
        this._comment_id = _comment_id;
        this._route_id = _route_id;
        this._author_id = _author_id;
        this._comment = _comment;
        this._attributes_id = _attributes_id;
        this._date = _date;
    }

    public String get_comment_id() {
        return _comment_id;
    }

    public void set_comment_id(String _comment_id) {
        this._comment_id = _comment_id;
    }

    public String get_route_id() {
        return _route_id;
    }

    public void set_route_id(String _route_id) {
        this._route_id = _route_id;
    }

    public String get_author_id() {
        return _author_id;
    }

    public void set_author_id(String _author_id) {
        this._author_id = _author_id;
    }

    public String get_comment() {
        return _comment;
    }

    public void set_comment(String _comment) {
        this._comment = _comment;
    }

    public String get_attributes_id() {
        return _attributes_id;
    }

    public void set_attributes_id(String _attributes_id) {
        this._attributes_id = _attributes_id;
    }

    public Date get_date() {
        return _date;
    }

    public void set_date(Date _date) {
        this._date = _date;
    }
}
