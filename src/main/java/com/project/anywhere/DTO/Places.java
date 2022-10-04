package com.project.anywhere.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Vector;

@Entity
@Table(name="Places")
@Getter
@Setter
public class Places {
    @Id
    @Column(name="ADDR")
    private String Place_id;
    @Column
    @OneToMany(mappedBy = "place")
    private List<Articles> articles;
}
