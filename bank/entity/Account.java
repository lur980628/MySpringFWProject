package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

public abstract class Account {
    protected String accountNumber;
    protected String ownerName;
    protected double balance;

    public Account(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    // 잔액 증가
    public void deposit(double amount) {
        this.balance += amount;
        System.out.println(amount + "원이 입금되었습니다. 현재 잔액: " + this.balance + "원");
    }

    // 잔액 감소 (하위 클래스에서 구현)
    public abstract void withdraw(double amount) throws InsufficientBalanceException;
    
    // 계좌 정보 출력
    public abstract String toString();

    // getter 메서드
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
}