package com.example.TravelProject.entity.Room;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "price_policy")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PricePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pricePolicyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;

    @Column(nullable = false)
    private LocalDateTime  startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Column(precision = 10, scale = 2)
    private BigDecimal additionalPersonSurcharge = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    private BigDecimal weekendSurcharge = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    private BigDecimal holidaySurcharge = BigDecimal.ZERO;
}
