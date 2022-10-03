package com.project.anywhere.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="Articles")
public class Article {
    @Id
    @Column(name="ARTICLE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long article_id;
    @Column(name="X",nullable = false)
    private double x;
    @Column(name="Y",nullable = false)
    private double y;
    @Column(name="TITLE",length = 255)
    private String title;
    @Column(name="image",nullable = true)
    private byte[] image;
    @Column(name="createdDate")
    private Timestamp createdDate;
    @Column(name="ADDR",nullable = false)
    private String addr;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="CONTENT",nullable = false)
    private String content;
}
