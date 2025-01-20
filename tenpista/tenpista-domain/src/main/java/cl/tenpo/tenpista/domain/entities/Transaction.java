package cl.tenpo.tenpista.domain.entities;

import cl.tenpo.tenpista.domain.shared.DateTimeHelper;

import java.time.LocalDateTime;

public class Transaction {

    private Integer id;
    private Integer amount;
    private String merchant;
    private String customer;
    private LocalDateTime transactionDate;
    private LocalDateTime updatedAt;

    public Transaction() {
        // Default constructor
    }

    public Transaction(Integer id, Integer amount, String merchant, String customer, LocalDateTime transactionDate, LocalDateTime updatedAt) {
        this.id = id;
        this.amount = amount;
        this.merchant = merchant;
        this.customer = customer;
        this.transactionDate = transactionDate;
        this.updatedAt = updatedAt;
    }

    public boolean hasId() {
        return this.id > 0;
    }

    public String getTransactionDateFormatted() {
        return transactionDate.format(DateTimeHelper.DEFAULT_FORMATTER);
    }

    public String getUpdatedAtFormatted() {
        return updatedAt.format(DateTimeHelper.DEFAULT_FORMATTER);
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // toString() Method
    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", merchant='" + merchant + '\'' +
                ", tenpistaName='" + customer + '\'' +
                ", transactionDate=" + transactionDate +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

