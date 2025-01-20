package com.tenpista.transactionmanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private Integer amount;

    @NotBlank
    private String merchant;

    @NotBlank
    @Column(name = "user_name")
    private String userName;

    @NotNull
    @PastOrPresent
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;
}
