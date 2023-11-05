package data;

public class Attribute {
    private String _attribute_id;
    private String _route_id;
    private String _user_id;
    private Enum _strength;
    private Enum _condition;
    private Enum _scenery;
    private Enum _experience;

    public Attribute(String _attribute_id, String _route_id, String _user_id, Enum _strength, Enum _condition, Enum _scenery, Enum _experience) {
        this._attribute_id = _attribute_id;
        this._route_id = _route_id;
        this._user_id = _user_id;
        this._strength = _strength;
        this._condition = _condition;
        this._scenery = _scenery;
        this._experience = _experience;
    }
    public String get_attribute_id() {
        return _attribute_id;
    }

    public void set_attribute_id(String _attribute_id) {
        this._attribute_id = _attribute_id;
    }

    public String get_route_id() {
        return _route_id;
    }

    public void set_route_id(String _route_id) {
        this._route_id = _route_id;
    }

    public String get_user_id() {
        return _user_id;
    }

    public void set_user_id(String _user_id) {
        this._user_id = _user_id;
    }

    public Enum get_strength() {
        return _strength;
    }

    public void set_strength(Enum _strength) {
        this._strength = _strength;
    }

    public Enum get_condition() {
        return _condition;
    }

    public void set_condition(Enum _condition) {
        this._condition = _condition;
    }

    public Enum get_scenery() {
        return _scenery;
    }

    public void set_scenery(Enum _scenery) {
        this._scenery = _scenery;
    }

    public Enum get_experience() {
        return _experience;
    }

    public void set_experience(Enum _experience) {
        this._experience = _experience;
    }
}
