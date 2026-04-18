package com.omkar.porfolio_backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBlog {
       private String title;
       private String content;
       private Category category;

}
