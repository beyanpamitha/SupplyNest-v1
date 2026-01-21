package com.supplynest.notification_service.entity;

import com.supplynest.notification_service.entity.enums.Channel;
import com.supplynest.notification_service.entity.enums.RecipientType;
import com.supplynest.notification_service.entity.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated
    private RecipientType recipientType;
    @Enumerated
    private Channel channel;

    private String title;
    private String message;

    @Enumerated
    private Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}