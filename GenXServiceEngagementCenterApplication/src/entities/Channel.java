package entities;

import javafx.beans.property.SimpleStringProperty;

/**
 * Class		: Channel		
 * Description	: Channel  Entity
 * This is SLA entity and we can maintain/retrieve Channel  details.											
 * @author HARSHIT KUMAR 			
 * Date			    : 30 November, 2017
 * Source File name	: Channel.java       	
 * 
 *
*/

public class Channel {

	private Integer channelid;
	private SimpleStringProperty channelname;
	private SimpleStringProperty channeldesc;
	public Channel(Integer channelid, String channelname, String channeldesc) {
		super();
		this.channelid = channelid;
		this.channelname =  new SimpleStringProperty(channelname);
		this.channeldesc =  new SimpleStringProperty(channeldesc);
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
		this.channelname =  new SimpleStringProperty(channelname);
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
		this.channeldesc =  new SimpleStringProperty(channeldesc);
	}

	
	
}
