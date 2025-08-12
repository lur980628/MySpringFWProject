package mylab.bank.control;

import mylab.bank.entity.Bank;
import mylab.bank.entity.SavingsAccount;
import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class BankDemo {
    public static void main(String[] args) {
        Bank bank = new Bank();

        System.out.println("=== 계좌 생성 ===");
        bank.createSavingsAccount("홍길동", 10000.0, 3.0);
        bank.createCheckingAccount("김철수", 20000.0, 5000.0);
        bank.createSavingsAccount("이영희", 30000.0, 2.0);
        System.out.println();

        bank.listAllAccounts();
        System.out.println();

        // 입금/출금 테스트
        System.out.println("=== 입금/출금 테스트 ===");
        try {
            bank.deposit("AC1000", 5000.0);
            bank.withdraw("AC1001", 3000.0);
        } catch (AccountNotFoundException | InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        System.out.println();
        
        // 이자 적용 테스트
        System.out.println("=== 이자 적용 테스트 ===");
        try {
            SavingsAccount account = (SavingsAccount) bank.findAccount("AC1000");
            account.applyInterest();
        } catch (AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        System.out.println();

        // 계좌 이체 테스트
        System.out.println("=== 계좌 이체 테스트 ===");
        try {
            bank.transfer("AC1002", "AC1001", 5000.0);
        } catch (AccountNotFoundException | InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        System.out.println();

        bank.listAllAccounts();
        System.out.println();

        // 예외 처리 테스트
        System.out.println("=== 예외 처리 테스트 ===");
        try {
            bank.withdraw("AC1001", 6000.0);
        } catch (WithdrawalLimitExceededException e) {
            System.out.println("예외 발생: " + e.getMessage());
        } catch (InsufficientBalanceException | AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        
        try {
            bank.withdraw("AC1001", 30000.0);
        } catch (InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        } catch (AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        
        try {
            bank.withdraw("AC9999", 1000.0);
        } catch (AccountNotFoundException | InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}