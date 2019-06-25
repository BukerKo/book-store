package com.bukrieiev.bookstore.entity;

import lombok.*;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking")
@EntityListeners(AuditingEntityListener.class)
public class Booking {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
//
//    @NonNull
//    @ManyToMany(fetch = FetchType.EAGER,
//    cascade = CascadeType.DETACH)
//    @JoinTable(name = "booking_books",
//            joinColumns = @JoinColumn(name = "booking_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id"))
//    @Getter
//    @Setter
//    private Set<Book> books;


    @NonNull
    @Getter
    @Setter
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookingBook> bookingBooks;

    @NonNull
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NonNull
    @Getter
    @Setter
    @Column(name = "total_price", nullable = false)
    private String totalPrice;

    public Booking(@NonNull User user, @NonNull String totalPrice) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.bookingBooks = new HashSet<>();
    }
}
