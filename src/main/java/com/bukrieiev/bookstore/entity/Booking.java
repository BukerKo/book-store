package com.bukrieiev.bookstore.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
    private Long totalPrice;

    public Booking(@NonNull User user, @NonNull Long totalPrice) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.bookingBooks = new HashSet<>();
    }
}
