package dev.apipulse.brbhr.referal.controller;

import dev.apipulse.brbhr.referal.model.ApplyReferralCodeRequest;
import dev.apipulse.brbhr.referal.model.GenerateReferralCodeRequest;
import dev.apipulse.brbhr.referal.model.ReferralCode;
import dev.apipulse.brbhr.referal.service.ReferralCodeService;
import dev.apipulse.brbhr.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> generateReferralCode(
            @AuthenticationPrincipal User userDetails, // Inject authenticated user details
            @RequestBody GenerateReferralCodeRequest request) {
        
        // Use the userDetails to obtain the userId or any required information
        ReferralCode referralCode = referralCodeService.generateReferralCode(
                userDetails.getUsername(), // Assuming the username is the userId or modify accordingly
                ReferralCode.DiscountType.FLAT_DYNAMIC_OFF,
                request.getDiscountValue(), 
                request.getMaxUses());
        return ResponseEntity.ok(referralCode.getCode());
    }

    @PostMapping("/generate/flat")
    public ResponseEntity<String> generateFlatReferralCode(
            @AuthenticationPrincipal User userDetails) {

        // Use the userDetails to obtain the userId or any required information
        ReferralCode referralCode = referralCodeService.generateReferralCode(
                userDetails.getUsername(), // Assuming the username is the userId or modify accordingly
                ReferralCode.DiscountType.FLAT_FIX_OFF,
                500,
                1000);
        return ResponseEntity.ok(referralCode.getCode());
    }

    @GetMapping("/validate/{code}")
    public ResponseEntity<Boolean> validateReferralCode(
            @PathVariable("code") String code) {
        
        boolean isValid = referralCodeService.validateReferralCode(code);
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/apply")
    public ResponseEntity<ReferralCode> applyReferralCode(
            @AuthenticationPrincipal User userDetails,
            @RequestBody ApplyReferralCodeRequest request) {

        Optional<ReferralCode> appliedCode = referralCodeService.applyReferralBenefits(
                request.getReferralCode(), userDetails.getUsername());

        if (appliedCode.isPresent()) {
            return ResponseEntity.ok(appliedCode.get()); // Successfully applied the referral code, return it
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Referral code is not valid or already used
        }
    }


}
