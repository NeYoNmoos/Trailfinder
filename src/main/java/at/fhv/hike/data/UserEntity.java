package at.fhv.hike.data;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user", schema = "trailfinder_dev", catalog = "ftb_inv_2023_vz_3_a")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "user_admin")
    private Boolean userType;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> comments = new ArrayList<>();

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void removeComment(CommentEntity comment) {
        comments.remove(comment);
        comment.setRoute(null);
        comment.setAuthor(null);
    }

    public UserEntity(){
        routes=new ArrayList<>();
    }
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RouteEntity> routes = new ArrayList<>();

    public List<RouteEntity> getRoutes() {
        return routes;
    }

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "favourite_route",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "route_id")})
    private List<RouteEntity> favorite_routes;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "done_route",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "route_id")})
    private List<RouteEntity> done_routes;

    public void removeRoute(RouteEntity route) {
        routes.remove(route);
        route.setActive(false);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getUserType() {
        return userType;
    }

    public void setUserType(Boolean userType) {
        this.userType = userType;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    public void setRoutes(List<RouteEntity> routes) {
        this.routes = routes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (userType != null ? !userType.equals(that.userType) : that.userType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        return result;
    }

    public List<RouteEntity> getFavorite_routes() {
        return favorite_routes;
    }

    public void setFavorite_routes(List<RouteEntity> favorite_routes) {
        this.favorite_routes = favorite_routes;
    }

    public List<RouteEntity> getDone_routes() {
        return done_routes;
    }

    public void setDone_routes(List<RouteEntity> done_routes) {
        this.done_routes = done_routes;
    }


}
