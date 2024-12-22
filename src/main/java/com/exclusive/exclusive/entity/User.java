package com.exclusive.exclusive.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 50)
    private String name;

    @Column(nullable = false)
    private String surname;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'CLIENT'")
    private String role = "CLIENT";

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    @Size(min = 8)
    private String password;

    // utilities methods

     /**
     * To add a like
     * @param like
     */
    public void addLike(Like like) {
        likes.add(like);
        like.setUser(this);
    }

    /**
     * To remove a like
     * @param like
     */
    public void removeLike(Like like) {
        likes.remove(like);
        like.setUser(null);
    }

}
