USE jcfz9y5ufeszpd3r;
CREATE TABLE IF NOT EXISTS users (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    isAdmin BOOLEAN NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(255),
    Authors TEXT,
    Description TEXT,
    PageCount INT,
    Categories TEXT,
    Thumbnail VARCHAR(255),
    SmallThumbnail VARCHAR(255)
);

-- wishlistId, title, userId, bookId
CREATE TABLE wishlist (
    wishlistId INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    userId INT,
    bookId INT,
    FOREIGN KEY (userId) REFERENCES users(userId)
);
