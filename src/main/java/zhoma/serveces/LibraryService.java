package zhoma.serveces;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zhoma.DTO.BookRequest;
import zhoma.DTO.BorrowAndReturnRequest;
import zhoma.DTO.UserRequest;
import zhoma.exceptions.*;
import zhoma.model.Book;
import zhoma.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final BookService bookService;
    private final UserService userService;

    public void addNewBook(BookRequest request) {
        bookService.addBook(request);
    }

    public void addNewUser(UserRequest request) {
        userService.addUser(request);
    }

    public List<User> getAllUser() {
        return userService.getAll();
    }

    public List<Book> getAllBook() {
        return bookService.getAll();
    }

    public void borrowBook(BorrowAndReturnRequest request) {
        int userID = request.getUserID();
        int bookID = request.getBookID();

        User user = userService.getByID(userID)
                .orElseThrow(() -> new UserNotFoundException("User with id = " + userID + " doesn't exist!!!"));

        Book book = bookService.getByID(bookID)
                .orElseThrow(() -> new BookNotFoundException("Book with id = " + bookID + " not found !!!"));

        if (book.isAvailable()) {
            user.getBorrowedBooks().add(book);
            book.setOwner(user);
            book.setAvailable(false);

            bookService.saveBook(book);
            userService.saveUser(user);
        } else throw new BookTakenException(" Book with id = " + bookID + "  already taken  !!!");
    }


    public void returnBook(BorrowAndReturnRequest request) {
        int userID = request.getUserID();
        int bookID = request.getBookID();

        User user = userService.getByID(userID)
                .orElseThrow(() -> new UserNotFoundException("User with id = " + userID + " doesn't exist!!!"));

        Book book = bookService.getByID(bookID)
                .orElseThrow(() -> new BookNotFoundException("Book with id = " + bookID + " not found !!!"));

        if (!book.isAvailable()) {
            user.getBorrowedBooks().remove(book);
            book.setOwner(null);
            book.setAvailable(true);

            bookService.saveBook(book);
            userService.saveUser(user);
        } else throw new BookNotTakenException("Book with id = " + bookID + " not taken, it is already in the library!!!");


    }


    public List<Book> getAllAvailableBooks() {
        List<Book> bookList = bookService.getAll();
        List<Book> availableBooks = new ArrayList<>();

        for (Book book : bookList) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }

        return availableBooks;
    }
    public List<Book> getAllNotAvailableBooks() {
        List<Book> bookList = bookService.getAll();
        List<Book> notavailableBooks = new ArrayList<>();

        for (Book book : bookList) {
            if (!book.isAvailable()) {
                notavailableBooks.add(book);
            }
        }

        return notavailableBooks;
    }

    public void deleteUser(int id) {
        userService.deleteUserByID(id);

    }

    public void deleteBook(int id) {
        bookService.deleteBookByID(id);
    }

    public void updateUser(UserRequest request,int id) {
        userService.updateUser(request,id);
    }
}
