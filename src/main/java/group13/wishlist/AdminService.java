package group13.wishlist;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final WishlistRepository wishlistRepository;

    public AdminService(UserRepository userRepository, WishlistRepository wishlistRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.wishlistRepository = wishlistRepository;
    }


    // Fetch all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Create a new user
    public User createUser(String username, String password, String role) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            User newUser = new User(username, password,role);
            newUser.setPassword(passwordEncoder.encode(password));
            return userRepository.save(newUser);
        }
        throw new RuntimeException("User already exists.");
    }

    // Delete a user by username, also deletes wishlists associated with the user
    @Transactional
    public boolean deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            wishlistRepository.deleteAll(wishlistRepository.findByUser(user));
            userRepository.delete(user);
            return true;
        }
        return false;
    }


    // Changes a user's role
    public boolean updateRole(String username, String role) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setRoles(role);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
