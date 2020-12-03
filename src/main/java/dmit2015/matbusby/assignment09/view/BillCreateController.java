package dmit2015.matbusby.assignment09.view;

import dmit2015.matbusby.assignment09.model.Bill;
import dmit2015.matbusby.assignment09.service.BillService;
import lombok.Getter;
import org.omnifaces.util.Messages;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("currentBillCreateController")
@RequestScoped
public class BillCreateController {

    @Inject
    private BillService billService;

    @Getter
    private Bill newBill = new Bill();

    public String onCreateNew() {
        newBill.setAmountBalance(newBill.getAmountDue());
        String nextPage = "";
        try {
            billService.create(newBill);
            Messages.addFlashGlobalInfo("Create was successful.");
            nextPage = "index?Faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Create failed");
        }
        return nextPage;
    }
}
