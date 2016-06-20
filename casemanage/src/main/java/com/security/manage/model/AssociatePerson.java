package com.security.manage.model;

import java.util.Date;

import com.security.manage.util.Page;

public class AssociatePerson extends Page  {
    private Integer id;

    private String name;

    private Integer sex;

    private String birth;

    private String photourl;

    private String idcard;

    private String address;

    private Integer associateid;

    private Integer creator;

    private String creatorname;

    private String organname;

    private String character;

    private String description;

    private Integer isleader;

    private byte[] timespan;

    private String telephone;
    
    private Date createtime;
    
    private String createtimes;
    
    private String guid;
    
    public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAssociateid() {
        return associateid;
    }

    public void setAssociateid(Integer associateid) {
        this.associateid = associateid;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getCreatorname() {
        return creatorname;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public String getOrganname() {
        return organname;
    }

    public void setOrganname(String organname) {
        this.organname = organname;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsleader() {
        return isleader;
    }

    public void setIsleader(Integer isleader) {
        this.isleader = isleader;
    }

    public byte[] getTimespan() {
        return timespan;
    }

    public void setTimespan(byte[] timespan) {
        this.timespan = timespan;
    }

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreatetimes() {
		return createtimes;
	}

	public void setCreatetimes(String createtimes) {
		this.createtimes = createtimes;
	}
}