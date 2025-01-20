package cl.tenpo.tenpista.mapper;

import cl.tenpo.tenpista.domain.entities.Transaction;
import cl.tenpo.tenpista.entity.TransactionEntity;

import java.time.LocalDateTime;

public class TransactionMapper {
    private TransactionMapper() {
        // Private constructor to prevent instantiation
    }

    // Convert from Domain to Entity
    public static TransactionEntity toEntity(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        TransactionEntity entity = new TransactionEntity();

        if (transaction.hasId()) {
            entity.setId(transaction.getId());
        }

        entity.setAmount(transaction.getAmount());
        entity.setMerchant(transaction.getMerchant());
        entity.setCustomer(transaction.getCustomer());
        entity.setTransactionDate(transaction.getTransactionDate() != null ? transaction.getTransactionDate() : LocalDateTime.now());
        entity.setUpdatedAt(transaction.getUpdatedAt() != null ? transaction.getUpdatedAt() : LocalDateTime.now());

        return entity;
    }

    // Convert from Entity to Domain
    public static Transaction toDomain(TransactionEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Transaction(
                entity.getId(),
                entity.getAmount(),
                entity.getMerchant(),
                entity.getCustomer(),
                entity.getTransactionDate(),
                entity.getUpdatedAt()
        );
    }
}
