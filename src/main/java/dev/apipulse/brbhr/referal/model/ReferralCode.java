package dev.apipulse.brbhr.referal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReferralCode {
    String code;
    @JsonIgnore
    String userId; // User who owns this referral code
    @JsonIgnore
    Set<String> usedByUserIds = new HashSet<>(); // Tracks user IDs that have used this referral code
    boolean active = true; // Whether the referral code is still active
    @JsonIgnore
    int maxUses; // Maximum number of users allowed to use this referral code
    DiscountType discountType; // Type of discount (PERCENTAGE_OFF or FLAT_OFF)
    double discountValue; // The value of the discount, interpretation depends on DiscountType

    public void addUser(String userId) {
        if (isUsable()) {
            usedByUserIds.add(userId);
        }
    }

    public boolean isUsedBy(String userId) {
        return usedByUserIds.contains(userId);
    }

    public int getNumberOfUses() {
        return usedByUserIds.size();
    }

    // Check if the referral code can still be used based on active status and max uses
    public boolean isUsable() {
        return active && (usedByUserIds.size() < maxUses);
    }

    // Enum for discount types
    public enum DiscountType {
        PERCENTAGE_FIX_OFF,
        PERCENTAGE_DYNAMIC_OFF,
        FLAT_FIX_OFF,
        FLAT_DYNAMIC_OFF,
    }
}
