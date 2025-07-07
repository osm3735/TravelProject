package com.example.TravelProject.entity.Room;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "room_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomTypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id", nullable = false)
    private Accommodation accommodation;

    @Column(nullable = false)
    private String name;

    @Lob
    private String description;

    @Column(nullable = false)
    private Integer maxOccupancy;

    @Column(nullable = false)
    private Integer standardOccupancy;

    private String bedType;

    @Column(precision = 6, scale = 2)
    private Double areaSqm;
}

