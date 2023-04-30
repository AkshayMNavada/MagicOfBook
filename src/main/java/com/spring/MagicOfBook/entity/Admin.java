package com.spring.MagicOfBook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "admin_id")
    private String adminId;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(name = "admin_read_later_books", joinColumns = @JoinColumn(name = "admin_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    @JsonIgnore
    private List<Book> readLaterBooks;

    @ManyToMany
    @JoinTable(name = "admin_liked_books", joinColumns = @JoinColumn(name = "admin_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    @JsonIgnore
    private List<Book> likedBooks;

    public Admin() {

    }

    public Admin(String username, String email, String password) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getReadLaterBooks() {
        return readLaterBooks;
    }

    public void setReadLaterBooks(List<Book> readLaterBooks) {
        this.readLaterBooks = readLaterBooks;
    }

    public List<Book> getLikedBooks() {
        return likedBooks;
    }

    public void setLikedBooks(List<Book> likedBooks) {
        this.likedBooks = likedBooks;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", readLaterBooks=" + readLaterBooks +
                ", likedBooks=" + likedBooks +
                '}';
    }
}
