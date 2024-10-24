package group13.wishlist;

import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        boolean loginSuccess = userService.loginUser(username, password);
        if (loginSuccess) {
            //return "Login successful!";
            return JwtUtil.generateToken(username);
        } else {
            return "Login failed. Check your credentials.";
        }
    }

    @GetMapping("/protected")
    public User protectedEndpoint(@RequestHeader("Authorization") String token) {
        try {
            String username = JwtUtil.validateToken(token);
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping("/logout")
    public String logoutUser(@RequestParam String username) {
        boolean logoutSuccess = userService.logoutUser(username);
        return logoutSuccess ? "Logout successful!" : "Logout failed. No user logged in or username mismatch.";
    }


    @PostMapping("/newuser")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Delete user
    @DeleteMapping("/users")
    public String deleteUser(@RequestParam String username, @RequestParam String confirm) {
        if ("yes".equalsIgnoreCase(confirm)) {
            boolean deleteSuccess = userService.deleteUser(username);
            return deleteSuccess ? "User deleted successfully." : "User deletion failed.";
        } else {
            return "User deletion not confirmed.";
        }
    }

    // Update user information

    @PatchMapping("/username")
    public String updateUsername(@RequestParam String username, @RequestParam String newUsername) {
        boolean updateSuccess = userService.updateUsername(username, newUsername);
        return updateSuccess ? "Username updated successfully." : "Username update failed.";
    }

    @PatchMapping("/password")
    public String updatePassword(@RequestParam String username,@RequestParam String currentPassword, @RequestParam String newPassword) {
        boolean updateSuccess = userService.updatePassword(username, currentPassword, newPassword);
        return updateSuccess ? "Password updated successfully." : "Password update failed.";
    }
}
