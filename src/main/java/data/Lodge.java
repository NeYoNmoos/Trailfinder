package data;

public class Lodge {
    private String _lodge_id;
    private String _route_id;
    private String _coordinate_id;
    private String _description;

    public Lodge(String _lodge_id, String _route_id, String _coordinate_id, String _description) {
        this._lodge_id = _lodge_id;
        this._route_id = _route_id;
        this._coordinate_id = _coordinate_id;
        this._description = _description;
    }

    public String get_lodge_id() {
        return _lodge_id;
    }

    public void set_lodge_id(String _lodge_id) {
        this._lodge_id = _lodge_id;
    }

    public String get_route_id() {
        return _route_id;
    }

    public void set_route_id(String _route_id) {
        this._route_id = _route_id;
    }

    public String get_coordinate_id() {
        return _coordinate_id;
    }

    public void set_coordinate_id(String _coordinate_id) {
        this._coordinate_id = _coordinate_id;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }
}
