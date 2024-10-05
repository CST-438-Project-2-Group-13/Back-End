package group13.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

  // Find wishlists by userId
  List<Wishlist> findByUserUserId(Long userId);

  // Find wishlists by bookId
  List<Wishlist> findByBookId(Long bookId);
}

