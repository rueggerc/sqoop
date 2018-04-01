package com.rueggerllc.tests;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.rueggerllc.dao.WidgetDAO;

public class WidgetDAOTests {

	private static Logger logger = Logger.getLogger(WidgetDAOTests.class);
	
	
	
	@BeforeClass
	public static void setupClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setupTest() throws Exception {
	}

	@After
	public void tearDownTest() throws Exception {
	}
	
	@Test
	@Ignore
	public void testGetMaxWidgetID() {
		WidgetDAO dao = new WidgetDAO();
		int max = dao.getMaxWidgetID();
		logger.info("MaxID=" + max);
	}
	
	
	@Test 
	@Ignore
	public void testSelectWidgets() {
		WidgetDAO dao = new WidgetDAO();
		dao.selectWidgets();
	}
	
	@Test 
	@Ignore
	public void testWidgets() {
		WidgetDAO dao = new WidgetDAO();
		dao.insertWidgets(10,0);
	}
	
	@Test 
	// @Ignore
	public void testInsertAdditionalWidgets() {
		WidgetDAO dao = new WidgetDAO();
		dao.insertAdditionalWidgets(10);
	}
	
	
}
