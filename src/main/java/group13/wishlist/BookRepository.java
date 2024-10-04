package group13.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  // Additional custom queries can be added here, e.g., find by title, author, etc.
  Book findByTitle(String title);

  Book findByAuthors(String authors);
}

