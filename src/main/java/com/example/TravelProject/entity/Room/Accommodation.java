package com.example.TravelProject.entity.Room;

import com.example.TravelProject.entity.UserAccount.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "Accommodation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accommodation_id")
    private Integer accommodationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id", nullable = false)
    private User owner;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "latitude", precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(name = "type")
    private String type; // 호텔, 모텔, 펜션, 리조트, 게스트하우스, 한옥

    @Column(name = "check_in_time")
    private LocalTime checkInTime;

    @Column(name = "check_out_time")
    private LocalTime checkOutTime;

    @Builder.Default
    @Column(name = "rating_avg", precision = 3, scale = 2)
    private BigDecimal ratingAvg = BigDecimal.valueOf(0.0);

    @Builder.Default
    @Column(name = "total_reviews")
    private Integer totalReviews = 0;

    @Builder.Default
    @CreationTimestamp
    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}