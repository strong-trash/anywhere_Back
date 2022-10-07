package com.project.anywhere.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Places")
@Getter
@Setter
public class Places {
    @Id
    @Column(name="ADDR")
    private String Place_id;
    @Column
    @OneToMany(mappedBy = "place",cascade = CascadeType.ALL)
    private List<Articles> articles;
    public void addArticles(Articles article){
        articles.add(article);
    }
}
