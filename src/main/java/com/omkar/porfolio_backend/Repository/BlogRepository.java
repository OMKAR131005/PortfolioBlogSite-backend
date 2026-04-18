package com.omkar.porfolio_backend.Repository;

import com.omkar.porfolio_backend.Entity.Blog;
import com.omkar.porfolio_backend.Entity.BlogStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query("""
            SELECT b FROM Blog b 
            WHERE b.status = :status 
            AND b.publishedAt >= :start 
            AND b.publishedAt < :end
            """)
    List<Blog> getBlogByDate(
            @Param("status") BlogStatus status,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("SELECT b FROM Blog b WHERE b.status = :status ORDER BY b.publishedAt DESC")
    List<Blog> getPublishBlogs(@Param("status") BlogStatus status);

    @Query("SELECT b FROM Blog b ORDER BY b.createdAt DESC")

    List<Blog> getAllBlogs();

}
