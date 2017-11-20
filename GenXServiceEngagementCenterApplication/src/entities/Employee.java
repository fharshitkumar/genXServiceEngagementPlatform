package entities;

public class Employee extends User{

	private Integer employeeid;
	private Integer departmentid;
	private Integer managerid;
	private Integer channelid;
	private Integer serviceid;
	private Integer roleid;
	private Boolean availables;
	public Employee(Integer personid, String username, String password, Boolean admin, String secretquestion,
			String secretanswer, String email, Integer employeeid, Integer departmentid, Integer managerid,
			Integer channelid, Integer serviceid, Integer roleid, Boolean availables) {
		super(personid, username, password, admin, secretquestion, secretanswer, email, roleid);
		this.employeeid = employeeid;
		this.departmentid = departmentid;
		this.managerid = managerid;
		this.channelid = channelid;
		this.serviceid = serviceid;
		this.roleid = roleid;
		this.availables = availables;
	}
	/**
	 * @return the employeeid
	 */
	public Integer getEmployeeid() {
		return employeeid;
	}
	/**
	 * @param employeeid the employeeid to set
	 */
	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}
	/**
	 * @return the departmentid
	 */
	public Integer getDepartmentid() {
		return departmentid;
	}
	/**
	 * @param departmentid the departmentid to set
	 */
	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}
	/**
	 * @return the managerid
	 */
	public Integer getManagerid() {
		return managerid;
	}
	/**
	 * @param managerid the managerid to set
	 */
	public void setManagerid(Integer managerid) {
		this.managerid = managerid;
	}
	/**
	 * @return the channelid
	 */
	public Integer getChannelid() {
		return channelid;
	}
	/**
	 * @param channelid the channelid to set
	 */
	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}
	/**
	 * @return the serviceid
	 */
	public Integer getServiceid() {
		return serviceid;
	}
	/**
	 * @param serviceid the serviceid to set
	 */
	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
	}
	/**
	 * @return the roleid
	 */
	public Integer getRoleid() {
		return roleid;
	}
	/**
	 * @param roleid the roleid to set
	 */
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	/**
	 * @return the availables
	 */
	public Boolean getAvailables() {
		return availables;
	}
	/**
	 * @param availables the availables to set
	 */
	public void setAvailables(Boolean availables) {
		this.availables = availables;
	}

	
	
}