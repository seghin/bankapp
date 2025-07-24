package me.rob.bankapp.logic;

import java.util.Objects;
import java.io.Serializable;

public class Credentials implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String username, password;

    public Credentials(String username, String password) {
        this.username = Objects.requireNonNull(username);
        this.password = Objects.requireNonNull(password);
    }

    public String getUsername() { return username; }

    String getPassword() { return password; }

    @Override
    public String toString() { return username; }
}