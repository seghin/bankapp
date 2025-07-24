package me.rob.bankapp.logic;

import java.time.LocalDateTime;
import java.math.BigDecimal;

class Operation {

	enum Type { DEPOSIT, WITHDRAW, TRANSFER }

	private final Type type;
	private final BigDecimal amount;
	private final LocalDateTime timestamp;

	Operation(Type type, BigDecimal amount) {

		this.type = type;

		this.amount = amount;

		this.timestamp = LocalDateTime.now();
	}

	public String toString() { return (timestamp + ": " + type + " of " + amount + "€"); }
}