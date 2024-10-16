package group13.wishlist;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Admin login (hardcoded credentials for now)
    public boolean loginAdmin(String username, String password) {
        // need to use a secure method for verifying admin credentials
        return "admin".equals(username) && "adminpassword".equals(password);
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

    // Delete a user by username
    public boolean deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    // Update a user by changing their password
    public boolean updateUser(String username, String newPassword) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
