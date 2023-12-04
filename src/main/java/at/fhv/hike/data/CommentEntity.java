package at.fhv.hike.data;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "comment", schema = "trailfinder_dev", catalog = "ftb_inv_2023_vz_3_a")
public class CommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "comment_id")
    private Integer commentId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(name = "route_id")
    private RouteEntity route;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private UserEntity author;
    @Basic
    @Column(name = "comment")
    private String comment;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "attribute_id", referencedColumnName = "attribute_id")
    private AttributeEntity attributeId;
    @Basic
    @Column(name = "date_comment")
    private Date dateComment;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public RouteEntity getRoute() {
        return route;
    }

    public void setRoute(RouteEntity route) {
        this.route = route;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity authorId) {
        this.author = authorId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AttributeEntity getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(AttributeEntity attributeId) {
        this.attributeId = attributeId;
    }

    public Date getDateComment() {
        return dateComment;
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentEntity that = (CommentEntity) o;

        if (commentId != null ? !commentId.equals(that.commentId) : that.commentId != null) return false;
        if (route != null ? !route.equals(that.route) : that.route != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (attributeId != null ? !attributeId.equals(that.attributeId) : that.attributeId != null) return false;
        if (dateComment != null ? !dateComment.equals(that.dateComment) : that.dateComment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId != null ? commentId.hashCode() : 0;
        result = 31 * result + (route != null ? route.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (attributeId != null ? attributeId.hashCode() : 0);
        result = 31 * result + (dateComment != null ? dateComment.hashCode() : 0);
        return result;
    }
}
