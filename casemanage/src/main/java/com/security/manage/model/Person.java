package com.security.manage.model;

import java.util.Date;

import com.security.manage.util.Page;

public class Person extends Page  {
    private Integer id;

    private String name;

    private Integer typeid;

    private Integer levelid;

    private Integer sex;

    private String birth;

    private String photourl;

    private String idcard;

    private String address;

    private String serialno;

    private String casecomment;

    private String description;

    private Integer creator;

    private String creatorname;

    private String organname;

    private String macaddress;

    private byte[] timespan;
    
    private String typeName;
    
    private String levelName;
    
    private Date createtime;
    
    private String createtimes;
    
    private String telephone;
    
    private String policename;
    
    private String policesector;
    
    private String policephone;

    public String getPolicename() {
		return policename;
	}

	public void setPolicename(String policename) {
		this.policename = policename;
	}

	public String getPolicesector() {
		return policesector;
	}

	public void setPolicesector(String policesector) {
		this.policesector = policesector;
	}

	public String getPolicephone() {
		return policephone;
	}

	public void setPolicephone(String policephone) {
		this.policephone = policephone;
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

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getLevelid() {
        return levelid;
    }

    public void setLevelid(Integer levelid) {
        this.levelid = levelid;
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

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getCasecomment() {
        return casecomment;
    }

    public void setCasecomment(String casecomment) {
        this.casecomment = casecomment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }

    public byte[] getTimespan() {
        return timespan;
    }

    public void setTimespan(byte[] timespan) {
        this.timespan = timespan;
    }

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCreatetimes() {
		return createtimes;
	}

	public void setCreatetimes(String createtimes) {
		this.createtimes = createtimes;
	}

	
}