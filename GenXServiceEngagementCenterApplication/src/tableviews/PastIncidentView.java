package tableviews;

import javafx.beans.property.SimpleStringProperty;

/**
 * Class		: PastIncidentView		
 * Description	: Allows the application components to access the Customer's past Incidents information view
 *                This will be used to show customer's past incidents information on Customer Representative screen. 
 *Specials      : The getter and setter has been enhanced to support JavaJX editable tableView for future enhancements.
 *
 *	.....................................................................
 *	....+-----------------------------------------------------------+....						
 *	....|            PastIncidentView Class                         |....						
 *	....|             << concrete class >>                          |....	                    
 *	....+-----------------------------------------------------------+....						
 *	....| [-] status           : SimpleStringProperty               |....						
 *	....| [-] customerid       : int                                |....						
 *	....| [-] shorttext        : SimpleStringProperty               |....						
 *	....+-----------------------------------------------------------+....						
 *	....| [+] PastIncidentView()                                    |....						
 *	....| [+] void: setstatus(String)                               |....						
 *	....| [+] void: setcustomerid(int)                              |....						
 *	....| [+] void: setshorttext(String)                            |....						
 *	....| [+] int:  getstatus()                                     |....						
 *	....| [+] int:  getcustomerid()                                 |....						
 *	....| [+] int:  getshorttext()                                  |....						
 *	....+-----------------------------------------------------------+....						
 *	.....................................................................																				
 * 																								
 * @author HARSHIT KUMAR 			
 * Date			    : 28 November, 2017
 * Source File name	: PastIncidentView.java       	
 * 
 *
*/

public class PastIncidentView {

	private SimpleStringProperty status;
	private Integer customerid;
	private SimpleStringProperty shorttext;
	public PastIncidentView(String status, Integer customerid, String shorttext) {
		super();
		this.status = new SimpleStringProperty(status);
		this.customerid = customerid;
		this.shorttext = new SimpleStringProperty(shorttext);
	}
	/**************************************************************************************
	 * @return the status
	 */
	public String getStatus() {
		return status.get();
	}
	/**************************************************************************************
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = new SimpleStringProperty(status);
	}
	/**************************************************************************************
	 * @return the customerid
	 */
	public Integer getCustomerid() {
		return customerid;
	}
	/**************************************************************************************
	 * @param customerid the customerid to set
	 */
	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}
	/**************************************************************************************
	 * @return the shorttext
	 */
	public String getShorttext() {
		return shorttext.get();
	}
	/**************************************************************************************
	 * @param shorttext the shorttext to set
	 */
	public void setShorttext(String shorttext) {
		this.shorttext = new SimpleStringProperty(shorttext);
	}

	
	
	
}
