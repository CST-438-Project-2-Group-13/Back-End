
package group13.wishlist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 255)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String authors;

  @Column(columnDefinition = "TEXT")
  private String description;

  private Integer pageCount;

  @Column(columnDefinition = "TEXT")
  private String categories;

  @Column(length = 255)
  private String thumbnail;

  @Column(length = 255)
  private String smallThumbnail;

  //@ManyToMany(mappedBy = "books")
  //private Set<Wishlist> wishlists = new HashSet<>();

  // Constructors, Getters, and Setters
  public Book() {}

  public Book(String title, String authors, String description, Integer pageCount, String categories, String thumbnail, String smallThumbnail) {
    this.title = title;
    this.authors = authors;
    this.description = description;
    this.pageCount = pageCount;
    this.categories = categories;
    this.thumbnail = thumbnail;
    this.smallThumbnail = smallThumbnail;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthors() {
    return authors;
  }

  public void setAuthors(String authors) {
    this.authors = authors;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getPageCount() {
    return pageCount;
  }

  public void setPageCount(Integer pageCount) {
    this.pageCount = pageCount;
  }

  public String getCategories() {
    return categories;
  }

  public void setCategories(String categories) {
    this.categories = categories;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public String getSmallThumbnail() {
    return smallThumbnail;
  }

  public void setSmallThumbnail(String smallThumbnail) {
    this.smallThumbnail = smallThumbnail;
  }

  /*
  public Set<Wishlist> getWishlists() {
    return wishlists;
  }

  public void setWishlists(Set<Wishlist> wishlists) {
    this.wishlists = wishlists;
  } */

  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", authors='" + authors + '\'' +
        ", description='" + description + '\'' +
        ", pageCount=" + pageCount +
        ", categories='" + categories + '\'' +
        ", thumbnail='" + thumbnail + '\'' +
        ", smallThumbnail='" + smallThumbnail + '\'' +
        '}';
  }
}
