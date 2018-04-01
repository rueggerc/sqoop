package com.rueggerllc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PetDAO {
	
	private static final Logger logger = Logger.getLogger(PetDAO.class);
	
	
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
	
	
	
	
	public void insertPets(int numberOfPets) {

		logger.info("Insert Pets");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Properties properties = new Properties();
			properties.setProperty("user", "hadoop");
			properties.setProperty("password", "dakota");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://captain:3306/hadoopdb", properties);
			
			String insertStatement = 
				"insert into pets " +
			    "(id, name, description, notes, weight, last_update) " +
			    "values " +
			    "(null, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(insertStatement);
			for (int i = 0; i < numberOfPets; i++) {
				insertPet(i, preparedStatement);
			}

		} catch (Exception e) {
			logger.error("ERROR", e);
		} finally {
			close(preparedStatement);
			close(connection);
		}		
	}
	
	private void insertPet(int i, PreparedStatement preparedStatement) throws Exception {
		logger.info("Insert Pet");
		preparedStatement.setString(1,"Pet" + i);
		preparedStatement.setString(2, "Description" + i);
		preparedStatement.setString(3, "Notes" + i);
		preparedStatement.setInt(4, new Integer(75+i));
		preparedStatement.setTimestamp(5, getTimestamp());
		preparedStatement.executeUpdate();
	}
	
	
	
	public void updatePets(int ID, String notes) {

		logger.info("Update Pets");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Properties properties = new Properties();
			properties.setProperty("user", "hadoop");
			properties.setProperty("password", "dakota");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://captain:3306/hadoopdb", properties);
			
			String updateStatement = 
				"update pets " +
			    "set notes = ? " +
				"where id = ?";
			preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1, notes);
			preparedStatement.setInt(2, ID);
			preparedStatement.executeUpdate();
			// connection.commit();

		} catch (Exception e) {
			logger.error("ERROR", e);
		} finally {
			close(preparedStatement);
			close(connection);
		}		
	}
	
	
	
	private Timestamp getTimestamp() {
		return new Timestamp(getNow().getTime());
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
