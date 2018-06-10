package com.example.cynthia.relax.beans;

import java.io.Serializable;
import java.util.List;

public class SpecialistBean implements Serializable {
    private Integer specialistId;

    private String phone;

    private String realName;

    private String portrait;

    private Double rating;

    private String qualification;

    private Integer employLength;

    private String introduction;

    private List<TypeBean> typeBeanList;

    private List<CommentBean> commentBeanList;

    private List<PreOrderStatusBean> preOrderStatusBeanList;

    public SpecialistBean() {
    }

    public SpecialistBean(String realName, Double rating, String qualification, Integer employLength) {
        this.realName = realName;
        this.rating = rating;
        this.qualification = qualification;
        this.employLength = employLength;
    }

    public Integer getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(Integer specialistId) {
        this.specialistId = specialistId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Integer getEmployLength() {
        return employLength;
    }

    public void setEmployLength(Integer employLength) {
        this.employLength = employLength;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<TypeBean> getTypeBeanList() {
        return typeBeanList;
    }

    public void setTypeBeanList(List<TypeBean> typeBeanList) {
        this.typeBeanList = typeBeanList;
    }

    public List<CommentBean> getCommentBeanList() {
        return commentBeanList;
    }

    public void setCommentBeanList(List<CommentBean> commentBeanList) {
        this.commentBeanList = commentBeanList;
    }

    public List<PreOrderStatusBean> getPreOrderStatusBeanList() {
        return preOrderStatusBeanList;
    }

    public void setPreOrderStatusBeanList(List<PreOrderStatusBean> preOrderStatusBeanList) {
        this.preOrderStatusBeanList = preOrderStatusBeanList;
    }
}
