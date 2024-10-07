package group13.wishlist;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Wishlist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "wishlist_id")  // Map it explicitly to the wishlistId column
  private Long wishlistId;  // Primary key, auto-incremented

  private String title;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "userId")
  private User user;  // Many-to-One relationship with User

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;

  // Constructors, Getters, and Setters
  public Wishlist() {}

  public Wishlist(String title, User user, Book book) {
    this.title = title;
    this.user = user;
    this.book = book;
  }

  public Long getWishlistId() {
    return wishlistId;
  }

  public void setWishlistId(Long wishlistId) {
    this.wishlistId = wishlistId;
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

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }
}
