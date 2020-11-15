package com.eziozhao.leafblog.mbg.entity;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 分类id
     */
    private Integer cid;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 发布日期
     */
    private Date publishdate;

    /**
     * 编辑日期
     */
    private Date edittime;

    /**
     * 0表示草稿箱，1表示已发表，2表示回收站，3表示已删除
     */
    private Integer state;

    /**
     * md文件源码
     */
    private String mdcontent;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public Date getEdittime() {
        return edittime;
    }

    public void setEdittime(Date edittime) {
        this.edittime = edittime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMdcontent() {
        return mdcontent;
    }

    public void setMdcontent(String mdcontent) {
        this.mdcontent = mdcontent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", cid=").append(cid);
        sb.append(", uid=").append(uid);
        sb.append(", publishdate=").append(publishdate);
        sb.append(", edittime=").append(edittime);
        sb.append(", state=").append(state);
        sb.append(", mdcontent=").append(mdcontent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}