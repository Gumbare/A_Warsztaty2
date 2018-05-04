package packagetwo;

import java.util.Scanner;

public class UserInput {

	private static Scanner scanner = new Scanner(System.in);

	public static String getString(String msg) {
		System.out.println(msg);
		return scanner.nextLine();
	}

	public static String getEmail(String msg) {
		while (true) {
			System.out.println(msg);
			scanner.next();
			if (scanner.hasNext("\\w+@\\w+\\.\\w+")) {
				String input = scanner.next();
				return input;
			} else {
				System.out.println("Wprowadź poprawny email");
				scanner.next("Wprowadź poprawny email");
			}
		}
	}

	public static String getPassword(String msg) {
		while (true) {
			System.out.println(msg);
			scanner.nextLine();
			if (scanner.hasNextLine()) {
				String input = scanner.nextLine();
				return input;
			} else {
				System.out.println("Wprowadź poprawne hasło");
				scanner.nextLine();
			}

		}
	}

	public static int getInt(String msg) {

		System.out.println(msg);

		while (!scanner.hasNextInt()) {
			scanner.next();
			System.out.println("To nie jest liczba. Podaj jeszcze raz:");
		}

		return scanner.nextInt();
	}

	public static double getDouble(String msg) {

		System.out.println(msg);

		while (!scanner.hasNextDouble()) {
			scanner.next();
			System.out.println("To nie jest liczba. Podaj jeszcze raz:");
		}

		return scanner.nextDouble();
	}

	public static void close() {
		UserInput.scanner.close();
	}
}