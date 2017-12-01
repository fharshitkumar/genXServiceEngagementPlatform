package models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

	@SuppressWarnings("resource")
	public void writeXLSXFile_1(ResultSet rs) throws IOException {

		String excelFileName = "AnalyticalReports/Retrieve Employee based on Specialization.xlsx";// name
																					// of
																					// excel
																					// file

		String sheetName = "Report";// name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);

		int cnt = 1;
		XSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("PersonID");
		row.createCell(1).setCellValue("FirstName");
		row.createCell(2).setCellValue("LastName");

		try {
			while (rs.next()) {

				XSSFRow row1 = sheet.createRow(cnt);
				row1.createCell(0).setCellValue(rs.getInt("PERSONID"));
				row1.createCell(1).setCellValue(rs.getString("FNAME"));
				row1.createCell(2).setCellValue(rs.getString("LNAME"));
				cnt++;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		// write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	@SuppressWarnings("resource")
	public void writeXLSXFile_2(ResultSet rs) throws IOException {

		String excelFileName = "AnalyticalReports/Specialization required to solve a particular problem.xlsx";// name
																								// of
																								// excel
																								// file

		String sheetName = "Report2";// name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);

		int cnt = 1;
		XSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("SPECIALIZATIONID");
		row.createCell(1).setCellValue("SPECIALIZATIONNAME");

		try {
			while (rs.next()) {

				XSSFRow row1 = sheet.createRow(cnt);
				row1.createCell(0).setCellValue(rs.getInt("SPECIALIZATIONID"));
				row1.createCell(1).setCellValue(rs.getString("SPECIALIZATIONNAME"));
				cnt++;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		// write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	@SuppressWarnings("resource")
	public void writeXLSXFile_3(ResultSet rs) throws IOException {

		String excelFileName = "AnalyticalReports/Unassigned tickets.xlsx";// name of excel
																// file

		String sheetName = "Report3";// name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);

		int cnt = 1;
		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("CHANNELID");
		row.createCell(1).setCellValue("CHANNELNAME");
		row.createCell(2).setCellValue("SERVICEID");
		row.createCell(3).setCellValue("SERVICENAME");
		row.createCell(4).setCellValue("PROBLEM");
		row.createCell(5).setCellValue("PRIORITY");
		row.createCell(6).setCellValue("STATUS");

		try {
			while (rs.next()) {

				XSSFRow row1 = sheet.createRow(cnt);
				row1.createCell(0).setCellValue(rs.getInt("CHANNELID"));
				row1.createCell(1).setCellValue(rs.getString("CHANNELNAME"));
				row1.createCell(2).setCellValue(rs.getInt("SERVICEID"));
				row1.createCell(3).setCellValue(rs.getString("SERVICENAME"));
				row1.createCell(4).setCellValue(rs.getString("PROBLEM"));
				row1.createCell(5).setCellValue(rs.getString("PRIORITY"));
				row1.createCell(6).setCellValue(rs.getString("STATUS"));
				cnt++;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		// write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	@SuppressWarnings("resource")
	public void writeXLSXFile_4(ResultSet rs) throws IOException {

		String excelFileName = "AnalyticalReports/Agent Workload.xlsx";// name of excel file

		String sheetName = "Report4";// name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);

		int cnt = 1;
		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("PersonID");
		row.createCell(1).setCellValue("FirstName");
		row.createCell(2).setCellValue("LastName");
		row.createCell(3).setCellValue("Hours Spent At Work");
		row.createCell(4).setCellValue("Assigned");
		row.createCell(5).setCellValue("Resolved");

		try {
			while (rs.next()) {

				XSSFRow row1 = sheet.createRow(cnt);
				row1.createCell(0).setCellValue(rs.getInt("PersonID"));
				row1.createCell(1).setCellValue(rs.getString("FName"));
				row1.createCell(2).setCellValue(rs.getString("LName"));
				row1.createCell(3).setCellValue(rs.getInt("Hours_Spent_At_Work"));
				row1.createCell(4).setCellValue(rs.getInt("Assigned"));
				row1.createCell(5).setCellValue(rs.getInt("Resolved"));

				cnt++;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		// write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	@SuppressWarnings("resource")
	public void writeXLSXFile_5(ResultSet rs) throws IOException {

		String excelFileName = "AnalyticalReports/Ticket escalation rate by channel.xlsx";// name
																			// of
																			// excel
																			// file

		String sheetName = "Report5";// name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);

		int cnt = 1;
		XSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("PRIORITY");
		row.createCell(1).setCellValue("Number Of Issues Logged");

		try {
			while (rs.next()) {

				XSSFRow row1 = sheet.createRow(cnt);
				row1.createCell(0).setCellValue(rs.getString("PRIORITY"));
				row1.createCell(1).setCellValue(rs.getInt("Number_Of_Issues_Logged"));
				cnt++;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		// write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	@SuppressWarnings("resource")
	public void writeXLSXFile_6(ResultSet rs) throws IOException {

		String excelFileName = "AnalyticalReports/Top topics raised.xlsx";// name of excel
															// file

		String sheetName = "Report6";// name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);

		int cnt = 1;
		XSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("PROBLEM");
		row.createCell(1).setCellValue("Number Of Times Raised");

		try {
			while (rs.next()) {

				XSSFRow row1 = sheet.createRow(cnt);
				row1.createCell(0).setCellValue(rs.getString("PROBLEM"));
				row1.createCell(1).setCellValue(rs.getInt("Number_Of_Times_Raised"));
				cnt++;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		// write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	@SuppressWarnings("resource")
	public void writeXLSXFile_7(ResultSet rs) throws IOException {

		String excelFileName = "AnalyticalReports/Tickets by region.xlsx";// name of excel
															// file

		String sheetName = "Report7";// name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);

		int cnt = 1;
		XSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("STATE");
		row.createCell(1).setCellValue("Number Of tickets logged");

		try {
			while (rs.next()) {

				XSSFRow row1 = sheet.createRow(cnt);
				row1.createCell(0).setCellValue(rs.getString("STATE"));
				row1.createCell(1).setCellValue(rs.getInt("Number_Of_Tickets_Raised"));
				cnt++;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		// write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	@SuppressWarnings("resource")
	public void writeXLSXFile_8(ResultSet rs) throws IOException {

		String excelFileName = "AnalyticalReports/Tickets based on Priority.xlsx";// name of
																	// excel
																	// file

		String sheetName = "Report8";// name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);

		int cnt = 1;
		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("CHANNELID");
		row.createCell(1).setCellValue("CHANNELNAME");
		row.createCell(2).setCellValue("SERVICEID");
		row.createCell(3).setCellValue("SERVICENAME");
		row.createCell(4).setCellValue("PROBLEM");
		row.createCell(5).setCellValue("PRIORITY");
		row.createCell(6).setCellValue("STATUS");

		try {
			while (rs.next()) {

				XSSFRow row1 = sheet.createRow(cnt);
				row1.createCell(0).setCellValue(rs.getInt("CHANNELID"));
				row1.createCell(1).setCellValue(rs.getString("CHANNELNAME"));
				row1.createCell(2).setCellValue(rs.getInt("SERVICEID"));
				row1.createCell(3).setCellValue(rs.getString("SERVICENAME"));
				row1.createCell(4).setCellValue(rs.getString("PROBLEM"));
				row1.createCell(5).setCellValue(rs.getString("PRIORITY"));
				row1.createCell(6).setCellValue(rs.getString("STATUS"));

			}
		} catch (SQLException e) {
		e.printStackTrace();
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		// write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

}

