package com.funtl.itoken.service.sso.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "comment")
public class Comment {
    @Id
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    private Integer type;

    private Integer commentator;

    @Column(name = "gmt_create")
    private Long gmtCreate;

    @Column(name = "gmt_modified")
    private Long gmtModified;

    @Column(name = "like_count")
    private Long likeCount;

    @Column(name = "commentCount")
    private Long commentcount;

    private String content;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return commentator
     */
    public Integer getCommentator() {
        return commentator;
    }

    /**
     * @param commentator
     */
    public void setCommentator(Integer commentator) {
        this.commentator = commentator;
    }

    /**
     * @return gmt_create
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @param gmtCreate
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * @return gmt_modified
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * @param gmtModified
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * @return like_count
     */
    public Long getLikeCount() {
        return likeCount;
    }

    /**
     * @param likeCount
     */
    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * @return commentCount
     */
    public Long getCommentcount() {
        return commentcount;
    }

    /**
     * @param commentcount
     */
    public void setCommentcount(Long commentcount) {
        this.commentcount = commentcount;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
}