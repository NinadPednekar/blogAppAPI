package com.blog.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "POSTS")
@NoArgsConstructor
@Setter
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    @Column(name = "IMAGE_NAME")
    private String imageName;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;

}
