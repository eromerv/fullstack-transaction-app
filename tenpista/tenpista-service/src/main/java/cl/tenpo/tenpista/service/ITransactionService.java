package cl.tenpo.tenpista.service;

import cl.tenpo.tenpista.dto.TransactionDto;

import java.util.List;

public interface ITransactionService {

    List<TransactionDto> getAllTransactions();

    TransactionDto getTransactionById(Integer id);

    TransactionDto createTransaction(TransactionDto transactionDto);

    TransactionDto updateTransaction(Integer id, TransactionDto transactionDetails);

    void deleteTransaction(Integer id);
}
