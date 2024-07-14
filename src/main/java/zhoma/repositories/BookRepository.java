package zhoma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhoma.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
