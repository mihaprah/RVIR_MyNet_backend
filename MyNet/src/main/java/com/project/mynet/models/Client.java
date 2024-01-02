package com.project.mynet.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;


@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    private double cashBalance = 0.0;

    private String email;

    private String password;

    private String salt;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Collection<Vault> vaults;
}
