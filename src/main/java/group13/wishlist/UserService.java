package group13.wishlist;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final HttpSession session;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, HttpSession session) {
        this.userRepository = userRepository;
        this.session = session;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Store the user in the session to mark them as logged in
            session.setAttribute("loggedInUser", user);
            return true;
        }
        return false;
    }

    public boolean logoutUser(String username) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null && loggedInUser.getUsername().equals(username)) {
            session.invalidate();
            return true; //logout
        }
        return false;  // No user was logged in, or username didn't match
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
