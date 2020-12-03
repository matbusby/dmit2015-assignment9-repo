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


@Named("currentBillDeleteController")
@ViewScoped
public class BillDeleteController implements Serializable {

    @Inject
    private BillService billService;

    @Inject @ManagedProperty("#{param.editId}")
    @Getter @Setter
    private Long editId;

    @Getter
    private Bill existingBill;

    @PostConstruct
    public void init() {
        if (!Faces.isPostback() && editId != null) {
            Optional<Bill> optionalBill = billService.findByID(editId);
            if (optionalBill.isPresent()) {
                existingBill = optionalBill.get();
            } else {
                Messages.addGlobalFatal("There is no record with an id of {0} to delete.", editId);
            }
        }
    }

    public String onDelete() {
        String nextPage = "";
        try {
            billService.remove(existingBill.getBillId());
            Messages.addFlashGlobalInfo("Delete was successful.");
            nextPage = "manageBills?faces-redirect=true";
        } catch (Exception ex) {
            ex.printStackTrace();
            Messages.addGlobalError("Delete not successful.");
        }
        return nextPage;
    }
}
