package cl.tenpo.tenpista.domain.shared;

import cl.tenpo.tenpista.domain.entities.Transaction;

import java.time.LocalDateTime;

public class TransactionFactory {
    public static Transaction create(Integer amount, String businessName, String tenpistaName) {
        return new Transaction(
                0,
                amount,
                businessName,
                tenpistaName,
                LocalDateTime.now(), // Created at
                LocalDateTime.now()  // Updated at
        );
    }
}

