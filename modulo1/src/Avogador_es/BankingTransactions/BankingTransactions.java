package Avogador_es.BankingTransactions;

abstract class Transaction {
	private final double amount;
	
	protected Transaction(double amount) {
		this.amount = amount;
	}
	
	public double getAmount() {
		return amount;
	}
}

class Withdrawal extends Transaction {

	public Withdrawal(double amount) {
		super(amount);
	}
}

class WireTransfer extends Transaction {

	public WireTransfer(double amount) {
		super(amount);
	}
}

class Payment extends Transaction {
	private final double commission;

	public Payment(double amount, double commission) {
		super(amount);
		this.commission = commission;
	}
	
	public double getCommission() {
		return commission;
	}
}

class Bank {
    public static double accountBalance(double initialAmount, Transaction[] transactions) {
        transactions = transactions.clone();
        double balance = initialAmount;
        for (Transaction transaction : transactions) {
            if (transaction instanceof Withdrawal) {
                balance -= transaction.getAmount();
            } else if (transaction instanceof WireTransfer) {
                balance -= transaction.getAmount();
            } else if (transaction instanceof Payment) {
                Payment payment = (Payment) transaction;
                balance -= payment.getAmount();
                balance -= payment.getCommission();
            }
        }
        return balance;
    }
}