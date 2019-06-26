package com.bukrieiev.bookstore.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NonNull
    @Getter
    @Setter
    @Column(name = "email", nullable = false)
    private String email;

    @NonNull
    @Getter
    @Setter
    @Column(name = "password", nullable = false)
    private String password;

    @NonNull
    @Getter
    @Setter
    @Column(name = "username", nullable = false)
    private String username;

    @NonNull
    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();



    @NonNull
    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name="userInformation")
    private UserInformation userInformation;
}
