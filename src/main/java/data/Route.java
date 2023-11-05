package data;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class Route {
    private String _route_id;
    private String _author;
    private String _name;
    private double _length;
    private double _altitude;
    private String _location;
    private String _description;
    private double _duration;

    public Route(String id, String author, String name, double length, double altitude, String location, String description, double duration) {
        _route_id = id;
        _author = author;
        _name = name;
        _length = length;
        _altitude = altitude;
        _location = location;
        _description = description;
        _duration = duration;
    }

    public String getId() {
        return _route_id;
    }

    public void setId(String id) {
        _route_id = id;
    }

    public String getAuthor() {
        return _author;
    }

    public void setAuthor(String author) {
        _author = author;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public double getLength() {
        return _length;
    }

    public void setLength(double length) {
        _length = length;
    }

    public double getAltitude() {
        return _altitude;
    }

    public void setAltitude(double altitude) {
        _altitude = altitude;
    }

    public String getLocation() {
        return _location;
    }

    public void setLocation(String location) {
        _location = location;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public double getDuration() {
        return _duration;
    }

    public void setDuration(double duration) {
        _duration = duration;
    }
}