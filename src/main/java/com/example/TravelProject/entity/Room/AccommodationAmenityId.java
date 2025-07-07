package com.example.TravelProject.entity.Room;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationAmenityId implements Serializable {
    private Integer accommodationId;
    private Integer amenityId;
}
