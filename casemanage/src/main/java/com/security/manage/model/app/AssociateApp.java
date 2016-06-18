package com.security.manage.model.app;

import java.util.Date;

public class AssociateApp {
	 private Integer id;

	    private String name;

	    private Integer typeid;
	    
	    private String typename;

		private String serialno;

	    private String latitude;

	    private String longitude;

	    private String address;

	    private Integer creator;

	    private String guid;

	    private String organname;

	    private String description;

	    private byte[] timespan;
	     
	    private Date createtime;
	     	    
	    private String telephone;

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

		public String getTypename() {
			return typename;
		}

		public void setTypename(String typename) {
			this.typename = typename;
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

		public String getGuid() {
			return guid;
		}

		public void setGuid(String guid) {
			this.guid = guid;
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
}
