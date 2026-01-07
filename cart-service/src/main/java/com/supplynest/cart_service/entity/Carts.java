package com.supplynest.cart_service.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Carts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @OneToMany(
            mappedBy = "cart",           //Cart is NOT the owner of the relationship
            cascade = CascadeType.ALL,   //Any operation on Cart is automatically applied to its CartItems
            orphanRemoval = true,        //If a CartItem is removed from Cart → delete it from DB
            fetch = FetchType.LAZY       //CartItems are loaded only when needed
    )
    private List<CartItems> items = new ArrayList<>();

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
