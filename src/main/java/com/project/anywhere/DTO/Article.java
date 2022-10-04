package com.project.anywhere.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Timestamp;

@Getter
@Setter
public class Article {
    private double x;
    private double y;
    private String title;
    private byte[] image;
    private Timestamp createdDate;
    private String password;
    private String content;
}
