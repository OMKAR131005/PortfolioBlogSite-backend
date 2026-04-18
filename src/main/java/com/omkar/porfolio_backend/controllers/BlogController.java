package com.omkar.porfolio_backend.controllers;

import com.omkar.porfolio_backend.Entity.*;
import com.omkar.porfolio_backend.Repository.UserRepository;
import com.omkar.porfolio_backend.dto.ApiResponse;
import com.omkar.porfolio_backend.services.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name = "Admin Blog API", description = "Admin endpoints — requires JWT")
@SecurityRequirement(name = "bearerAuth")
public class BlogController {

    private final BlogService blogService;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Operation(summary = "Create a new blog draft")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Blog>> createBlog(@RequestBody CreateBlog blog) {
        Blog created = blogService.createBlog(blog);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Blog draft created", created));
    }

    @Operation(summary = "Publish a blog by ID")
    @PatchMapping("/publish/{id}")
    public ResponseEntity<ApiResponse<Blog>> publishBlog(@PathVariable Long id) {
        Blog published = blogService.publishBlog(id);
        return ResponseEntity.ok(ApiResponse.success("Blog published", published));
    }

    @Operation(summary = "Get all blogs (draft + published)")
    @GetMapping("/getAllBlog")
    public ResponseEntity<ApiResponse<List<Blog>>> getAllBlogs() {
        return ResponseEntity.ok(ApiResponse.success(blogService.getAllBlogs()));
    }

    @Operation(summary = "Get all published blogs")
    @GetMapping("/getPublishBlogs")
    public ResponseEntity<ApiResponse<List<Blog>>> getPublishBlogs() {
        return ResponseEntity.ok(ApiResponse.success(blogService.getPublishBlogs()));
    }

    @Operation(summary = "Update a draft blog")
    @PutMapping("/updateBlog/{id}")
    public ResponseEntity<ApiResponse<Blog>> updateBlog(
            @PathVariable Long id,
            @RequestBody CreateBlog createBlog) {
        Blog updated = blogService.updateDraftBlog(
                id, createBlog.getContent(), createBlog.getTitle(), createBlog.getCategory());
        return ResponseEntity.ok(ApiResponse.success("Blog updated", updated));
    }

    @Operation(summary = "Delete a blog")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Blog>> deleteBlog(@PathVariable Long id) {
        Blog deleted = blogService.deleteBlog(id);
        return ResponseEntity.ok(ApiResponse.success("Blog deleted", deleted));
    }

    @Operation(summary = "Register admin user")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@Valid @RequestBody LoginRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(Roles.Admin);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Admin registered", null));
    }
}
