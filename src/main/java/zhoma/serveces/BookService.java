package zhoma.serveces;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zhoma.DTO.BookRequest;
import zhoma.model.Book;
import zhoma.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public void addBook(BookRequest request) {
        Book book = Book.builder().
                title(request.getTitle())
                .author(request.getAuthor())
                .owner(null)
                .isAvailable(true)
                .build();
        repository.save(book);
    }
    public void saveBook(Book book){
        repository.save(book);
    }


    public List<Book> getAll() {
        return repository.findAll();
    }

    public Optional<Book> getByID(int bookID) {
        return repository.findById(bookID);
    }
}
