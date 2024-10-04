package group13.wishlist;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;

@Entity
public class Wishlist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long wishlistId;

  private String title;

  // Many-to-one relationship with User entity
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_user_wishlist"))
  private User user;

  private Long bookId;

  // Constructors
  public Wishlist() {
  }

  public Wishlist(String title, User user, Long bookId) {
    this.title = title;
    this.user = user;
    this.bookId = bookId;
  }

  // Getters and Setters
  public Long getWishlistId() {
    return wishlistId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Long getBookId() {
    return bookId;
  }


}

