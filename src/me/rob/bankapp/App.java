package me.rob.bankapp.app;

import me.rob.bankapp.logic.Account;
import me.rob.bankapp.logic.AccountService;
import me.rob.bankapp.logic.TransactionService;
import me.rob.bankapp.logic.Auth;
import me.rob.bankapp.logic.Repository;
import me.rob.bankapp.persistence.FileDatabase;
import me.rob.bankapp.utils.Console;

public class App {

	public static void main(String[] args) {

		var repository = new Repository(new FileDatabase<>("accounts.txt"));

		Account account = null;

		int choice;

		do {

			System.out.println("Menu:");
			System.out.println("0. Exit");
			System.out.println("1. Create account");
			System.out.println("2. Login");
			System.out.println("3. See history");
			System.out.println("4. Deposit");
			System.out.println("5. Withdraw");

			choice = Console.getInteger("Enter your choice: ", false);

			switch (choice) {

				case 1, 2 -> {

					var username = Console.getString("Insert username: ", false);

					var password = Console.getString("Insert password: ", false);

					switch (choice) {
					
						case 1 -> AccountService.register(username, password, repository);

						case 2 -> account = Auth.login(username, password, repository);

					}
				}

				case 3 -> { if (account != null) account.seeHistory(); }

				case 4, 5 -> { 

					var amount = Console.getBigDecimal("Insert amount: ", false);

					switch (choice) {

						case 4 -> TransactionService.deposit(account, amount);

						case 5 -> TransactionService.withdraw(account, amount);

					}
				}
			}

		} while (choice != 0);

		Auth.logout(account.getCredentials().getUsername());
	}
}