package com.blog.api.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
@NoArgsConstructor
@Setter
@Getter
public class Role {
    @Id
    private Integer id;
    private String name;
}
