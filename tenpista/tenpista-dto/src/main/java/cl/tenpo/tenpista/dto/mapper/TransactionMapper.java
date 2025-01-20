package cl.tenpo.tenpista.dto.mapper;

import cl.tenpo.tenpista.domain.entities.Transaction;
import cl.tenpo.tenpista.dto.TransactionDto;

public class TransactionMapper {
    public static TransactionDto toDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getMerchant(),
                transaction.getCustomer(),
                transaction.getTransactionDateFormatted(),
                transaction.getUpdatedAtFormatted()
        );
    }
}