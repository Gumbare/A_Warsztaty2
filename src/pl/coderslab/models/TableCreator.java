package pl.coderslab.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreator {

	// creating tables in previously prepared 'school' data base
	private static final String query1 = "CREATE TABLE user_group(id int(11) AUTO_INCREMENT,\n"
			+ "                   name varchar(255),\n" + "                   PRIMARY KEY(id));";
	private static final String query2 = "CREATE TABLE users(id bigint(20) AUTO_INCREMENT,\n"
			+ "                   username varchar(255),\n" + "                   email varchar(255) UNIQUE,\n"
			+ "                   password varchar(245),\n" + "                   user_group_id int(11),\n"
			+ "                   PRIMARY KEY(id),\n"
			+ "                   FOREIGN KEY(user_group_id) REFERENCES user_group(id) ON DELETE CASCADE);";
	private static final String query3 = "CREATE TABLE exercise(id int(11) AUTO_INCREMENT,\n"
			+ "                      title varchar(255),\n" + "                      description TEXT,\n"
			+ "                      PRIMARY KEY(id));";
	private static final String query4 = "CREATE TABLE solution(id int(11) AUTO_INCREMENT,\n"
			+ "                      created DATETIME,\n" + "                      updated DATETIME,\n"
			+ "                      description TEXT,\n" + "                      exercise_id int(11),\n"
			+ "                      users_id bigint(20),\n" + "                      PRIMARY KEY(id),\n"
			+ "                      FOREIGN KEY(exercise_id) REFERENCES exercise(id) ON DELETE CASCADE,\n"
			+ "                      FOREIGN KEY(users_id) REFERENCES users(id) ON DELETE CASCADE);";

	public static void main(String[] args) {
		// creating Connection
		try (Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/school?useSSL=false", "root", "coderslab")) {

			Statement stm = conn.createStatement();
			stm.executeUpdate(query1);
			stm.executeUpdate(query2);
			stm.executeUpdate(query3);
			stm.executeUpdate(query4);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
