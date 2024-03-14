package dev.apipulse.brbhr.referal.service;

import dev.apipulse.brbhr.referal.model.ReferralCode;
import dev.apipulse.brbhr.referal.model.ReferralCode.DiscountType;
import dev.apipulse.brbhr.referal.repository.ReferralCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReferralCodeService {

    @Autowired
    private ReferralCodeRepository referralCodeRepository;

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

    public Optional<ReferralCode> applyReferralBenefits(String referralCode, String referredUserId) {
        return referralCodeRepository.findById(referralCode).map(code -> {
            if (code.isUsable() && !code.isUsedBy(referredUserId)) {
                code.addUser(referredUserId);
                referralCodeRepository.save(code);
                // Here, apply the actual benefits based on the code's discountType and discountValue
                // This might involve calling other services or repositories
                return code;
            }
            return null;
        });
    }
}
