package com.kokabmedia.jdbc;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kokabmedia.jdbc.entity.Person;
import com.kokabmedia.jdbc.personjdbc.PersonJdbcDao;

/*
 * This class is the main thread class of the application, with the main method that 
 * launches the application with the Spring framework.
 * 
 * The @SpringBootApplication annotation initialises the Spring framework and starts (launches) 
 * the Application Context of the Spring framework which is the implementation of the Spring 
 * IOC Container that manages all of the beans. It also initialises Spring Boot framework and auto 
 * configuration and enables component scanning of this package and sub-packages to locate beans,
 * this is all done automatically. 
 */
@SpringBootApplication
public class JdbcDatabaseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		
		// Returns Application Context
		SpringApplication.run(JdbcDatabaseApplication.class, args);
	}
	
	// For logging purposes
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * The @Autowired annotation tells the Spring framework that PersonJdbcDao
	 * instance (bean) is an dependency of JdbcDatabaseApplication class, it is a 
	 * mechanism for implementing Spring dependency injection.
	 * 
	 * The PersonJdbcDao class is now a dependency of the JdbcDatabaseApplication class.
	 * 
	 * Spring framework creates an instance (bean) of the PersonJdbcDao and autowires it to
	 * the JdbcDatabaseApplication class object as a dependency.
	 */
	@Autowired
	PersonJdbcDao dao;
	
	
	/*
	 * With the CommandLineRunner the code in this run() method will be executes as soon as the 
	 * Application Context is launched.
	 */
	@Override
	public void run(String... args) throws Exception {
		
		// Start a query and retrieve data from the database
		logger.info("All users -> {}",dao.findAll());
		
		// Start a query and retrieve data from the database
		logger.info("User id 10001 -> {}",dao.findById(10001));
		
		// Start a query and delete data from the database
		logger.info("Delete id 10002 -> No of rows deleted - {}",
				dao.deleteById(10002));
		
		// Start a query and insert data into the database
		logger.info("Inserting 10004 -> {}",
				dao.insert(new Person(10004, "Mark", "Amsterdam", new Date())));

		// Start a query and update data in the database
				logger.info("Updating 10003 -> {}",
						dao.update(new Person(10004, "Jill", "Berlin", new Date())));
	}

}
