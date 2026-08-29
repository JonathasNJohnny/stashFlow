package br.com.jonathas.stashflow.application.command;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jonathas.stashflow.domain.Payment;
import br.com.jonathas.stashflow.dto.CreatePaymentRequest;
import br.com.jonathas.stashflow.dto.PaymentResponse;
import br.com.jonathas.stashflow.repository.PaymentRepository;

@Service
public class PaymentCommandService {

    private final PaymentRepository paymentRepository;

    public PaymentCommandService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public PaymentResponse createPayment(CreatePaymentRequest request) {
        Payment payment = Payment.create(
                request.customerId(),
                request.amount()
        );

        Payment savedPayment = paymentRepository.save(payment);

        return PaymentResponse.from(savedPayment);
    }
}