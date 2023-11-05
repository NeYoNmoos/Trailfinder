package data;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import com.google.protobuf.Enum;

public class User {
    private String _id;
    private String _fname;
    private String _lname;
    private String _email;
    private String _password;
    private String _phone;
    private String _username;
    private Enum _usertype;

    public User(String _id, String _fname, String _lname, String _email, String _password, String _phone, String _username, Enum _usertype) {
        this._id = _id;
        this._fname = _fname;
        this._lname = _lname;
        this._email = _email;
        this._password = _password;
        this._phone = _phone;
        this._username = _username;
        this._usertype = _usertype;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_fname() {
        return _fname;
    }

    public void set_fname(String _fname) {
        this._fname = _fname;
    }

    public String get_lname() {
        return _lname;
    }

    public void set_lname(String _lname) {
        this._lname = _lname;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }
}
