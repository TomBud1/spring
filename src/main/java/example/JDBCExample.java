package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import example.entity.Customer;

public class JDBCExample {

	public static void main(String[] args) {
		
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/test", "postgres",
					"postgres");
			System.out.println("connecting");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
			
			try {
				selectRecordsFromDbUserTable(connection);
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			
		} else {
			System.out.println("Failed to make connection!");
		}
		
	}
	
	private static void selectRecordsFromDbUserTable(Connection connection) throws SQLException {
		
		Statement statement=null;
		
		String selectTableSQL = "SELECT * from customer";
		
		try {
		statement=connection.createStatement();
		System.out.println(selectTableSQL);
		
		ResultSet rs = statement.executeQuery(selectTableSQL);
		
			while (rs.next()) {
				
				int id = rs.getInt("ID");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				
				Customer customer = new Customer();
				customer.setId(id);
				customer.setFirstName(firstName);
				customer.setLastName(lastName);
				customer.setEmail(email);
				
				System.out.println("ID: " + id);
				System.out.println("first name: " + firstName);
				System.out.println("last name: " + lastName);
				System.out.println("email: " + email);
				System.out.println("");
				
				System.out.println(customer.toString());
				System.out.println("");
			}
			
		} catch(SQLException e) {
			
			System.out.println(e.getMessage());
		
		} finally {
			
			if(statement != null) {
				statement.close();
			}
			
			if(connection != null) {
				connection.close();
			}
			
		}
	}

}
