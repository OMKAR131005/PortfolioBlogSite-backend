package com.omkar.porfolio_backend.controllers;

import com.omkar.porfolio_backend.Entity.Blog;
import com.omkar.porfolio_backend.dto.ApiResponse;
import com.omkar.porfolio_backend.dto.ChatRequest;
import com.omkar.porfolio_backend.services.BlogService;
import com.omkar.porfolio_backend.services.GeminiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/public/api")
@Tag(name = "Public Blog API", description = "Public endpoints for reading published blogs")
public class PublicController {

    private final BlogService blogService;

    @Operation(summary = "Get all published blogs")
    @GetMapping("/getPublishBlogs")
    public ResponseEntity<ApiResponse<List<Blog>>> getPublishBlogs() {
        List<Blog> blogs = blogService.getPublishBlogs();
        return ResponseEntity.ok(ApiResponse.success(blogs));
    }

    @Operation(summary = "Get a published blog by ID")
    @GetMapping("/getBlogById/{id}")
    public ResponseEntity<ApiResponse<Blog>> getBlogById(@PathVariable Long id) {
        Blog blog = blogService.getBlogById(id);
        return ResponseEntity.ok(ApiResponse.success(blog));
    }

    @Operation(summary = "Get published blogs by date")
    @GetMapping("/date/{date}")
    public ResponseEntity<ApiResponse<List<Blog>>> getBlogByDate(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {
        List<Blog> blogs = blogService.getBlogByDate(date);
        return ResponseEntity.ok(ApiResponse.success(blogs));
    }
}
