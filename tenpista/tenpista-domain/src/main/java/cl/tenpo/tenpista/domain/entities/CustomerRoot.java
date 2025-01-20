package cl.tenpo.tenpista.domain.entities;

import cl.tenpo.tenpista.domain.error.TransactionLimitExceededException;

import java.util.List;

public class CustomerRoot {
    private String customer;
    private List<Transaction> transactionList;

    public CustomerRoot() {
    }

    public CustomerRoot(String customer, List<Transaction> transactionList) {
        this.customer = customer;
        this.transactionList = transactionList;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public void validateTransactions() {
        if (transactionList != null && transactionList.size() > 100) {
            throw new TransactionLimitExceededException("A customer cannot have more than 100 transactions");
        }
    }
}
