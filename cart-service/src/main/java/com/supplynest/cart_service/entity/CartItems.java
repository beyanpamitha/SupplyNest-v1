package com.supplynest.cart_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "cart_items",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"cart_id", "product_id"})  //This guarantees:One product per cart / No race condition duplicates
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id",nullable = false)
    @JsonIgnore            //Do NOT include this field when converting the object to JSON
    private Carts cart;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Double quantity;

    //Should not get unit price from the front end. because anyone can open devtools and change the unit price.
    @Column(nullable = false)
    private Double unitPrice;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
