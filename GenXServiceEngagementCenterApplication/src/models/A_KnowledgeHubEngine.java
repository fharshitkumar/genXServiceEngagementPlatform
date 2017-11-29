package models;

import java.sql.ResultSet;
import java.sql.SQLException;


import entities.KnowledgeBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class A_KnowledgeHubEngine {

	public ObservableList<KnowledgeBase> displayKnowledgeHub(String selectedcustomerincident){

		A_DatabaseCommunicationEngine DCE = new A_DatabaseCommunicationEngine();
		ObservableList<KnowledgeBase> knowledgebaseincidentdata = FXCollections.observableArrayList();
		String SQLQuery = "SELECT * FROM KNOWLEDGEBASE";
		ResultSet resultSet = null;
		ResultSet rs = null;
		System.out.println("Inside A_KnowledgeHubEngine: Function ---> displayKnowledgeHub");

		try {
			resultSet = DCE.getResultSet(SQLQuery);
			rs = resultSet;

			Integer incidentid;
			String shorttext;
			String problem;
			Integer serviceid;
			Integer processorid;
			String solution;
			String tag;
			String notes;
			Integer sentiment;

			
			while(rs.next())
			{

				incidentid = rs.getInt(1);
				shorttext = (String)rs.getString(2);
				problem = (String)rs.getString(3);
				serviceid = rs.getInt(4);			
				processorid = rs.getInt(5);
				solution = (String)rs.getString(6);	
				tag = (String)rs.getString(7);				
				notes = (String)rs.getString(8);
				sentiment = rs.getInt(9);
				boolean matchfound=false;
				for (String incidentword : selectedcustomerincident.split(" ")) {
					for (String word : problem.split(" ")) {
						if(word.contains(incidentword))
							matchfound = true;
					}
				}

				if(matchfound)
				{
					knowledgebaseincidentdata.add(new KnowledgeBase(
							incidentid,
							shorttext,
							problem,
							serviceid,
							processorid,
							solution,
							tag,
							notes,
							sentiment

							));
				}
			}
			resultSet.close();

		} catch (SQLException e) {
			System.out.println("Failed to get Knowledge Hub data. More Details:");
			System.out.println(e.getMessage());
		}	

		return knowledgebaseincidentdata;


	}

}
