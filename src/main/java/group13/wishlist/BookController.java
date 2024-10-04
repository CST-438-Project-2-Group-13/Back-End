package group13.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

  @Autowired
  private BookService bookService;

  // Get all books
  @GetMapping
  public List<Book> getAllBooks() {
    return bookService.getAllBooks();
  }

  // Get a book by title
  @GetMapping("/{title}")
  public Book getBookByTitle(@PathVariable String title) {
    return bookService.getBookByTitle(title);
  }

  // Add a new book
  @PostMapping
  public Book addBook(@RequestBody Book book) {
    return bookService.saveBook(book);
  }

  // Update a book
  @PutMapping("/{id}")
  public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
    Book book = bookService.getBookByTitle(bookDetails.getTitle());  // Fetch current book by title, can be done by id as well
    if (book != null) {
      book.setAuthors(bookDetails.getAuthors());
      book.setDescription(bookDetails.getDescription());
      book.setPageCount(bookDetails.getPageCount());
      book.setCategories(bookDetails.getCategories());
      book.setThumbnail(bookDetails.getThumbnail());
      book.setSmallThumbnail(bookDetails.getSmallThumbnail());
      return bookService.updateBook(book);
    }
    return null;
  }

  // Delete a book
  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
  }
}

