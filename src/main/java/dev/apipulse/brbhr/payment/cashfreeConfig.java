package dev.apipulse.brbhr.payment;

import com.cashfree.Cashfree;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class cashfreeConfig {
    @Bean
    public Cashfree init() {
        Cashfree.XClientId = "2288612553fd48fa104f6db072168822";
        Cashfree.XClientSecret = "cfsk_ma_test_df42f05c2d7a6f19b86720c212c5d66c_188e256e";
        Cashfree.XEnvironment = Cashfree.SANDBOX;
        Cashfree cashfree = new Cashfree();
        return cashfree;
    }
}
