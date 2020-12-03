package dmit2015.matbusby.assignment09.view;

import dmit2015.matbusby.assignment09.model.Bill;
import dmit2015.matbusby.assignment09.service.BillService;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

@Named("currentBillPaymentController")
@ViewScoped
public class BillPaymentController implements Serializable {

    @Inject
    private BillService billService;

    @Inject @ManagedProperty("#{param.editId}")
    @Getter @Setter
    private Long editId;

    @Getter
    private Bill existingBill;

    @Getter
    private BigDecimal payment;

    @PostConstruct
    public void init() {
        Optional<Bill> optionalBill = billService.findByID(editId);
        if (optionalBill.isPresent()) {
            existingBill = optionalBill.get();
        }
    }

    public String payBill(BigDecimal payment) {
        String nextPage = "";
        try {
            billService.payBill(editId, payment);
            Messages.addFlashGlobalInfo("Bill payment was successful");
            return "manageBills?faces-redirect=true";
        } catch (Exception e) {
            Messages.addGlobalError("Payment was not successful");
        }
        return nextPage;
    }

}
