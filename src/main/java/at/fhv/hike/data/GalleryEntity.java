package at.fhv.hike.data;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name = "gallery", schema = "trailfinder_dev", catalog = "Trailfinder")
public class GalleryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "picture_id")
    private String pictureId;
    @Basic
    @Column(name = "route_id")
    private String routeId;
    @Basic
    @Column(name = "picture")
    private byte[] picture;

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GalleryEntity that = (GalleryEntity) o;

        if (pictureId != null ? !pictureId.equals(that.pictureId) : that.pictureId != null) return false;
        if (routeId != null ? !routeId.equals(that.routeId) : that.routeId != null) return false;
        if (!Arrays.equals(picture, that.picture)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pictureId != null ? pictureId.hashCode() : 0;
        result = 31 * result + (routeId != null ? routeId.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(picture);
        return result;
    }
}
