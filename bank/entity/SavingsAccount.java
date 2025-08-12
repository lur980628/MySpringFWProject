package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, String ownerName, double initialBalance, double interestRate) {
        super(accountNumber, ownerName, initialBalance);
        this.interestRate = interestRate;
    }

    // 이자 적용 기능
    public void applyInterest() {
        double interest = balance * (interestRate / 100);
        this.deposit(interest); // 이자를 입금 처리
        System.out.println("이자 " + interest + "원이 적용되었습니다. 현재 잔액: " + this.balance + "원");
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (this.balance < amount) {
            throw new InsufficientBalanceException("잔액이 부족합니다.");
        }
        this.balance -= amount;
        System.out.println(amount + "원이 출금되었습니다. 현재 잔액: " + this.balance + "원");
    }

    @Override
    public String toString() {
        return String.format("계좌번호: %s, 소유자: %s, 잔액: %.1f원, 이자율: %.1f%%",
                accountNumber, ownerName, balance, interestRate);
    }
}