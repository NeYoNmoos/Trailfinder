package data;

public class TimeOfYear {
    private String _month_id;
    private String _route_id;
    private Boolean _january;
    private Boolean _february;
    private Boolean _march;
    private Boolean _april;
    private Boolean _may;
    private Boolean _june;
    private Boolean _july;
    private Boolean _august;
    private Boolean _september;
    private Boolean _oktober;
    private Boolean _november;
    private Boolean _december;

    public TimeOfYear(String _month_id, String _route_id, Boolean _january, Boolean _february, Boolean _march, Boolean _april, Boolean _may, Boolean _june, Boolean _july, Boolean _august, Boolean _september, Boolean _oktober, Boolean _november, Boolean _december) {
        this._month_id = _month_id;
        this._route_id = _route_id;
        this._january = _january;
        this._february = _february;
        this._march = _march;
        this._april = _april;
        this._may = _may;
        this._june = _june;
        this._july = _july;
        this._august = _august;
        this._september = _september;
        this._oktober = _oktober;
        this._november = _november;
        this._december = _december;
    }

    public String get_month_id() {
        return _month_id;
    }

    public void set_month_id(String _month_id) {
        this._month_id = _month_id;
    }

    public String get_route_id() {
        return _route_id;
    }

    public void set_route_id(String _route_id) {
        this._route_id = _route_id;
    }

    public Boolean get_january() {
        return _january;
    }

    public void set_january(Boolean _january) {
        this._january = _january;
    }

    public Boolean get_february() {
        return _february;
    }

    public void set_february(Boolean _february) {
        this._february = _february;
    }

    public Boolean get_march() {
        return _march;
    }

    public void set_march(Boolean _march) {
        this._march = _march;
    }

    public Boolean get_april() {
        return _april;
    }

    public void set_april(Boolean _april) {
        this._april = _april;
    }

    public Boolean get_may() {
        return _may;
    }

    public void set_may(Boolean _may) {
        this._may = _may;
    }

    public Boolean get_june() {
        return _june;
    }

    public void set_june(Boolean _june) {
        this._june = _june;
    }

    public Boolean get_july() {
        return _july;
    }

    public void set_july(Boolean _july) {
        this._july = _july;
    }

    public Boolean get_august() {
        return _august;
    }

    public void set_august(Boolean _august) {
        this._august = _august;
    }

    public Boolean get_september() {
        return _september;
    }

    public void set_september(Boolean _september) {
        this._september = _september;
    }

    public Boolean get_oktober() {
        return _oktober;
    }

    public void set_oktober(Boolean _oktober) {
        this._oktober = _oktober;
    }

    public Boolean get_november() {
        return _november;
    }

    public void set_november(Boolean _november) {
        this._november = _november;
    }

    public Boolean get_december() {
        return _december;
    }

    public void set_december(Boolean _december) {
        this._december = _december;
    }
}
