package dmit2015.matbusby.assignment09.view;

import dmit2015.matbusby.assignment09.model.Bill;
import dmit2015.matbusby.assignment09.service.BillService;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;

@Named("currentBillEditController")
@ViewScoped
public class BillEditController implements Serializable {
//    private static final long serialVersionUID = 1L;

    @Inject @ManagedProperty("#{param.editId}")
    @Getter @Setter
    private Long editId;

    @Inject
    private BillService billService;

    @Getter
    private Bill existingBill;

    @PostConstruct
    public void init() {
        if (!Faces.isPostback()) {
            Optional<Bill> optionalBill = billService.findByID(editId);
            if (optionalBill.isPresent()) {
                existingBill = optionalBill.get();
            } else {
                Messages.addGlobalInfo("A valid billId is required");
            }
        }
    }

    public String updateBill() {
        String nextPage = "";
        try {
        billService.update(existingBill);
        Messages.addFlashGlobalInfo("Bill update was successful");
        return "manageBills?faces-redirect=true";
        } catch (Exception e) {
            Messages.addGlobalError("Update was not successful");
        }
        return nextPage;
    }

}
