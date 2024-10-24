package group13.wishlist;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

  @Autowired
  private WishlistService wishlistService;

  @PostMapping
  public ResponseEntity<Wishlist> createWishlist(@RequestParam int userId,
      @RequestParam String name,
      @RequestParam String description) {
    Wishlist wishlist = wishlistService.createWishlist(userId, name, description);
    return ResponseEntity.ok(wishlist);
  }

  @PostMapping("/{wishlistId}/books/{bookId}")
  public ResponseEntity<?> addBookToWishlist(@PathVariable Long wishlistId,
      @PathVariable Long bookId) {
    wishlistService.addBookToWishlist(wishlistId, bookId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/user/{userId}")
  public List<Wishlist> getWishlistsByUser(@PathVariable int userId) {
    return wishlistService.getWishlistsByUser(userId);
  }

  // Get all wishlists
  @GetMapping
  public List<Wishlist> getAllWishlists() {
    return wishlistService.getAllWishlists();
  }

  // Get a wishlist by ID
  @GetMapping("/{id}")
  public Optional<Wishlist> getWishlistById(@PathVariable Long id) {
    return wishlistService.getWishlistById(id);
  }

  // Update an existing wishlist
  @PutMapping("/{id}")
  public Wishlist updateWishlist(@PathVariable Long id, @RequestBody Wishlist wishlistDetails) {
    Optional<Wishlist> wishlist = wishlistService.getWishlistById(id);
    if (wishlist.isPresent()) {
      Wishlist existingWishlist = wishlist.get();
      existingWishlist.setTitle(wishlistDetails.getTitle());
      existingWishlist.setUser(wishlistDetails.getUser());
      return wishlistService.saveWishlist(existingWishlist);
    }
    return null; // Handle the case where the wishlist is not found
  }

  //delete a book from a wishlist
  @DeleteMapping("/{wishlistId}/books/{bookId}")
  public ResponseEntity<?> removeBookFromWishlist(@PathVariable Long wishlistId, @PathVariable Long bookId) {
    try {
      wishlistService.removeBookFromWishlist(wishlistId, bookId);
      return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
    } catch (Exception e) {
      return ResponseEntity.notFound().build(); // Return 404 if book or wishlist not found
    }
  }

  // Delete a wishlist
  @DeleteMapping("/{id}")
  public void deleteWishlist(@PathVariable Long id) {
    wishlistService.deleteWishlist(id);
  }
}
