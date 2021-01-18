package com.distribution.worker.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(catalog = "worker_db", name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany
    @JoinTable(catalog = "worker_db", name = "role_authority",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> authorities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> users;


    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }

}
