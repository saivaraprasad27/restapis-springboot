package com.learn.DecProjectModule.controller;

import com.learn.DecProjectModule.dto.PaymentRequestDto;
import com.learn.DecProjectModule.service.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    /*
     * Reconciliation in Spring
     *
     * Reconciliation is the process of validating and synchronizing data
     * between two or more systems to ensure data consistency and accuracy.
     *
     * In Spring-based applications, reconciliation is commonly used in:
     *  - Payment systems
     *  - Banking and accounting platforms
     *  - Reporting services
     *  - Batch and scheduled jobs
     *
     * ------------------------------------------------------------
     * Typical Reconciliation Flow
     * ------------------------------------------------------------
     *
     *   External System (Payment Gateway / Bank)
     *                ↓
     *        Fetch transaction records
     *                ↓
     *      Compare with local database entries
     *                ↓
     *        Identify matches and mismatches
     *                ↓
     *   Update status / Retry processing / Raise alerts
     *
     * ------------------------------------------------------------
     * Common Example: Payment Reconciliation
     * ------------------------------------------------------------
     *
     * Scenario:
     *   - Payment Gateway reports a transaction as SUCCESS
     *   - Application database still shows the transaction as PENDING
     *
     * Reconciliation Process:
     *   1. Fetch the latest transaction status from the payment gateway
     *   2. Compare gateway records with application database records
     *   3. Detect inconsistencies or missing updates
     *   4. Update the local database with the correct status
     *   5. Trigger alerts or retries if required
     *
     * This ensures reliable financial records and prevents revenue loss
     * or incorrect transaction states.
     */


    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService  = paymentService;
    }

    @PostMapping("/payments")
    public ResponseEntity<String> createPaymentLink(@RequestBody PaymentRequestDto paymentRequestDto) throws StripeException {
        String paymentLink = paymentService.makePayment(paymentRequestDto.getOrderId(),paymentRequestDto.getAmount());
        return new ResponseEntity<>(paymentLink, HttpStatus.OK);
    }
}
