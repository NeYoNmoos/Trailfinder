package data;

public class Coordinate {
    private String _coordinates_id;
    private String _route;
    private double _sequence;
    private double _longitude;
    private double _latitude;

    public Coordinate(String _coordinates_id, String _route, double _sequence, double _longitude, double _latitude) {
        this._coordinates_id = _coordinates_id;
        this._route = _route;
        this._sequence = _sequence;
        this._longitude = _longitude;
        this._latitude = _latitude;
    }

    public String get_coordinates_id() {
        return _coordinates_id;
    }

    public void set_coordinates_id(String _coordinates_id) {
        this._coordinates_id = _coordinates_id;
    }

    public String get_route() {
        return _route;
    }

    public void set_route(String _route) {
        this._route = _route;
    }

    public double get_sequence() {
        return _sequence;
    }

    public void set_sequence(double _sequence) {
        this._sequence = _sequence;
    }

    public double get_latitude() {
        return _latitude;
    }

    public void set_latitude(double _latitude) {
        this._latitude = _latitude;
    }

    public double get_longitude() {
        return _longitude;
    }

    public void set_longitude(double _longitude) {
        this._longitude = _longitude;
    }



}
