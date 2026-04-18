//package com.omkar.porfolio_backend.Repository;
//
//import com.omkar.porfolio_backend.Entity.Blog;
//import com.omkar.porfolio_backend.Entity.BlogViews;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDateTime;
//
//@Repository
//public interface ViewCountRepository extends JpaRepository<BlogViews,Long> {
//
//    @Query("""
//            SELECT COUNT(v)
//            FROM BlogView v
//            WHERE v.blog = :blog
//              AND v.ipAddress = :ip
//              AND v.viewedAt >= :oneHourAgo
//            """)
//    int countView(@Param("blog") Blog blog, @Param(":ip")String ip, @Param("oneHourAgo")LocalDateTime time);
//}
