package dev.apipulse.brbhr.referal.model;

import dev.apipulse.brbhr.referal.model.ReferralCode.DiscountType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenerateReferralCodeRequest {

    private String userId; // Consider if you need this, based on your authentication strategy
    private DiscountType discountType;
    private double discountValue;
    private int maxUses;
}
