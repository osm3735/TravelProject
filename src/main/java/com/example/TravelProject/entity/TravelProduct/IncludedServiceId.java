package com.example.TravelProject.entity.TravelProduct;

import lombok.*;
import java.io.Serializable;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class IncludedServiceId implements Serializable {
    private Integer product;
    private Integer service;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IncludedServiceId)) return false;
        IncludedServiceId that = (IncludedServiceId) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(service, that.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, service);
    }
}
