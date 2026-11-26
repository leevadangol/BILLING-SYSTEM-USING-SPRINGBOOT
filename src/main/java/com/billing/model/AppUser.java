package com.billing.model;

import jakarta.persistence.*;

/** One row of the users table (password stored as SHA-256 hex, like the reference project). */
@Entity
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    public AppUser() { }

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
