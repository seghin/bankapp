package me.rob.bankapp.logic;

import me.rob.bankapp.persistence.Database;

import java.util.List;

public class Repository {

    private Database<Account> database;

    private List<Account> accounts;

    public Repository(Database<Account> database) { 
        
        this.database = database; 
        
        accounts = database.load();    
    }

    public List<Account> getAccounts() { return List.copyOf(accounts); }

    public Account search(String username) {

        if (username == null || username.isBlank()) throw new IllegalArgumentException("Username cannot be empty.");

        if (accounts.isEmpty()) return null;

        for (var account : accounts) if (account.getCredentials().getUsername().equals(username)) return account;
    
        return null;
    }

    public void add(String username, String password) {

        if (search(username) != null) throw new IllegalArgumentException("Account already exists.");

        accounts.add(new Account(username, password));

        database.save(accounts);
    }

    public void remove(String username) {

        if (!accounts.removeIf(account -> account.getCredentials().getUsername().equals(username))) throw new IllegalArgumentException("Account does not exist.");
        
        database.save(accounts);
    }
}