package com.example.TravelProject.entity.Room;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accommodation_amenity")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AccommodationAmenity {

    @EmbeddedId
    private AccommodationAmenityId id;

    @ManyToOne
    @MapsId("accommodationId")
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @ManyToOne
    @MapsId("amenityId")
    @JoinColumn(name = "amenity_id")
    private Amenity amenity;
}

