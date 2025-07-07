//package com.example.TravelProject.entity.TravelProduct;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "included_service")
//@IdClass(IncludedServiceId.class)
//@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
//public class IncludedService {
//
//    @Id
//    @Column(name = "product_id")
//    private Long productId;
//
//    @Id
//    @Column(name = "service_id")
//    private Long serviceId;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id", insertable = false, updatable = false)
//    private TravelProduct product;
//
//    @ManyToOne
//    @JoinColumn(name = "service_id", insertable = false, updatable = false)
//    private Service travelService;
//}
