package com.example.TravelProject.entity.TravelProduct;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "included_service")
@IdClass(IncludedServiceId.class)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class IncludedService {

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private TravelProduct product;

    @Id
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service travelService;
}