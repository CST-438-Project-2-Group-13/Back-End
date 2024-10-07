package group13.wishlist;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

  @Autowired
  private WishlistRepository wishlistRepository;

  // Get all wishlists
  public List<Wishlist> getAllWishlists() {
    return wishlistRepository.findAll();
  }

  // Get wishlist by ID
  public Optional<Wishlist> getWishlistById(Long id) {
    return wishlistRepository.findByWishlistId(id);
  }

  // Get wishlists by userId
  public List<Wishlist> getWishlistsByUserId(Long userId) {
    return wishlistRepository.findByUserUserId(userId);
  }

  // Get wishlists by bookId
  public List<Wishlist> getWishlistsByBookId(Long bookId) {
    return wishlistRepository.findByBookId(bookId);
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

