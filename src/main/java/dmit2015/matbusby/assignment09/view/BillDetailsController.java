package dmit2015.matbusby.assignment09.view;

import dmit2015.matbusby.assignment09.model.Bill;
import dmit2015.matbusby.assignment09.service.BillService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named("currentBillDetailsController")
@RequestScoped
public class BillDetailsController {

    @Inject
    private BillService billService;

    @Inject @ManagedProperty("#{param.editId}")
    @Getter @Setter
    private Long editId;

    @Getter
    private Bill existingBill;

    @PostConstruct
    public void init() {
        Optional<Bill> optionalBill = billService.findByID(editId);
        if (optionalBill.isPresent()) {
            existingBill = optionalBill.get();
        }
    }
}
