package entities;

/**
 * Class		: Employee		
 * Description	: Employee Entity
 * This is SLA entity and we can maintain/retrieve Employee details.											
 * @author HARSHIT KUMAR 			
 * Date			    : 30 November, 2017
 * Source File name	: Employee.java       	
 * 
 *
*/

public class Employee extends User{

	private Integer employeeid;
	private Integer departmentid;
	private Integer managerid;
	private Integer channelid;
	private Integer serviceid;
	private Integer roleid;
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


	
	
}
