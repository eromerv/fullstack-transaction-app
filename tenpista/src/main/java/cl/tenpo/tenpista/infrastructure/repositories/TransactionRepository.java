public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Counts the number of transactions for a specific user.
     *
     * @param userName the username to count transactions for
     * @return the number of transactions for the user
     */
    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.userName = :userName")
    long countByUserName(@Param("userName") String userName);

    /**
     * Finds a transaction by its ID.
     *
     * @param id the ID of the transaction
     * @return an Optional containing the transaction, if found
     */
    Optional<Transaction> findById(Long id);

    /**
     * Retrieves all transactions.
     *
     * @return a list of all transactions
     */
    List<Transaction> findAll();

    /**
     * Saves a transaction.
     *
     * @param transaction the transaction to save
     * @return the saved transaction
     */
    Transaction save(Transaction transaction);

    /**
     * Deletes a transaction by its ID.
     *
     * @param id the ID of the transaction to delete
     */
    void deleteById(Long id);
}
