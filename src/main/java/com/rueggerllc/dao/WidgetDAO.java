package com.rueggerllc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Properties;

import org.apache.log4j.Logger;

public class WidgetDAO {
	
	private static final Logger logger = Logger.getLogger(WidgetDAO.class);
	
	
	public void selectWidgets() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Properties properties = new Properties();
			properties.setProperty("user", "hadoop");
			properties.setProperty("password", "dakota");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://captain:3306/hadoopdb", properties);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from widgets");
			while (resultSet.next()) {
				String widgetName = resultSet.getString("widget_name");
				logger.info("Widget Name=" + widgetName);
			}
		} catch (Exception e) {
			logger.error("ERROR", e);
		} finally {
			close(resultSet);
			close(statement);
			close(connection);
		}
	}
	
	public int getMaxWidgetID() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		int max = 0;
		try {
			Properties properties = new Properties();
			properties.setProperty("user", "hadoop");
			properties.setProperty("password", "dakota");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://captain:3306/hadoopdb", properties);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select max(id) from widgets");
			while (resultSet.next()) {
				max = resultSet.getInt(1);
				logger.info("MAX=" + max);
			}
		} catch (Exception e) {
			logger.error("ERROR", e);
		} finally {
			close(resultSet);
			close(statement);
			close(connection);
		}
		return max;
	}
	
	
	
	
	public void insertAdditionalWidgets(int numberOfWidgetsToInsert) {
		int maxWidgetID = getMaxWidgetID();
		insertWidgets(numberOfWidgetsToInsert, maxWidgetID+1);
	}
	
	public void insertWidgets(int numberOfWidgetsToInsert, int startID) {

		logger.info("Insert Widgets Starting with startID=" + startID);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Properties properties = new Properties();
			properties.setProperty("user", "hadoop");
			properties.setProperty("password", "dakota");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://captain:3306/hadoopdb", properties);
			
			String insertStatement = 
				"insert into widgets " +
			    "(id, widget_name, price, design_date, version, design_comment) " +
			    "values " +
			    "(null, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(insertStatement);
			for (int i = 0; i < numberOfWidgetsToInsert; i++) {
				insertWidget(startID + i, preparedStatement);
			}

		} catch (Exception e) {
			logger.error("ERROR", e);
		} finally {
			close(preparedStatement);
			close(connection);
		}		
	}
	
	private void insertTop3Widgets(PreparedStatement preparedStatement) throws Exception {
		preparedStatement.setString(1,"sprocket");
		preparedStatement.setDouble(2, new Double(0.25));
		preparedStatement.setDate(3, getNow());
		preparedStatement.setInt(4, new Integer(1));
		preparedStatement.setString(5, "Connects two gizmos");
		preparedStatement.executeUpdate();		
		
		preparedStatement.setString(1,"gizmo");
		preparedStatement.setDouble(2, new Double(4.00));
		preparedStatement.setDate(3, getNow());
		preparedStatement.setInt(4, new Integer(4));
		preparedStatement.setString(5, "some widget");
		preparedStatement.executeUpdate();		
		
		preparedStatement.setString(1,"gadget");
		preparedStatement.setDouble(2, new Double(99.99));
		preparedStatement.setDate(3, getNow());
		preparedStatement.setInt(4, new Integer(13));
		preparedStatement.setString(5, "Flagship Product");
		preparedStatement.executeUpdate();		
	}
	
	private void insertWidget(int i, PreparedStatement preparedStatement) throws Exception {
		logger.info("Insert");
		preparedStatement.setString(1,"Widget#" + i);
		preparedStatement.setDouble(2, new Double(311.55+i));
		preparedStatement.setDate(3, getNow());
		preparedStatement.setInt(4, new Integer(42+i));
		preparedStatement.setString(5, "Widget Comment: " + i);
		preparedStatement.executeUpdate();
	}
	
	private static java.sql.Date getNow() {
		long now = Calendar.getInstance().getTime().getTime();
		java.sql.Date date = new java.sql.Date(now);
		return date;
	}
	
	private void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
		}
	}
	private void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (Exception e) {
		}
	}
	private void close(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (Exception e) {
		}
	}

}
