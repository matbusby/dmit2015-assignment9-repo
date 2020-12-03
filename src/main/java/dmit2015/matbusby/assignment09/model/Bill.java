package dmit2015.matbusby.assignment09.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "bills")
public class Bill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BillId;

    @Column(nullable = false)
    @NotBlank(message = "Payee name must not be blank")
    @Size(min = 3, message = "Payee name must be at least 3 characters long")
    private String payeeName;

    @com.fasterxml.jackson.databind.annotation.JsonSerialize(using = com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer.class)
    @com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer.class)
    @com.fasterxml.jackson.annotation.JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    private LocalDate dueDate;

    @Column(nullable = true)
    private BigDecimal amountDue;

    @Column(nullable = true)
    private BigDecimal amountBalance;

}
