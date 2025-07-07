package com.example.TravelProject.entity.Room;

import com.example.TravelProject.entity.UserAccount.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="Accommodation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accommodation_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id", nullable = false)
    private User owner_user_id;

    @Column(nullable = false)
    private String name; // 숙박업체 이름

    @Column(columnDefinition = "TEXT")
    private String description; // 숙박업체 설명

    @Column(nullable = false)
    private String address; // 주소

    @Column(precision = 10, scale = 8)
    private Double latitude; // 위도

    @Column(precision = 11, scale = 8)
    private Double longitude; // 경도

    @Column
    private String type; // 숙소 유형 (호텔, 모텔, 펜션 등)

    private LocalDateTime checkInTime; // 체크인 시간

    private LocalDateTime checkOutTime; // 체크아웃 시간

    @Column(precision = 3, scale = 2)
    private Double ratingAvg = 0.0;

    @Column
    private Integer totalReviews = 0; // 리뷰 수

    @Column(nullable = false)
    private LocalDateTime registrationDate = LocalDateTime.now(); // 등록일시

    @Column(nullable = false)
    private Boolean isActive = true; // 활성 여부
}
