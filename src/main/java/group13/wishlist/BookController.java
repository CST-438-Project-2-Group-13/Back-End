package group13.wishlist;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping("/searchBooks")
  public List<Book> searchBooks(@RequestParam String query) {
    String apiUrl = "https://www.googleapis.com/books/v1/volumes?key=AIzaSyBSouM1nC4vrRwjs4BlIftlB0QCrw5X0Ss&maxResults=24&q=" + query;
    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(apiUrl, String.class);

    // Parse the JSON response
    JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
    JsonArray items = jsonObject.getAsJsonArray("items");

    List<Book> books = new ArrayList<>();
    for (int i = 0; i < items.size(); i++) {
      JsonObject item = items.get(i).getAsJsonObject();
      JsonObject volumeInfo = item.getAsJsonObject("volumeInfo");

      // Extract relevant data
      String title = volumeInfo.get("title").getAsString();
      JsonArray authorsArray = volumeInfo.getAsJsonArray("authors");
      StringBuilder authorsBuilder = new StringBuilder();
      if (authorsArray != null) {
        for (int j = 0; j < authorsArray.size(); j++) {
          authorsBuilder.append(authorsArray.get(j).getAsString()).append(", ");
        }
      }
      String authors = authorsBuilder.length() > 0 ? authorsBuilder.substring(0, authorsBuilder.length() - 2) : "";
      String description = volumeInfo.has("description") ? volumeInfo.get("description").getAsString() : "No description available";
      Integer pageCount = volumeInfo.has("pageCount") ? volumeInfo.get("pageCount").getAsInt() : null;
      String categories = volumeInfo.has("categories") ? volumeInfo.getAsJsonArray("categories").get(0).getAsString() : "";
      JsonObject imageLinks = volumeInfo.has("imageLinks") ? volumeInfo.getAsJsonObject("imageLinks") : null;
      String thumbnail = imageLinks != null && imageLinks.has("thumbnail") ? imageLinks.get("thumbnail").getAsString() : null;
      String smallThumbnail = imageLinks != null && imageLinks.has("smallThumbnail") ? imageLinks.get("smallThumbnail").getAsString() : null;

      // Create Book object
      books.add(new Book(title, authors, description, pageCount, categories, thumbnail, smallThumbnail));
    }
    System.out.println(books);
    return books;
  }

  
  @GetMapping("/api/books")
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
  @PostMapping("/api/books")
  public Book addBook(@RequestBody Book book) {
    return bookService.saveBook(book);
  }

  // Update a book
  @PutMapping("(/api/books/{id}")
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
  @DeleteMapping("/api/books/{id}")
  public void deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
  }
}

