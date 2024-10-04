package group13.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
  Wishlist findByTitle(String title);

  //List<Wishlist> findByUserId(Long userId);
}
