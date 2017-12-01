package models;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Page;
import com.restfb.types.Post;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.ResultSet;


/**
 * Class		: A_InteractionManagementEngine		
 * Description	: Allows to access Facebook page posts from GenX Incident page and create a new
 * Incident. 
 *
 *
 *	.....................................................................
 *	....+-----------------------------------------------------------+....						
 *	....|        A_InteractionManagementEngine Class                |....						
 *	....|             << concrete class >>                          |....	                    
 *	....+-----------------------------------------------------------+....						
 *	....| [-] accessToken           : String                        |....						
 *	....| [-] postFeed       : iConnection<Post>                    |....						
 *	....|                                                           |....						
 *	....+-----------------------------------------------------------+....						
 *	....| [+] DateTime: timeConversion(String datetimeinput)        |....						
 *	*...+-----------------------------------------------------------+....						
 *	.....................................................................																				
 * 																								
 * @author PRAGYA SHUKLA 			
 * Date			    : 28 November, 2017
 * Source File name	: A_InteractionManagementEngine.java       	
 * 
 *
*/

public class A_InteractionManagementEngine {

	
    /******************************************************************************************
     * @param datetimeinput : Receives String format datetime to convert to jodaTime format
     * @return : Jodatime format datetime
     *******************************************************************************************
     */
    public static DateTime timeConversion(String datetimeinput){
        String createdTime = datetimeinput;
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        Date date1 = new Date(Long.parseLong(createdTime)); // right here
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // here you would have to customize the output format you are looking for
        DateTime dt = formatter.parseDateTime(sdf1.format(date1));
        return dt;
    }

    /***********************************************************************************************
     * Main function definition to get the facebook page post and create a new incident
     * @param args
     * *********************************************************************************************
     */

	public static void FacebookManager() {

	    // Initial DB Connection Objects
        A_DatabaseCommunicationEngine selectConn = new A_DatabaseCommunicationEngine();
        A_IncidentManagementEngine insertClass = new A_IncidentManagementEngine();
        System.getProperties().setProperty("oracle.jdbc.J2EE13Compliant", "true");
		String accessToken= "EAACFwplUaTYBAPk2DKpJEZCZCrJ0P8uQG3e2LRJbHlSZC5Y7RAW47jmx4pQ6ypv5QjG0cTTs442NSucNWDfO3hcwTwQn7KSCorHHQNCgZA7FSUHYxEqjbcDLZAYxOG657Exdv4LDtfL0N1Bf2GuX0h5DYNlx2NuzN8oYGqJAZAigZDZD";
		FacebookClient fb= new DefaultFacebookClient(accessToken, Version.VERSION_2_9);
		Page page = fb.fetchObject("410477796035085", Page.class);
		Connection<Post> postFeed= fb.fetchConnection(page.getId() + "/feed", Post.class,
				Parameter.with("fields", "from,message,created_time"));
		long latestPull=0;

        //Capture Latest inserted incident datetime
        String SqlQuery = "select max(createdon) as newDate from incident where CHANNELID = 100";
        try {
            ResultSet newRs =  selectConn.getResultSet(SqlQuery);
            if (newRs.next()){
                latestPull = newRs.getTimestamp(1).getTime();
            }
            newRs.close();
        } catch (SQLException e) {
            System.out.println("Select Query Error : View below StackTrace");
            e.printStackTrace();
        }

        //Pull ALl services from service table
        String serviceList = "";
        try {
            ResultSet serviceRs = selectConn.getResultSet("select servicename from service");

            while (serviceRs.next()) {
                serviceList = serviceList.concat(serviceRs.getString(1).toUpperCase() + ",");
            }
            serviceList = serviceList.concat(",");
        }catch (SQLException e) {
            System.out.println("Select Query Error : View below StackTrace");
            e.printStackTrace();
        }

        serviceList = serviceList.replace(",,","");

        //Iterate through all posts in Facebook
        for(List<Post> postPage: postFeed){
			for(Post aPost : postPage){
				long createTime = aPost.getCreatedTime().getTime();

				//Call timeConversion function for Facebook and Database
                DateTime Fbtime = timeConversion(Long.toString(createTime));
                DateTime Dbtime = timeConversion(Long.toString(latestPull));
                String[] postArray = aPost.getMessage().toUpperCase().split(" ");

                //Get the name of user posted message
                String UserName = aPost.getFrom().getName();
                String Fname = UserName.split(" ")[0];
                String Lname = UserName.split(" ")[1];

                Integer personID = -1;
                Integer roleID = -1;
                Integer serviceid = -1;
                String shortMsg="";
                String longMsg = "";
                Integer serviceFound = 0;

                //Process the feed only if entry is newer than incident table
                if(Fbtime.getMillis() - Dbtime.getMillis() > 0) {
                    try {
                        // Pull PersonID from personinfo table for user posted message
                        ResultSet newRs = selectConn.getResultSet("select PERSONID from personinfo where FNAME = '" + Fname + "' and LNAME ='" + Lname + "'");

                        if (newRs.next()) {
                            personID = newRs.getInt(1);
                            //Pull roleId of user
                            ResultSet roleRs = selectConn.getResultSet("select roleid from customer where customerid = " + personID);

                            if (roleRs.next()) {
                                roleID = roleRs.getInt("roleid");

                                for (String x_element : postArray) {

                                    for (String y_element : serviceList.split(",")) {
                                        //pull serviceid from service table for the service requested in feed
                                        if (x_element.equals(y_element)) {
                                            ResultSet serviceRs = selectConn.getResultSet("select serviceid from  service where upper(servicename) =  '" + y_element + "'");
                                            serviceRs.next();
                                            serviceid = serviceRs.getInt(1);
                                            shortMsg = "MICROSOFT " + y_element + " ENDED WITH AN ERROR";
                                            serviceFound = 1;
                                        }

                                    }
                                }
                                longMsg = aPost.getMessage();
                            }
                            //Define variables used in inserting record in incident table
                            Integer customerid = personID;
                            Integer channelid = 100;
                            String priority = "";
                            if (roleID == 1) {
                                priority = "HIGH";
                            } else {
                                priority = "MEDIUM";
                            }
                            String status = "OPEN";
                            String solution = "Ticket OPENED";
                            Integer sentiment = 3;

                                if (serviceFound == 1) {
                                    Timestamp timeStamp = new Timestamp(Fbtime.getMillis());
                                    //Insert record
                                    insertClass.createIncident(null, customerid, channelid, serviceid, shortMsg, longMsg, priority, timeStamp, status, solution, null, null, null, null, false, sentiment);
                                } else{
                                    System.out.println("Service Undefined for message : " + longMsg);
                                }
                        }
                        newRs.close();
                    } catch (SQLException e) {
                        System.out.println("Select Query Error : View below StackTrace" + e.getMessage());
                        
                    }
                }

			}
		}

	}
	
	
	
}



	
