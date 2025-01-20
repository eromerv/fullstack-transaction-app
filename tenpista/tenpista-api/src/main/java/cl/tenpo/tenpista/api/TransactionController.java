package cl.tenpo.tenpista.api;

import cl.tenpo.tenpista.dto.TransactionDto;
import cl.tenpo.tenpista.service.ITransactionService;
import com.giffing.bucket4j.spring.boot.starter.context.RateLimiting;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RateLimiting(name = "default")
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    ITransactionService transactionService;

    @Operation(summary = "Retrieve all transactions", description = "Fetches a list of all transactions in the system.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of transactions",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionDto.class)))
    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAll() {
        log.debug("Fetching all transactions");
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @Operation(summary = "Retrieve a transaction by ID", description = "Fetch a specific transaction by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionDto.class))),
            @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getById(@PathVariable Integer id) {
        log.debug("Fetching transaction with ID: {}", id);
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @Operation(summary = "Create a new transaction", description = "Registers a new transaction in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction successfully created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(
            @RequestBody @Valid TransactionDto request) {
        log.debug("Creating a new transaction with request: {}", request);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(request));
    }

    @Operation(summary = "Update an existing transaction", description = "Modifies details of an existing transaction.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction successfully updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionDto.class))),
            @ApiResponse(responseCode = "404", description = "Transaction not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(
            @PathVariable int id, @RequestBody @Valid TransactionDto request) {
        log.debug("Updating transaction ID: {} using request: {}", id, request);
        return ResponseEntity.ok(transactionService.updateTransaction(id, request));
    }

    @Operation(summary = "Delete a transaction", description = "Removes a transaction by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transaction successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Transaction not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer id) {
        log.debug("Deleting transaction ID: {}", id);
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
