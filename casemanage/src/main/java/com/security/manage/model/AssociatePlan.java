package com.security.manage.model;

public class AssociatePlan {
    private Integer id;

    private Integer associateid;

    private String planurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssociateid() {
        return associateid;
    }

    public void setAssociateid(Integer associateid) {
        this.associateid = associateid;
    }

    public String getPlanurl() {
        return planurl;
    }

    public void setPlanurl(String planurl) {
        this.planurl = planurl;
    }
}