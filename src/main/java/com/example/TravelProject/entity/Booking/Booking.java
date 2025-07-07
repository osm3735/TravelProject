package com.example.TravelProject.entity.Booking;

import com.example.TravelProject.entity.Room.Accommodation;
import com.example.TravelProject.entity.Room.RoomType;
import com.example.TravelProject.entity.TravelProduct.ProductSchedule;
import com.example.TravelProject.entity.TravelProduct.TravelProduct;
import com.example.TravelProject.entity.UserAccount.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private TravelProduct travelProduct;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private ProductSchedule schedule;

    private String bookingType;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @Column(nullable = false)
    private Integer numAdults = 1;

    private Integer numChildren = 0;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    private String currency = "KRW";

    private LocalDateTime bookingDate = LocalDateTime.now();

    private String status;

    @Column(columnDefinition = "TEXT")
    private String requestNotes;

    private LocalDateTime cancellationDate;

    private BigDecimal cancellationFee = BigDecimal.ZERO;
}
