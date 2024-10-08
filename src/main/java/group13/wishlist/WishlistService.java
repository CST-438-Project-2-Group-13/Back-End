package group13.wishlist;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

  @Autowired
  private WishlistRepository wishlistRepository;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private UserRepository userRepository;

  // Get all wishlists
  public List<Wishlist> getAllWishlists() {
    return wishlistRepository.findAll();
  }

  public Wishlist createWishlist(int userId, String title, String description) {
    User user = userRepository.findById((long) userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    Wishlist wishlist = new Wishlist();
    wishlist.setUser(user);
    wishlist.setTitle(title);
    wishlist.setDescription(description);
    return wishlistRepository.save(wishlist);
  }

  public void addBookToWishlist(Long wishlistId, Long bookId) {
    Wishlist wishlist = wishlistRepository.findById(wishlistId)
        .orElseThrow(() -> new ResourceNotFoundException("Wishlist not found"));
    Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    wishlist.getBooks().add(book);
    wishlistRepository.save(wishlist);
  }

  public List<Wishlist> getWishlistsByUser(int userId) {
    User user = userRepository.findById((long) userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    return wishlistRepository.findByUser(user);
  }

  // Get wishlist by ID
  public Optional<Wishlist> getWishlistById(Long id) {
    return wishlistRepository.findByWishlistId(id);
  }

  // Get wishlists by userId
  public List<Wishlist> getWishlistsByUserId(int userId) {
    return wishlistRepository.findByUserUserId(userId);
  }

  // Create or update a wishlist
  public Wishlist saveWishlist(Wishlist wishlist) {
    return wishlistRepository.save(wishlist);
  }

  // Delete a wishlist by ID
  public void deleteWishlist(Long wishlistId) {
    wishlistRepository.deleteById(wishlistId);
  }
}

