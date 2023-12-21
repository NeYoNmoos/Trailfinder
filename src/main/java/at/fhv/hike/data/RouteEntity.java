package at.fhv.hike.data;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "route", schema = "trailfinder_dev", catalog = "ftb_inv_2023_vz_3_a")
public class RouteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "route_id")
    private Integer routeId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author", referencedColumnName = "user_id")
    private UserEntity author;
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

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Basic
    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Basic
    @Column(name = "duration")
    private Double duration;
    @Basic
    @Column(name="months")
    private Integer months;
    @Basic
    @Column(name="active")
    private Boolean active;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "attribute_id", referencedColumnName = "attribute_id")
    private AttributeEntity attributeEntity;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> comments = new ArrayList<>();
    @OneToMany(mappedBy = "routeEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<GalleryEntity> gallery = new ArrayList<>();

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

    public List<GalleryEntity> getGallery() {
        return gallery;
    }

    public void setGallery(List<GalleryEntity> gallery) {
        this.gallery = gallery;
    }

    public void addGallery(GalleryEntity _gallery) {
        gallery.add(_gallery);
        _gallery.setRoute(this);
    }

    public void removeGallery(GalleryEntity _gallery) {
        gallery.remove(_gallery);
        _gallery.setRoute(null);
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public void addComment(CommentEntity comment) {
        comments.add(comment);
        comment.setRoute(this);
    }

    public void removeComment(CommentEntity comment) {
        comments.remove(comment);
        comment.setRoute(null);
    }

    public RouteEntity(){
        this.comments = new ArrayList<>();
        this.gallery = new ArrayList<>();
        this.coordinates = new ArrayList<>();
    }

    public RouteEntity(Integer routeId, UserEntity author, String name, Double length, Double altitude, String location, String description, Double duration) {
        this.routeId = routeId;
        this.author = author;
        this.name = name;
        this.length = length;
        this.altitude = altitude;
        this.location = location;
        this.description = description;
        this.duration = duration;
    }

    public Integer getRouteId() {
        return routeId;
    }
    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
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

    public void setMonths(Integer months) { this.months = months; }

    public Integer getMonths() { return months; }

    public void setActive(Boolean active) { this.active = active; }

    public Boolean getActive() { return active; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteEntity that = (RouteEntity) o;

        if (routeId != null ? !routeId.equals(that.routeId) : that.routeId != null) return false;
      //  if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (length != null ? !length.equals(that.length) : that.length != null) return false;
        if (altitude != null ? !altitude.equals(that.altitude) : that.altitude != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (months != null ? !months.equals(that.months) : that.months != null) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = routeId != null ? routeId.hashCode() : 0;
        //result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (altitude != null ? altitude.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (months != null ? months.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        return result;
    }

    public AttributeEntity getAttributeEntity() {
        return attributeEntity;
    }

    public void setAttributeEntity(AttributeEntity attributeEntity) {
        this.attributeEntity = attributeEntity;
    }

}
