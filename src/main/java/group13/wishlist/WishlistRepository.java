package group13.wishlist;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

  // Find wishlists by userId
  List<Wishlist> findByUserUserId(Long userId);

  // Find wishlists by bookId
  List<Wishlist> findByBookId(Long bookId);

  Optional<Wishlist> findByWishlistId(Long id);
}

