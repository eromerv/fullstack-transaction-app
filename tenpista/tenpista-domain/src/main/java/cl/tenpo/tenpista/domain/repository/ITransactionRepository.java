package cl.tenpo.tenpista.domain.repository;

import cl.tenpo.tenpista.domain.entities.Transaction;

import java.util.List;
import java.util.Optional;

public interface ITransactionRepository {

    List<Transaction> findAll();

    List<Transaction> findAllByCustomer(String customerName);

    Optional<Transaction> findById(Integer id);

    Transaction insert(Transaction transaction);

    Transaction update(Integer id, Transaction transaction);

    boolean delete(Integer id);
}

