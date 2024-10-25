package group13.wishlist;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Admin login
    @PostMapping("/login")
    public String adminLogin(@RequestParam String username, @RequestParam String password) {
        boolean loginSuccess = adminService.loginAdmin(username, password);
        return loginSuccess ? "Admin login successful!" : "Admin login failed. Check your credentials.";
    }

    // View all users (admin only)
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    // Create new user
    @PutMapping("/users")
    public User createUser(@RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String role) {
        return adminService.createUser(username, password, role);
    }

    // Delete user
    @DeleteMapping("/users")
    public ResponseEntity<String> deleteUser(@RequestParam String username) {
        try {
            boolean deleteSuccess = adminService.deleteUser(username);
            return deleteSuccess
                    ? ResponseEntity.ok("User deleted successfully.")
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User deletion failed: User not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the user.");
        }
    }

    // Update user information
    @PatchMapping("/users")
    public String updateUser(@RequestParam String username, @RequestParam String newPassword) {
        boolean updateSuccess = adminService.updateUser(username, newPassword);
        return updateSuccess ? "User updated successfully." : "User update failed.";
    }
}
