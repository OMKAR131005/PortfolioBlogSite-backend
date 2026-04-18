package com.omkar.porfolio_backend.services;

import com.omkar.porfolio_backend.Entity.*;
import com.omkar.porfolio_backend.Exceptions.BlogCanNotBeCreated;
import com.omkar.porfolio_backend.Exceptions.ResourceNotFoundException;
import com.omkar.porfolio_backend.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Blog createBlog(CreateBlog createBlog) {
        if (createBlog.getTitle() == null || createBlog.getTitle().isBlank() ||
                createBlog.getContent() == null || createBlog.getContent().isBlank()) {
            throw new BlogCanNotBeCreated("Title and content cannot be blank");
        }

        Blog blog = new Blog();
        blog.setTitle(createBlog.getTitle());
        blog.setContent(createBlog.getContent().replace("\\n", "\n"));
        blog.setCategory(createBlog.getCategory());
        blog.setStatus(BlogStatus.DRAFT);
        blog.setCreatedAt(LocalDateTime.now());

        return blogRepository.save(blog);
    }

    public Blog publishBlog(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog with ID: " + id + " not found"));
        blog.setPublishedAt(LocalDateTime.now());
        blog.setStatus(BlogStatus.PUBLISHED);
        return blogRepository.save(blog);
    }

    public List<Blog> getBlogByDate(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        return blogRepository.getBlogByDate(BlogStatus.PUBLISHED, start, end);
    }

    public List<Blog> getPublishBlogs() {
        return blogRepository.getPublishBlogs(BlogStatus.PUBLISHED);
    }

    public Blog getBlogById(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog with ID: " + id + " not found"));
        if (blog.getPublishedAt() == null) {
            throw new ResourceNotFoundException("Blog with ID: " + id + " is not yet published");
        }
        return blog;
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.getAllBlogs();
    }

    public Blog updateDraftBlog(Long id, String content, String title, Category category) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog with ID: " + id + " not found"));
        if (!blog.getStatus().equals(BlogStatus.DRAFT)) {
            throw new BlogCanNotBeCreated("Only DRAFT blogs can be updated");
        }
        blog.setTitle(title);
        blog.setContent(content);
        blog.setCategory(category);
        return blogRepository.save(blog);
    }

    public Blog deleteBlog(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog with ID: " + id + " not found"));
        blogRepository.deleteById(id);
        return blog;
    }
}
