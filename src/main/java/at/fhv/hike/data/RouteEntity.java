package at.fhv.hike.data;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "route", schema = "trailfinder_dev", catalog = "Trailfinder")
public class RouteEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "route_id")
    private String routeId;
    @Basic
    @Column(name = "author")
    private String author;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "length")
    private Double length;
    @Basic
    @Column(name = "altitude")
    private Double altitude;
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "duration")
    private Double duration;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "attribute_id", referencedColumnName = "attribute_id")
    private AttributeEntity attributeEntity;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "month_id", referencedColumnName = "month_id")
    private TimeOfYearEntity timeOfYearEntity;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CoordinateEntity> coordinates = new ArrayList<>();

    public List<CoordinateEntity> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<CoordinateEntity> coordinates) {
        this.coordinates = coordinates;
    }

    public void addCoordinate(CoordinateEntity coordinate) {
        coordinates.add(coordinate);
        coordinate.setRoute(this);
    }

    public void removeCoordinate(CoordinateEntity coordinate) {
        coordinates.remove(coordinate);
        coordinate.setRoute(null);
    }

    public RouteEntity(){

    }

    public RouteEntity(String routeId, String author, String name, Double length, Double altitude, String location, String description, Double duration) {
        this.routeId = routeId;
        this.author = author;
        this.name = name;
        this.length = length;
        this.altitude = altitude;
        this.location = location;
        this.description = description;
        this.duration = duration;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDuration() {
        return duration;
    }


    public void setDuration(Double duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteEntity that = (RouteEntity) o;

        if (routeId != null ? !routeId.equals(that.routeId) : that.routeId != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (length != null ? !length.equals(that.length) : that.length != null) return false;
        if (altitude != null ? !altitude.equals(that.altitude) : that.altitude != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = routeId != null ? routeId.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (altitude != null ? altitude.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }

    public AttributeEntity getAttributeEntity() {
        return attributeEntity;
    }

    public void setAttributeEntity(AttributeEntity attributeEntity) {
        this.attributeEntity = attributeEntity;
    }

    public TimeOfYearEntity getTimeOfYearEntity() {
        return timeOfYearEntity;
    }

    public void setTimeOfYearEntity(TimeOfYearEntity timeOfYearEntity) {
        this.timeOfYearEntity = timeOfYearEntity;
    }
}
