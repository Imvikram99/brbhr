package dev.apipulse.brbhr.referal.controller;

import dev.apipulse.brbhr.referal.model.ApplyReferralCodeRequest;
import dev.apipulse.brbhr.referal.model.GenerateReferralCodeRequest;
import dev.apipulse.brbhr.referal.model.ReferralCode;
import dev.apipulse.brbhr.referal.service.ReferralCodeService;
import dev.apipulse.brbhr.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/referral-codes")
public class ReferralCodeController {

    private final ReferralCodeService referralCodeService;

    @Autowired
    public ReferralCodeController(ReferralCodeService referralCodeService) {
        this.referralCodeService = referralCodeService;
    }

    @PostMapping("/generate")
    public ResponseEntity<ReferralCode> generateReferralCode(
            @AuthenticationPrincipal User userDetails, // Inject authenticated user details
            @RequestBody GenerateReferralCodeRequest request) {
        
        // Use the userDetails to obtain the userId or any required information
        ReferralCode referralCode = referralCodeService.generateReferralCode(
                userDetails.getUsername(), // Assuming the username is the userId or modify accordingly
                request.getDiscountType(), 
                request.getDiscountValue(), 
                request.getMaxUses());
        return ResponseEntity.ok(referralCode);
    }

    @GetMapping("/validate/{code}")
    public ResponseEntity<Boolean> validateReferralCode(
            @PathVariable("code") String code) {
        
        boolean isValid = referralCodeService.validateReferralCode(code);
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyReferralCode(
            @AuthenticationPrincipal User userDetails, // Inject authenticated user details
            @RequestBody ApplyReferralCodeRequest request) {

        // Here, you might want to verify that the userDetails match the intended operation
        Optional<ReferralCode> appliedCode = referralCodeService.applyReferralBenefits(
                request.getReferralCode(), userDetails.getUsername()); // Adjust based on your user identification strategy

        return appliedCode
                .map(code -> ResponseEntity.ok().body("code applied successfully")) // Successfully applied the referral code
                .orElse(ResponseEntity.badRequest().body("Referral code is not valid or already used")); // Handle error case
    }

}
