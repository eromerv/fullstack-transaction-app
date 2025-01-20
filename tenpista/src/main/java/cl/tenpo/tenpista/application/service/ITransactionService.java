package cl.tenpo.tenpista.application.service;


import cl.tenpo.tenpista.application.dto.Transaction;

import java.util.List;

public interface ITransactionService {

    /**
     * Creates a new transaction.
     *
     * @param transaction the transaction to create
     * @return the created transaction
     */
    Transaction createTransaction(Transaction transaction);

    /**
     * Retrieves all transactions.
     *
     * @return a list of all transactions
     */
    List<Transaction> getAllTransactions();

    /**
     * Retrieves a transaction by its ID.
     *
     * @param id the ID of the transaction
     * @return the transaction with the specified ID
     */
    Transaction getTransactionById(Long id);

    /**
     * Updates an existing transaction.
     *
     * @param id the ID of the transaction to update
     * @param transaction the updated transaction details
     * @return the updated transaction
     */
    Transaction updateTransaction(Long id, Transaction transaction);

    /**
     * Deletes a transaction by its ID.
     *
     * @param id the ID of the transaction to delete
     */
    void deleteTransaction(Long id);
}

