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
    @Basic
    @Column(name = "route_id")
    private Integer routeId;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private UserEntity authorEntity;
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

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public UserEntity getAuthorEntity() {
        return authorEntity;
    }

    public void setAuthorEntity(UserEntity authorId) {
        this.authorEntity = authorId;
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
        if (routeId != null ? !routeId.equals(that.routeId) : that.routeId != null) return false;
        if (authorEntity != null ? !authorEntity.equals(that.authorEntity) : that.authorEntity != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (attributeId != null ? !attributeId.equals(that.attributeId) : that.attributeId != null) return false;
        if (dateComment != null ? !dateComment.equals(that.dateComment) : that.dateComment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId != null ? commentId.hashCode() : 0;
        result = 31 * result + (routeId != null ? routeId.hashCode() : 0);
        result = 31 * result + (authorEntity != null ? authorEntity.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (attributeId != null ? attributeId.hashCode() : 0);
        result = 31 * result + (dateComment != null ? dateComment.hashCode() : 0);
        return result;
    }
}
