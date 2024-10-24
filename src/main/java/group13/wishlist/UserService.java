package group13.wishlist;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository ) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public String loginUser(String username, String password) {
        // Fetch user from the database
        User user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // If login is successful, generate and return the token
            return JwtUtil.generateToken(username, user.getRoles());  // Assuming the role is 'ROLE_USER'
        } else {
            // If login fails, return null or an error message
            return null;  // Indicates failed login
        }
    }

    public boolean logoutUser(String username) {
        return username != null; //logout
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Delete a user by username
    public boolean deleteUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public boolean updateUsername(String username, String newUsername) {
        User user = userRepository.findByUsername(username);
        User newUser = userRepository.findByUsername(newUsername);
        if (user != null && newUser == null) {
            user.setUsername(newUsername);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean updatePassword(String username, String currentPassword, String newPassword) {
        User user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(currentPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }

}
