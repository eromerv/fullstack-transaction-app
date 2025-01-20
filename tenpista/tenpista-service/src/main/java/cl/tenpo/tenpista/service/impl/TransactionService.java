package cl.tenpo.tenpista.service.impl;

import cl.tenpo.tenpista.domain.entities.CustomerRoot;
import cl.tenpo.tenpista.domain.entities.Transaction;
import cl.tenpo.tenpista.domain.error.TransactionDeletionException;
import cl.tenpo.tenpista.domain.error.TransactionNotFoundException;
import cl.tenpo.tenpista.domain.repository.ITransactionRepository;
import cl.tenpo.tenpista.domain.shared.DateTimeHelper;
import cl.tenpo.tenpista.domain.shared.TransactionFactory;
import cl.tenpo.tenpista.dto.TransactionDto;
import cl.tenpo.tenpista.dto.mapper.TransactionMapper;
import cl.tenpo.tenpista.service.ITransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionService implements ITransactionService {

    @Autowired
    ITransactionRepository transactionRepository;

    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(transaction -> new TransactionDto(
                        transaction.getId(),
                        transaction.getAmount(),
                        transaction.getMerchant(),
                        transaction.getCustomer(),
                        transaction.getTransactionDateFormatted(),
                        transaction.getUpdatedAtFormatted()
                ))
                .collect(Collectors.toList());
    }


    @Override
    public TransactionDto getTransactionById(Integer id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with ID: " + id));

        return TransactionMapper.toDto(transaction);
    }


    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        String customerName = transactionDto.getCustomer();
        List<Transaction> transactions = transactionRepository.findAllByCustomer(customerName);
        if(!transactions.isEmpty()) {
            CustomerRoot customerRoot = new CustomerRoot(customerName, transactions);
            customerRoot.validateTransactions();
        }

        Transaction transaction = transactionRepository.insert(TransactionFactory.create(
                transactionDto.getAmount(),
                transactionDto.getMerchant(),
                transactionDto.getCustomer()
        ));

        return TransactionMapper.toDto(transaction);
    }

    @Override
    public TransactionDto updateTransaction(Integer id, TransactionDto transactionDto) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with ID: " + id));

        String customerName = transactionDto.getCustomer();
        List<Transaction> transactions = transactionRepository.findAllByCustomer(customerName);
        if(!transactions.isEmpty()) {
            CustomerRoot customerRoot = new CustomerRoot(customerName, transactions);
            customerRoot.validateTransactions();
        }

        transaction.setAmount(transactionDto.getAmount());
        transaction.setMerchant(transactionDto.getMerchant());
        transaction.setCustomer(transactionDto.getCustomer());
        transaction.setTransactionDate(DateTimeHelper.parseDate(transactionDto.getTransactionDate()));
        transaction.setUpdatedAt(LocalDateTime.now());

        Transaction updatedTransaction = transactionRepository.update(id, transaction);
        return TransactionMapper.toDto(updatedTransaction);
    }


    @Override
    public void deleteTransaction(Integer id) {
        boolean deleted = transactionRepository.delete(id);
        if (!deleted) {
            log.warn("Failed to delete transaction with ID: {}", id);
            throw new TransactionDeletionException("Transaction deletion failed for ID: " + id);
        }
    }
}
