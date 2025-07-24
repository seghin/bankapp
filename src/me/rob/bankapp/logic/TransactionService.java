package me.rob.bankapp.logic;

import java.math.BigDecimal;

import java.util.logging.Logger;

public final class TransactionService {

	private static final Logger logger = Logger.getLogger(TransactionService.class.getName());

    public static void deposit(Account account, BigDecimal amount) {

		String username = account.getCredentials().getUsername();

		logger.info("Attempting to make a deposit for user " + username);

		if (amount.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Amount must be positive.");

		if (!Auth.isLoggedIn(username)) throw new IllegalStateException("User is not logged.");

        account.updateBalance(amount);

		account.updateHistory(Operation.Type.DEPOSIT, amount);

		logger.info("Deposit completed.");
    }

	public static void withdraw(Account account, BigDecimal amount) {

		String username = account.getCredentials().getUsername();

		logger.info("Attempting to make a withdraw for user " + username);

		if (amount.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Amount must be positive.");

		if (!Auth.isLoggedIn(username)) throw new IllegalStateException("User is not logged.");

		if (amount.compareTo(account.getBalance()) > 0) throw new IllegalStateException("Insufficient funds.");

        account.updateBalance(amount.negate());

		account.updateHistory(Operation.Type.WITHDRAW, amount);

		logger.info("Withdraw completed!");
    }
}