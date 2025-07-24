package me.rob.bankapp.logic;

import me.rob.bankapp.utils.PasswordUtils;

import java.util.HashMap;
import java.util.Map;

public final class Auth {

    private final static Map<String, Account> loggedInUsers = new HashMap<>();

    public static Account login(String username, String password, Repository repository) {

        var account = repository.search(username);

        if (account == null) throw new IllegalStateException("Account does not exist.");

        if (!PasswordUtils.verify(password, account.getCredentials().getPassword())) throw new IllegalArgumentException("Wrong password.");

        loggedInUsers.put(username, account);

        return account;
    }

    public static void logout(String username) { if (!loggedInUsers.containsKey(username)) loggedInUsers.remove(username); }

    public static boolean isLoggedIn(String username) { return loggedInUsers.containsKey(username); }

    public static Account getLoggedInAccount(String username) { return loggedInUsers.get(username); }
}