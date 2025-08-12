package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class CheckingAccount extends Account {
    private double withdrawalLimit;

    public CheckingAccount(String accountNumber, String ownerName, double initialBalance, double withdrawalLimit) {
        super(accountNumber, ownerName, initialBalance);
        this.withdrawalLimit = withdrawalLimit;
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > this.withdrawalLimit) {
            throw new WithdrawalLimitExceededException("출금 한도를 초과했습니다. 한도: " + this.withdrawalLimit + "원");
        }
        if (this.balance < amount) {
            throw new InsufficientBalanceException("잔액이 부족합니다.");
        }
        this.balance -= amount;
        System.out.println(amount + "원이 출금되었습니다. 현재 잔액: " + this.balance + "원");
    }

    @Override
    public String toString() {
        return String.format("계좌번호: %s, 소유자: %s, 잔액: %.1f원, 출금 한도: %.1f원",
                accountNumber, ownerName, balance, withdrawalLimit);
    }
}