package br.com.jonathas.stashFlow.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jonathas.stashFlow.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}