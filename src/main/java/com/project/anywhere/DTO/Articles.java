package com.project.anywhere.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name="Articles")
public class Articles {
    @Id
    @Column(name="ARTICLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long article_id;
    @Column(name="X",nullable = false)
    private double x;
    @Column(name="Y",nullable = false)
    private double y;
    @Column(name="TITLE",length = 255)
    private String title;
    @Column(name="image",nullable = true)
    private byte[] image;
    @Column(name="createdDate",nullable = true)
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name="Place_id")
    private Places place;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="CONTENT",nullable = true)
    private String content;
    public void setAll(Article article, Optional<Places> placesOptional){
        this.x = article.getX();
        this.y=article.getY();
        this.title=article.getTitle();
        this.image=article.getImage();
        this.createdDate=article.getCreatedDate();
        this.place=placesOptional.isEmpty()?null:placesOptional.get();
        this.password=article.getPassword();
        this.content=article.getContent();
    }
}
