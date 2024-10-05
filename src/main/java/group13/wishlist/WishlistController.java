package group13.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

  @Autowired
  private WishlistService wishlistService;

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

  // Get wishlists by userId
  @GetMapping("/user/{userId}")
  public List<Wishlist> getWishlistsByUserId(@PathVariable Long userId) {
    return wishlistService.getWishlistsByUserId(userId);
  }

  // Get wishlists by bookId
  @GetMapping("/book/{bookId}")
  public List<Wishlist> getWishlistsByBookId(@PathVariable Long bookId) {
    return wishlistService.getWishlistsByBookId(bookId);
  }

  // Create a new wishlist
  @PostMapping
  public Wishlist createWishlist(@RequestBody Wishlist wishlist) {
    //need to fetch User here
    return wishlistService.saveWishlist(wishlist);
  }

  // Update an existing wishlist
  @PutMapping("/{id}")
  public Wishlist updateWishlist(@PathVariable Long id, @RequestBody Wishlist wishlistDetails) {
    Optional<Wishlist> wishlist = wishlistService.getWishlistById(id);
    if (wishlist.isPresent()) {
      Wishlist existingWishlist = wishlist.get();
      existingWishlist.setTitle(wishlistDetails.getTitle());
      existingWishlist.setUser(wishlistDetails.getUser());
      existingWishlist.setBook(wishlistDetails.getBook());
      return wishlistService.saveWishlist(existingWishlist);
    }
    return null; // Handle the case where the wishlist is not found
  }

  // Delete a wishlist
  @DeleteMapping("/{id}")
  public void deleteWishlist(@PathVariable Long id) {
    wishlistService.deleteWishlist(id);
  }
}
