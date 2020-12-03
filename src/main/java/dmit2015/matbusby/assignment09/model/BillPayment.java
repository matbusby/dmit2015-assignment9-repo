package dmit2015.matbusby.assignment09.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "payments")
public class BillPayment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billPaymentId;

    @ManyToOne
    private Bill bill;

    @Column
    private BigDecimal paymentAmount;

    @Column
    private LocalDate paymentDate;
}
