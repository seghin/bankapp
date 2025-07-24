package me.rob.bankapp.logic;

import me.rob.bankapp.utils.PasswordUtils;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

public final class AccountService {

    private static Logger logger = Logger.getLogger(AccountService.class.getName());

	public static void register(String username, String password, Repository repository) {

        logger.info("Attempting to register user " + username + ".");

        var account = repository.search(username);

        if (account != null) throw new IllegalStateException("Account already exists.");

        if (!PasswordUtils.isValid(password)) throw new IllegalArgumentException("Password must respect the rules.");

        repository.add(username, PasswordUtils.hash(password));

        logger.info("User " + username + "registered successfully.");
    }

    public static void delete(String username, String password, Repository repository) {

        logger.info("Attempting to delete user " + username + ".");

        var account = repository.search(username);

        if (account == null) throw new IllegalStateException("Account does not exist.");

        if (!PasswordUtils.verify(password, account.getCredentials().getPassword())) throw new IllegalArgumentException("Wrong password.");

        repository.remove(username);

        logger.info("User " + username + " deleted successfully.");
    }
}