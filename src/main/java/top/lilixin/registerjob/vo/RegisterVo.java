package top.lilixin.registerjob.vo;

/**
*@Author: lilixin
*@Date: 2016年10月17日
*/
public class RegisterVo {
	private Long id;
	private String uid;
	private String type;
	private String regtime;
	private String location;
	private String plat;
	private String opt;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPlat() {
		return plat;
	}
	public void setPlat(String plat) {
		this.plat = plat;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	@Override
	public String toString() {
		return "RegisterVo [id=" + id + ", uid=" + uid + ", type=" + type + ", regtime=" + regtime + ", location="
				+ location + ", plat=" + plat + ", opt=" + opt + "]";
	}
	
}
