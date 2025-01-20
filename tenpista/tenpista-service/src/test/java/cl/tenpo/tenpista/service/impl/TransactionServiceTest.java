package cl.tenpo.tenpista.service.impl;

import cl.tenpo.tenpista.domain.entities.Transaction;
import cl.tenpo.tenpista.domain.error.TransactionDeletionException;
import cl.tenpo.tenpista.domain.error.TransactionNotFoundException;
import cl.tenpo.tenpista.domain.repository.ITransactionRepository;
import cl.tenpo.tenpista.domain.shared.DateTimeHelper;
import cl.tenpo.tenpista.dto.TransactionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private ITransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction testTransaction;
    private TransactionDto testTransactionDto;

    @BeforeEach
    void setUp() {
        testTransaction = new Transaction();
        testTransaction.setId(1);
        testTransaction.setAmount(1000);
        testTransaction.setMerchant("Test Merchant");
        testTransaction.setCustomer("John Doe");
        testTransaction.setTransactionDate(LocalDateTime.now());
        testTransaction.setUpdatedAt(LocalDateTime.now());

        String testDate = LocalDateTime.now().format(DateTimeHelper.DEFAULT_FORMATTER);
        testTransactionDto = new TransactionDto(1, 1000, "Test Merchant", "John Doe", testDate, testDate);    }

    @Test
    void testGetAllTransactions() {
        when(transactionRepository.findAll()).thenReturn(Collections.singletonList(testTransaction));

        var transactions = transactionService.getAllTransactions();

        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void testGetTransactionById_Success() {
        when(transactionRepository.findById(1)).thenReturn(Optional.of(testTransaction));

        var transaction = transactionService.getTransactionById(1);

        assertNotNull(transaction);
        assertEquals(testTransactionDto.getId(), transaction.getId());
        verify(transactionRepository, times(1)).findById(1);
    }

    @Test
    void testGetTransactionById_NotFound() {
        when(transactionRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(TransactionNotFoundException.class, () -> transactionService.getTransactionById(1));
        verify(transactionRepository, times(1)).findById(1);
    }

    @Test
    void testCreateTransaction() {
        when(transactionRepository.findAllByCustomer("John Doe")).thenReturn(Collections.emptyList());
        when(transactionRepository.insert(any(Transaction.class))).thenReturn(testTransaction);

        var createdTransaction = transactionService.createTransaction(testTransactionDto);

        assertNotNull(createdTransaction);
        assertEquals(testTransactionDto.getAmount(), createdTransaction.getAmount());
        verify(transactionRepository, times(1)).findAllByCustomer("John Doe");
        verify(transactionRepository, times(1)).insert(any(Transaction.class));
    }

    @Test
    void testUpdateTransaction_Success() {
        when(transactionRepository.findById(1)).thenReturn(Optional.of(testTransaction));
        when(transactionRepository.update(eq(1), any(Transaction.class))).thenReturn(testTransaction);

        var updatedTransaction = transactionService.updateTransaction(1, testTransactionDto);

        assertNotNull(updatedTransaction);
        assertEquals(testTransactionDto.getAmount(), updatedTransaction.getAmount());
        verify(transactionRepository, times(1)).findById(1);
        verify(transactionRepository, times(1)).update(eq(1), any(Transaction.class));
    }

    @Test
    void testUpdateTransaction_NotFound() {
        when(transactionRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(TransactionNotFoundException.class, () -> transactionService.updateTransaction(1, testTransactionDto));
        verify(transactionRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteTransaction_Success() {
        when(transactionRepository.delete(1)).thenReturn(true);

        assertDoesNotThrow(() -> transactionService.deleteTransaction(1));
        verify(transactionRepository, times(1)).delete(1);
    }

    @Test
    void testDeleteTransaction_Failure() {
        when(transactionRepository.delete(1)).thenReturn(false);

        assertThrows(TransactionDeletionException.class, () -> transactionService.deleteTransaction(1));
        verify(transactionRepository, times(1)).delete(1);
    }
}
