package group13.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  // Fetch all books
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  // Fetch a book by title
  public Book getBookByTitle(String title) {
    return bookRepository.findByTitle(title);
  }

  // Save a new book
  public Book saveBook(Book book) {
    return bookRepository.save(book);
  }

  // Update an existing book
  public Book updateBook(Book book) {
    return bookRepository.save(book);
  }

  // Delete a book
  public void deleteBook(Long id) {
    bookRepository.deleteById(id);
  }
}

