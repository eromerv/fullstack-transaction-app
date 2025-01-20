package cl.tenpo.tenpista.application.service.impl;

import cl.tenpo.tenpista.application.dto.Transaction;
import cl.tenpo.tenpista.application.service.ITransactionService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TransactionServiceImpl implements ITransactionService {
    @Override
    public Transaction createTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }

    @Override
    public Transaction getTransactionById(String id) {
        Transaction transaction = new Transaction();
        transaction.se
        return transactionDto;
    }

    @Override
    public Transaction updateTransaction(Long id, Transaction transaction) {
        return null;
    }

    @Override
    public void deleteTransaction(Long id) {

    }
}
