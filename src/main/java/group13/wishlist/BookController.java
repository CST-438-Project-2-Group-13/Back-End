package group13.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

  @Autowired
  private BookService bookService;

  // Get a book by title
  @GetMapping("title/{title}")
  public Book getBookByTitle(@PathVariable String title) {
    return bookService.getBookByTitle(title);
  }

  @GetMapping("authors/{authors}")
  public Book getBookByAuthors(@PathVariable String authors) {
    return bookService.getBookByAuthors(authors);
  }

  @GetMapping
  public List<Book> searchBooks(
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String authors,
      @RequestParam(required = false) String category) {

    // If no query parameters are provided, return all books
    if (title == null && authors == null && category == null) {
      return bookService.getAllBooks();
    }

    // Otherwise, return filtered results
    return bookService.searchBooks(title, authors, category);
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

