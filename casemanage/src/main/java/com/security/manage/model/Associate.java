package com.security.manage.model;

import java.util.Date;
import java.util.List;

import com.security.manage.util.Page;

public class Associate extends Page {
    private Integer id;

    private String name;

    private Integer typeid;
    
    private String typename;

	private String serialno;

    private String latitude;

    private String longitude;

    private String address;

    private Integer creator;

    private String creatorname;

    private String organname;

    private String description;

    private byte[] timespan; 
    
    private Date createtime;
    
    private String createtimes;
    
    private String telephone;
    
    private PlanPicture picture;
    
    public PlanPicture getPicture() {
		return picture;
	}

	public void setPicture(PlanPicture picture) {
		this.picture = picture;
	}

	private String guid;
    
    public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
 

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	private String createdate;

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

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getTimespan() {
        return timespan;
    }

    public void setTimespan(byte[] timespan) {
        this.timespan = timespan;
    }
 
	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
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