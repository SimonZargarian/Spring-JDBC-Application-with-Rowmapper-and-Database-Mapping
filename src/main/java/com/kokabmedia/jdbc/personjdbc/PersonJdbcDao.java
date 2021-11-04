package com.kokabmedia.jdbc.personjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kokabmedia.jdbc.entity.Person;

/*
 * This class communicates with the database.
 * 
 * The @Repository annotation allows the Spring framework to creates an instance (bean) 
 * of this class and manage it with the Spring Application Context (the IOC container)
 * that maintains all the beans for the application.  
 *
 * The @Repository annotation lets the Spring framework manage the PersonJdbcDao 
 * class as a Spring bean. The Spring framework will find the bean with auto-detection 
 * when scanning the class path with component scanning. It turns the class into a 
 * Spring bean at the auto-scan time.
 * 
 * @Repository annotation allows the PersonJdbcDao class to be wired in as dependency 
 * to a another object or a bean with the @Autowired annotation.
 * 
 * The @Repository annotation is a specialisation of @Component annotation for more 
 * specific use cases.
 */
@Repository
public class PersonJdbcDao {

	/*
	 * The @Autowired annotation tells the Spring framework that jdbcTemplate
	 * instance is an dependency of PersonJdbcDao class it is a mechanism for 
	 * implementing Spring dependency injection.
	 * 
	 * The JdbcTemplate class is now a dependency of the PersonJdbcDao class.
	 * 
	 * Spring framework creates an instance of the JdbcTemplate and autowires it to
	 * the PersonJdbcDao class object as a dependency.
	 *
	 * The JdbcTemplate allows Spring to give us the database connection and lets us
	 * execute a queries. It is autowired with a data source connection. 
	 */
	@Autowired
	JdbcTemplate jdbcTemplate;

	/*
	 * Row mapping is process of mapping the collected results from the database 
	 * to the java beans. JPA handles this for us, but with JDBC we need to
	 * Implement it.
	 * 
	 * Inner class with a custom Row Mapper, for when of manual mapping 
	 * is necessary. We can replace BeanPropertyRowMapper<Person> (Person.class)
	 * with PersonRowMapper.
	 */
	class PersonRowMapper implements RowMapper<Person>{

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getTimestamp("birth_date"));
	
			return person;
		}
		
	}
	
	public List<Person> findAll() {

		/*
		 * The jdbcTemplate.query() method executes a query select * from person, maps
		 * it to the Person table and returns a mapped List of Person.
		 * 
		 * BeanPropertyRowMapper is a automatic mapper provided by SPring JDBC, which
		 * can be used when the column names in the database and the field names of the
		 * Person class match each other, with exact mapping. The data in the Person
		 * table is mapped to the Person class.
		 * 
		 * The Spring Boot auto configuration handles the configuration of embedded H2 database, 
		 * the autowiring of JdbcTemplate, the connection of JdbcTemplate with embedded H2 database
		 * and collecting the data source.
		 */
		return jdbcTemplate.query("select * from person", 
				new BeanPropertyRowMapper<Person>(Person.class));

	}
	
	// Querying for a specific object (row)
	@SuppressWarnings("deprecation")
	public Person findById(int id) {

		/*
		 * The jdbcTemplate.queryForObject() method executes a query select * from person 
		 * where id=?, maps it tothe a specific Person with an id, returns a that Person.
		 */
		return jdbcTemplate.queryForObject("select * from person where id=?",
				new Object[]{id},
				new BeanPropertyRowMapper<Person>(Person.class));

	}
	
	// Delete a specific object (row)
	public int deleteById(int id) {

		/*
		 * The jdbcTemplate.update() method executes a query delete from person  where id=?,
		 * maps it to the a specific Person with an id, deletes a that Person and returns
		 * number of rows effected.
		 */
		return jdbcTemplate.update("delete from person where id=?",
				new Object[]{id});
	}
	
	// Insert a new object (row)
	public int insert(Person person) {

		/*
		 * The jdbcTemplate.update() method executes a query insert into person (id, name, 
		 * location, birth_date) values(?, ?, ?, ?), maps it to a new Person and returns
		 * number of rows effected.
		 * 
		 * The database column names and the java get methods must be in same corresponding order.
		 */
		return jdbcTemplate.update
				("insert into person (id, name, location, birth_date) values(?, ?, ?, ?)",
				new Object[]{person.getId(), person.getName(), person.getLocation(), 
						new Timestamp(person.getBirthDate().getTime())});
		
	}

	// Update the details of a object (row)
	public int update(Person person) {

		/*
		 * The jdbcTemplate.update() method executes a query "update person set name = ?, location = ?, 
		 * birth_date = ? where id = ? ", maps it to a existing Person and returns number of rows effected.
		 * 
		 * The database column names and the java get methods must be in same corresponding order.
		 */
		return jdbcTemplate.update
				("update person set name = ?, location = ?, birth_date = ? where id = ? ",
				new Object[]{person.getName(), person.getLocation(), 
						new Timestamp(person.getBirthDate().getTime()), person.getId()});
		
	}
}
