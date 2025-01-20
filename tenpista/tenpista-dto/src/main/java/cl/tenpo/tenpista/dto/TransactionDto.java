package cl.tenpo.tenpista.dto;

import cl.tenpo.tenpista.dto.validation.PastOrPresentString;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Integer id;

    @NotNull(message = "The amount is required")
    @Min(value = 0, message = "The amount cannot be negative")
    private Integer amount;

    @NotBlank(message = "The merchant (comercio) name is required")
    private String merchant;

    @NotBlank(message = "The customer name (tenpista) is required")
    private String customer;

    @NotNull(message = "The transaction date is required")
    @PastOrPresentString(message = "The transaction date must not be set to a future date and time")
    private String transactionDate;

    @JsonIgnore()
    private String updatedAt;
}
