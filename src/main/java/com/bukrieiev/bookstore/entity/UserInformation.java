package com.bukrieiev.bookstore.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="user_information")
@NoArgsConstructor
@RequiredArgsConstructor
public class UserInformation {

    @Id
    @Getter
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Getter
    @Setter
    @Column(name = "birthday")
    private String birthday;

    @NonNull
    @Getter
    @Setter
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

}
