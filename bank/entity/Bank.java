package mylab.bank.entity;

import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;
import java.util.ArrayList;

public class Bank {
    private ArrayList<Account> accounts;
    private int nextAccountNumber;

    public Bank() {
        this.accounts = new ArrayList<>();
        this.nextAccountNumber = 1000;
    }

    private String createAccountNumber() {
        return "AC" + nextAccountNumber++;
    }

    // 저축 계좌 생성
    public SavingsAccount createSavingsAccount(String ownerName, double initialBalance, double interestRate) {
        String accountNumber = createAccountNumber();
        SavingsAccount newAccount = new SavingsAccount(accountNumber, ownerName, initialBalance, interestRate);
        accounts.add(newAccount);
        System.out.println("Saving(저축) 계좌가 생성되었습니다: " + newAccount);
        return newAccount;
    }

    // 체킹 계좌 생성
    public CheckingAccount createCheckingAccount(String ownerName, double initialBalance, double withdrawalLimit) {
        String accountNumber = createAccountNumber();
        CheckingAccount newAccount = new CheckingAccount(accountNumber, ownerName, initialBalance, withdrawalLimit);
        accounts.add(newAccount);
        System.out.println("체킹 계좌가 생성되었습니다: " + newAccount);
        return newAccount;
    }
    
    // 계좌번호로 계좌 검색
    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new AccountNotFoundException("계좌번호 " + accountNumber + "에 해당하는 계좌를 찾을 수 없습니다.");
    }
    
    // 입금
    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        findAccount(accountNumber).deposit(amount);
    }

    // 출금
    public void withdraw(String accountNumber, double amount) throws AccountNotFoundException, InsufficientBalanceException {
        findAccount(accountNumber).withdraw(amount);
    }
    
    // 계좌 이체
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) throws AccountNotFoundException, InsufficientBalanceException {
        Account fromAccount = findAccount(fromAccountNumber);
        Account toAccount = findAccount(toAccountNumber);

        // 출금 후 입금 (출금 과정에서 예외 발생 가능)
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        System.out.printf("%.1f원이 %s에서 %s로 송금되었습니다.%n", amount, fromAccountNumber, toAccountNumber);
    }

    // 모든 계좌 정보 출력
    public void listAllAccounts() {
        System.out.println("=== 모든 계좌 목록 ===");
        for (Account account : accounts) {
            System.out.println(account);
        }
        System.out.println("===================");
    }
}