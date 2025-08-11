package mylab.library.control;

import mylab.library.entity.Book;
import mylab.library.entity.Library;

import java.util.List;

public class LibraryManagementSystem {

    private static Library centralLibrary;

    public static void main(String[] args) {
        // a. 도서관 객체 생성
        centralLibrary = new Library("중앙 도서관");

        // b. 샘플 도서 추가
        addSampleBooks(centralLibrary);

        // c. 도서관 정보 출력
        System.out.println("\n===== " + centralLibrary.getName() + " =====\n");
        displayLibraryStatus(centralLibrary);

        // d. 기능 테스트
        System.out.println("\n===== 도서 검색 테스트 =====\n");
        testFindBook(centralLibrary);

        System.out.println("\n===== 도서 대출 테스트 =====\n");
        testCheckOut(centralLibrary);
        
        System.out.println("\n===== 도서 반납 테스트 =====\n");
        testReturn(centralLibrary);
        
        // e. 대출 가능한 도서 목록 출력
        System.out.println("\n===== 대출 가능한 도서 목록 =====\n");
        displayAvailableBooks(centralLibrary);
    }
    
    // 샘플 도서 추가 메서드
    private static void addSampleBooks(Library library) {
        library.addBook(new Book("자바 프로그래밍", "김자바", "978-89-01-12345-6", 2022));
        library.addBook(new Book("객체지향의 사실과 오해", "조영호", "978-89-01-67890-1", 2015));
        library.addBook(new Book("Clean Code", "Robert C. Martin", "978-0-13-235088-4", 2008));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "978-0-13-468599-1", 2018));
        library.addBook(new Book("Head First Java", "Kathy Sierra", "978-0-596-00920-5", 2005));
        library.addBook(new Book("자바의 정석", "남궁성", "978-89-01-14077-4", 2019));
    }

    // 도서관 상태 출력 메서드
    private static void displayLibraryStatus(Library library) {
        System.out.println("전체 도서 수: " + library.getTotalBooks());
        System.out.println("대출 가능 도서 수: " + library.getAvailableBooksCount());
        System.out.println("대출 중인 도서 수: " + library.getBorrowedBooksCount());
    }

    // 도서 검색 테스트
    private static void testFindBook(Library library) {
        System.out.println("제목으로 검색 결과:");
        Book bookByTitle = library.findByTitle("자바의 정석");
        if (bookByTitle != null) {
            System.out.println(bookByTitle.toString() + "\n");
        }
        
        System.out.println("저자로 검색 결과:");
        List<Book> booksByAuthor = library.findByAuthor("Robert C. Martin");
        if (!booksByAuthor.isEmpty()) {
            for (Book book : booksByAuthor) {
                System.out.println(book.toString() + "\n");
            }
        }
    }

    // 도서 대출 테스트
    private static void testCheckOut(Library library) {
        String isbnToCheckout = "978-89-01-14077-4";
        
        if (library.checkOutBook(isbnToCheckout)) {
            System.out.println("도서 대출 성공!");
            Book borrowedBook = library.findByISBN(isbnToCheckout);
            if (borrowedBook != null) {
                System.out.println("대출된 도서 정보:");
                System.out.println(borrowedBook.toString());
            }
        } else {
            System.out.println("도서 대출 실패!");
        }

        System.out.println("\n도서관 현재 상태:");
        displayLibraryStatus(library);
    }
    
    // 도서 반납 테스트
    private static void testReturn(Library library) {
        String isbnToReturn = "978-89-01-14077-4";

        if (library.returnBook(isbnToReturn)) {
            System.out.println("도서 반납 성공!");
            Book returnedBook = library.findByISBN(isbnToReturn);
            if (returnedBook != null) {
                System.out.println("반납된 도서 정보:");
                System.out.println(returnedBook.toString());
            }
        } else {
            System.out.println("도서 반납 실패!");
        }
        
        System.out.println("\n도서관 현재 상태:");
        displayLibraryStatus(library);
    }

    // 대출 가능한 도서 목록 출력
    private static void displayAvailableBooks(Library library) {
        List<Book> availableBooks = library.getAvailableBooks();
        for (Book book : availableBooks) {
            System.out.println(book.toString());
            System.out.println("------------------------");
        }
    }
}