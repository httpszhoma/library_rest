package zhoma.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zhoma.DTO.BookRequest;
import zhoma.DTO.BorrowAndReturnRequest;
import zhoma.DTO.UserRequest;
import zhoma.model.Book;
import zhoma.model.User;
import zhoma.serveces.LibraryService;

import java.util.List;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping()
    public List<String> allServices() {
        return List.of(
                "addbook",
                "adduser",
                "allusers",
                "allbooks",
                "borrowbook",
                "returnbook",
                "allAvailableBooks",
                "allNotAvailableBooks",
                "userdelete",
                "bookdelete"
        );
    }


    @PostMapping("/addbook")
    public ResponseEntity<String> addBook(@RequestBody BookRequest request) {
        libraryService.addNewBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book added successfully");
    }

    @PostMapping("/adduser")
    public ResponseEntity<String> addUser(@RequestBody UserRequest request) {
        libraryService.addNewUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
    }

    @GetMapping("/allusers")
    public List<User> getAllUser() {
        return libraryService.getAllUser();
    }

    @GetMapping("/allbooks")
    public List<Book> getAllBook() {
        return libraryService.getAllBook();
    }

    @PostMapping("/borrowbook")
    public ResponseEntity<String> borrowBook(@RequestBody BorrowAndReturnRequest request) {
        libraryService.borrowBook(request);
        return ResponseEntity.status(HttpStatus.OK).body("Book with id = " + request.getBookID() + " successfully borrowed by User with id = " + request.getUserID() + "; ");
    }

    @PostMapping("/returnbook")
    public ResponseEntity<String> returnBook(@RequestBody BorrowAndReturnRequest request) {
        libraryService.returnBook(request);
        return ResponseEntity.status(HttpStatus.OK).body("Book with id = "+ request.getBookID()+ "  successfully returned by User with id = " + request.getUserID() + "; ");
    }

    @GetMapping("/allAvailableBooks")
    public List<Book> AllAvailableBooks(){
        return libraryService.getAllAvailableBooks();
    }

    @GetMapping("/allNotAvailableBooks")
    public List<Book> allNotAvailableBooks(){
        return libraryService.getAllNotAvailableBooks();
    }

    @DeleteMapping("/userdelete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id")int id){
        libraryService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully !!!");
    }


    @DeleteMapping("/bookdelete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable(name = "id")int id){
        libraryService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully !!!");
    }



}
