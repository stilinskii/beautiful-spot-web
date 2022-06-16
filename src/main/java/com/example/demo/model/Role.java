package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Role {

    @Id
    private int id;
    private String name;
    private int enabled;

    @ManyToMany(mappedBy = "roles")
    List<Member> members = new ArrayList<>();
}
