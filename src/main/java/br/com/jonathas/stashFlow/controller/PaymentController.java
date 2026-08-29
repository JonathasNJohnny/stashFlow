package br.com.jonathas.stashFlow.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jonathas.stashFlow.application.command.PaymentCommandService;
import br.com.jonathas.stashFlow.dto.CreatePaymentRequest;
import br.com.jonathas.stashFlow.dto.PaymentResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentCommandService paymentCommandService;

    public PaymentController(
            PaymentCommandService paymentCommandService
    ) {
        this.paymentCommandService = paymentCommandService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(
            @Valid @RequestBody CreatePaymentRequest request
    ) {
        PaymentResponse payment =
                paymentCommandService.createPayment(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(payment.id())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(payment);
    }
}