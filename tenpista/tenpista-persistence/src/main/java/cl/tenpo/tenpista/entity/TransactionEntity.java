package cl.tenpo.tenpista.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer amount;

    @Column(name = "merchant", nullable = false, length = 255)
    private String merchant;

    @Column(name = "customer", nullable = false, length = 255)
    private String customer;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
