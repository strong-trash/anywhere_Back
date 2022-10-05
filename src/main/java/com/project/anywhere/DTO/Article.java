package com.project.anywhere.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
public class Article {
    private String addr;
    private double x;
    private double y;
    private String title;
    private byte[] image;
    private Date createdDate;
    private String password;
    private String content;
}
