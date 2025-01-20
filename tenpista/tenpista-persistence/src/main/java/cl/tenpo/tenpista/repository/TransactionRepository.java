package cl.tenpo.tenpista.repository;

import cl.tenpo.tenpista.domain.entities.Transaction;
import cl.tenpo.tenpista.domain.error.TransactionNotFoundException;
import cl.tenpo.tenpista.domain.repository.ITransactionRepository;
import cl.tenpo.tenpista.entity.TransactionEntity;
import cl.tenpo.tenpista.mapper.TransactionMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional
public class TransactionRepository implements ITransactionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Transaction> findAll() {
        List<TransactionEntity> entities = entityManager
                .createQuery("SELECT t FROM TransactionEntity t", TransactionEntity.class)
                .getResultList();

        return entities.stream()
                .map(TransactionMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> findAllByCustomer(String customer) {
        List<TransactionEntity> entities = entityManager
                .createQuery("SELECT t FROM TransactionEntity t WHERE t.customer = :customer", TransactionEntity.class)
                .setParameter("customer", customer)
                .getResultList();

        return entities.stream()
                .map(TransactionMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Transaction> findById(Integer id) {
        TransactionEntity entity = entityManager.find(TransactionEntity.class, id);
        return Optional.ofNullable(TransactionMapper.toDomain(entity));
    }

    @Override
    public Transaction insert(Transaction transaction) {
        TransactionEntity entity = TransactionMapper.toEntity(transaction);
        entityManager.persist(entity);
        entityManager.flush();
        return TransactionMapper.toDomain(entity);
    }

    @Override
    public Transaction update(Integer id, Transaction transaction) {
        TransactionEntity existingEntity = Optional.ofNullable(entityManager.find(TransactionEntity.class, id))
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with ID: " + id));

        existingEntity.setAmount(transaction.getAmount());
        existingEntity.setMerchant(transaction.getMerchant());
        existingEntity.setCustomer(transaction.getCustomer());
        existingEntity.setTransactionDate(transaction.getTransactionDate());
        existingEntity.setUpdatedAt(LocalDateTime.now());

        entityManager.merge(existingEntity);
        entityManager.flush();

        return TransactionMapper.toDomain(existingEntity);
    }


    @Override
    public boolean delete(Integer id) {
        TransactionEntity entity = entityManager.find(TransactionEntity.class, id);
        if (entity == null) {
            throw new TransactionNotFoundException("Transaction not found with ID: " + id);
        }

        entityManager.remove(entity);
        entityManager.flush();
        return true;
    }
}
