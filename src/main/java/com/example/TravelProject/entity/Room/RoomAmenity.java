//package com.example.TravelProject.entity.Room;
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "room_amenity")
//@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
//public class RoomAmenity {
//
//    @EmbeddedId
//    private RoomAmenityId id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("roomTypeId")
//    @JoinColumn(name = "room_type_id")
//    private RoomType roomType;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("amenityId")
//    @JoinColumn(name = "amenity_id")
//    private Amenity amenity;
//}
