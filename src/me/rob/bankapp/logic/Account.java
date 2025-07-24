package me.rob.bankapp.logic;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	private Credentials credentials;
	private BigDecimal balance = BigDecimal.ZERO;
	
	private List<Operation> history = new ArrayList<>();

	public Account(String username, String password) { credentials = new Credentials(username, password); }

	public Credentials getCredentials() { return credentials; } 

	public void seeHistory() { for (var operation : history) System.out.println(operation); }

	BigDecimal getBalance() { return balance; }

	void updateBalance(BigDecimal amount) { balance = balance.add(amount); }

	void updateHistory(Operation.Type type, BigDecimal amount) { history.add(new Operation(type, amount)); }

	@Override
	public String toString() { return (credentials + " " + balance + "€"); }
}