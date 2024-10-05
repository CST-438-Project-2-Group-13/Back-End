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

CREATE TABLE `users` (
                         `userId` int(11) NOT NULL AUTO_INCREMENT,
                         `username` varchar(255) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         `isAdmin` tinyint(1) NOT NULL DEFAULT 0,
                         `is_admin` bit(1) NOT NULL,
                         PRIMARY KEY (`userId`),
                         UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `wishlist` (
                            `wishlist_id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `title` varchar(255) NOT NULL,
                            `user_id` int(11) DEFAULT NULL,
                            `book_id` bigint(20) DEFAULT NULL,
                            PRIMARY KEY (`wishlist_id`),
                            KEY `userId` (`user_id`),
                            CONSTRAINT `wishlist_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `users` (
                         `user_id` int(11) NOT NULL,
                         `username` varchar(255) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         `isAdmin` tinyint(1) NOT NULL DEFAULT 0,
                         `is_admin` bit(1) NOT NULL,
                         PRIMARY KEY (`user_id`),
                         UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci