package com.bukrieiev.bookstore.entity;

import lombok.*;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking")
@EntityListeners(AuditingEntityListener.class)
public class Booking {

    public Booking(Set<Book> books, String totalPrice) {
        this.books = books;
        this.totalPrice = totalPrice;
    }

    @Getter
    @Setter
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NonNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "booking_books",
            joinColumns = @JoinColumn(name = "booking_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    @Getter
    @Setter
    private Set<Book> books;

    @NonNull
    @Getter
    @Setter
    @Column(name = "total_price", nullable = false)
    private String totalPrice;

}
