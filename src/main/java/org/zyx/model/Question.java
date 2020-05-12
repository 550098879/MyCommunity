package org.zyx.model;

public class Question {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.ID
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.TITLE
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.GMT_CREATE
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.GMT_MODIFIED
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.CREATER_ID
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    private Long createrId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.COMMENT_COUNT
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    private Long commentCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.VIEW_COUNT
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    private Long viewCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.LIKE_COUNT
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    private Long likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.TAGS
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    private String tags;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.DISCRIPTION
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    private String discription;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.ID
     *
     * @return the value of QUESTION.ID
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.ID
     *
     * @param id the value for QUESTION.ID
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.TITLE
     *
     * @return the value of QUESTION.TITLE
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.TITLE
     *
     * @param title the value for QUESTION.TITLE
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.GMT_CREATE
     *
     * @return the value of QUESTION.GMT_CREATE
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.GMT_CREATE
     *
     * @param gmtCreate the value for QUESTION.GMT_CREATE
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.GMT_MODIFIED
     *
     * @return the value of QUESTION.GMT_MODIFIED
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.GMT_MODIFIED
     *
     * @param gmtModified the value for QUESTION.GMT_MODIFIED
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.CREATER_ID
     *
     * @return the value of QUESTION.CREATER_ID
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.CREATER_ID
     *
     * @param createrId the value for QUESTION.CREATER_ID
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.COMMENT_COUNT
     *
     * @return the value of QUESTION.COMMENT_COUNT
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public Long getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.COMMENT_COUNT
     *
     * @param commentCount the value for QUESTION.COMMENT_COUNT
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.VIEW_COUNT
     *
     * @return the value of QUESTION.VIEW_COUNT
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public Long getViewCount() {
        return viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.VIEW_COUNT
     *
     * @param viewCount the value for QUESTION.VIEW_COUNT
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.LIKE_COUNT
     *
     * @return the value of QUESTION.LIKE_COUNT
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public Long getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.LIKE_COUNT
     *
     * @param likeCount the value for QUESTION.LIKE_COUNT
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.TAGS
     *
     * @return the value of QUESTION.TAGS
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public String getTags() {
        return tags;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.TAGS
     *
     * @param tags the value for QUESTION.TAGS
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.DISCRIPTION
     *
     * @return the value of QUESTION.DISCRIPTION
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public String getDiscription() {
        return discription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.DISCRIPTION
     *
     * @param discription the value for QUESTION.DISCRIPTION
     *
     * @mbg.generated Tue May 12 17:34:39 GMT+08:00 2020
     */
    public void setDiscription(String discription) {
        this.discription = discription;
    }
}