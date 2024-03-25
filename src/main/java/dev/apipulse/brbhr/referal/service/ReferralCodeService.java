package dev.apipulse.brbhr.referal.service;

import dev.apipulse.brbhr.referal.model.ReferralCode;
import dev.apipulse.brbhr.referal.model.ReferralCode.DiscountType;
import dev.apipulse.brbhr.referal.repository.ReferralCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReferralCodeService {

    @Autowired
    private ReferralCodeRepository referralCodeRepository;
    private static final Logger log = LoggerFactory.getLogger(ReferralCodeService.class);


    public ReferralCode generateReferralCode(String userId, DiscountType discountType, double discountValue, int maxUses) {
        String uniqueCode = UUID.randomUUID().toString();
        ReferralCode referralCode = new ReferralCode(uniqueCode, userId, new HashSet<>(), true, maxUses, discountType, discountValue);
        referralCodeRepository.save(referralCode);
        return referralCode;
    }

    public boolean validateReferralCode(String referralCode) {
        Optional<ReferralCode> codeOpt = referralCodeRepository.findById(referralCode);
        return codeOpt.map(ReferralCode::isUsable).orElse(false);
    }

    public Optional<ReferralCode> applyReferralBenefits(String referralCode, String referredUserName) {
        try {
            return referralCodeRepository.findById(referralCode).map(code -> {
                if (code.isUsable() && !code.isUsedBy(referredUserName)) {
                    code.addUser(referredUserName);
                    referralCodeRepository.save(code);
                    return code;
                } else {
                    // Log a warning if the code is not usable or has been used by the same user before
                    log.warn("Referral code {} is not usable or already used by {}", referralCode, referredUserName);
                    return null;
                }
            });
        } catch (Exception e) {
            // Log the exception with an error level log
            log.error("Error applying referral benefits for referralCode: {}, referredUserName: {}", referralCode, referredUserName, e);
            return Optional.empty();
        }
    }
}
