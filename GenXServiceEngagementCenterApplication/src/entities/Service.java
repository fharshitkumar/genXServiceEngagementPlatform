package entities;

import javafx.beans.property.SimpleStringProperty;


/**
 * Class		: Service		
 * Description	: Service Entity
 * This is an Services Entity which will represent the services offered by a company.
 * Service tickets will be created for these services by customers.
 * Currently 17 services are offered by company
 * Microsoft Office 
 * Microsoft Excel
 * Microsoft PowerPoint
 * Microsoft Outlook
 * Microsoft OneNote
 * Microsoft OneDrive
 * Microsoft Publisher
 * Microsoft Access
 * Microsoft PictureMgr
 * Microsoft SharePoint
 * Microsoft Skype
 * Microsoft Exchange
 * Microsoft Yammer
 * Microsoft Sway
 * Microsoft Power BI
 * Microsoft Visio
 * Microsoft Project										
 * @author HARSHIT KUMAR 			
 * Date			    : 30 November, 2017
 * Source File name	: Service.java       	
 * 
 *
*/

public class Service {

	private Integer channelid;
	private SimpleStringProperty channelname;
	private SimpleStringProperty channeldesc;
	public Service(Integer channelid, String channelname, String channeldesc) {
		super();
		this.channelid = channelid;
		this.channelname = new SimpleStringProperty(channelname);
		this.channeldesc = new SimpleStringProperty(channeldesc);
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
	 * @return the channelname
	 */
	public String getChannelname() {
		return channelname.get();
	}
	/**
	 * @param channelname the channelname to set
	 */
	public void setChannelname(String channelname) {
		this.channelname = new SimpleStringProperty(channelname);
	}
	/**
	 * @return the channeldesc
	 */
	public String getChanneldesc() {
		return channeldesc.get();
	}
	/**
	 * @param channeldesc the channeldesc to set
	 */
	public void setChanneldesc(String channeldesc) {
		this.channeldesc = new SimpleStringProperty(channeldesc);
	}

}
