package com.rueggerllc.tests;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.rueggerllc.dao.PetDAO;

public class PetDAOTests {

	private static Logger logger = Logger.getLogger(PetDAOTests.class);
	
	
	
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
	public void testInsertPets() {
		PetDAO dao = new PetDAO();
		dao.insertPets(10);
	}
	
	
	@Test 
	// @Ignore
	public void testUpdatePet() {
		PetDAO dao = new PetDAO();
		dao.updatePets(6, "Updated6 Notes");
		dao.updatePets(7, "Updated7 Notes");
	}
	
	
}
