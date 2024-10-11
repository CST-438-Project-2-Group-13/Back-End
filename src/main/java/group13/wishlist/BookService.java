package group13.wishlist;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  // Fetch all books
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public List<Book> searchBooks(String title, String author, String category) {
    if (title != null) {
      return bookRepository.findByTitleContaining(title);
    } else if (author != null) {
      return bookRepository.findByAuthorsContaining(author);
    } else if (category != null) {
      return bookRepository.findByCategoriesContaining(category);
    }
    return getAllBooks();
  }

  // Fetch a book by title
  public Book getBookByTitle(String title) {
    return bookRepository.findByTitle(title);
  }

  public Book getBookByAuthors(String authors) {
    return bookRepository.findByAuthors(authors);
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

