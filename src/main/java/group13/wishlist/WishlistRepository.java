package group13.wishlist;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

  List<Wishlist> findByUser(User user);

  // Find wishlists by userId
  List<Wishlist> findByUserUserId(int userId);

  Optional<Wishlist> findByWishlistId(Long id);
}

