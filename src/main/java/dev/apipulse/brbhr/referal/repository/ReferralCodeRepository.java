package dev.apipulse.brbhr.referal.repository;

import dev.apipulse.brbhr.referal.model.ReferralCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReferralCodeRepository extends MongoRepository<ReferralCode, String> {

    // Find all referral codes associated with a specific user
    List<ReferralCode> findByUserId(String userId);
    Optional<ReferralCode> findByCode(String code);

    // Find all active referral codes for a user
    List<ReferralCode> findByUserIdAndActiveTrue(String userId);

    // Find referral codes that have not reached their max uses
    @Query("{'userId': ?0, 'usedByUserIds': {$size: {$lt: ?1}}}")
    List<ReferralCode> findByUserIdAndMaxUsesNotReached(String userId, int maxUses);

    // Find referral codes by discount type
    List<ReferralCode> findByDiscountType(ReferralCode.DiscountType discountType);

    // Check if a specific user has used a referral code
    boolean existsByCodeAndUsedByUserIdsContains(String code, String userId);

    // Find referral codes that have been used by a specific number of users
    // Useful for analyzing the popularity or effectiveness of a code
    @Query("{'usedByUserIds': {$size: ?0}}")
    List<ReferralCode> findByNumberOfUses(int numberOfUsers);

    // Find active referral codes with a specific discount value
    List<ReferralCode> findByActiveTrueAndDiscountValue(double discountValue);
}
