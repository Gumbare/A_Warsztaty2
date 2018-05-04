package packagetwo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import pl.coderslab.models.User;

public class AdministrationProgram {

	private static Connection conn;

	public static void main(String[] args) {
		AdministrationProgram app = new AdministrationProgram();
		app.run();
		UserInput.close();
	}

	public AdministrationProgram() {
		try {
			AdministrationProgram.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school",
					"root", "coderslab");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void run() {
		while (true) {
			User[] users = {};
			try {
				users = User.loadAllUsers(AdministrationProgram.conn);
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(0);
			}
			displayUsers(users);
			userMenu();
		}
	}

	public void displayUsers(User[] users) {
		System.out.println("Uzytkownicy w bazie:");
		System.out.println("id | username | email | password | personGroupId");
		for (User user : users) {
			System.out.println(user.getId() + " | " + user.getUsername() + " | " + user.getEmail() + " | ");
			
		}
	}

	public void userMenu() {
		System.out.println("Wybierz jedną z opcji:");
		System.out.println("add - dodanie użytkownika");
		System.out.println("edit - edycja użytkownika");
		System.out.println("delete - usuwanie użytkownika");
		System.out.println("quit - zakończenie programu");
		System.out.println("Wpisz opcje i zatwierdz enterem");
		Scanner scanner = new Scanner(System.in);
		processInput(scanner.next());
	}

	public void processInput(String str) {

		try {
			switch (str) {

			case "add":
				add();
				break;
			case "edit":
				edit();
				break;
			case "delete":
				System.out.println("Usuwanie użytkownika");
				break;
			case "quit":
				System.out.println("Koniec programu");
				System.exit(0);

			default:
				System.out.println("Koniec programu.");
				System.exit(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	private void add() throws SQLException {
		System.out.println("DODAWANIE Użytkownika");
		String username = UserInput.getString("Wprowadz username:");
		String email = UserInput.getString("Wprowadź email:");
		String password = UserInput.getString("Wprowadz password:");
		User user = new User(username, email, password);
		user.saveToDB(AdministrationProgram.conn);
		System.out.println("Zapisano do bazy. id: " + user.getId());
	}

	private void edit() throws SQLException {
		System.out.println("EDYCJA ELEMENTU");
		Integer id = UserInput.getInt("Wprowadz id:");
		System.out.println("Edytujesz: ");
		User[] users = { User.loadUserById(AdministrationProgram.conn, id) };
		displayUsers(users);
		String username = UserInput.getString("Wprowadz username:");
		String email = UserInput.getString("Wprowadź email:");
		String password = UserInput.getString("Wprowadz password:");
		User user = new User(id, username, email, password);
		user.saveToDB(AdministrationProgram.conn);
		System.out.println("Zapisano do bazy. id: " + user.getId());
	}
}
