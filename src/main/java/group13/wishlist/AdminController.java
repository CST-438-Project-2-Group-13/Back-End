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
    private final UserService userService;

    public AdminController(AdminService adminService,UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    // View all users (admin only)
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    // Create new user
    @PutMapping("/users")
    public User createUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
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
    @PatchMapping("/username")
    public ResponseEntity<String> updateUsername(@RequestParam String username, @RequestParam String newUsername) {
        try {
            boolean updateSuccess = userService.updateUsername(username, newUsername);
            return updateSuccess ? ResponseEntity.ok("Username updated successfully.")
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username update failed.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating username.");
        }

    }

    // Update user role
    @PatchMapping("/password")
    public ResponseEntity<String> updateRole(@RequestParam String username, @RequestParam String role) {
        try {
            boolean updateSuccess = adminService.updateRole(username, role);
            return updateSuccess ? ResponseEntity.ok("Role updated successfully.")
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role update failed.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating role.");
        }
    }
}
