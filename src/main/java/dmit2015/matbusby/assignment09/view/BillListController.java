package dmit2015.matbusby.assignment09.view;

import dmit2015.matbusby.assignment09.model.Bill;
import dmit2015.matbusby.assignment09.service.BillService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("currentBillListController")
@ViewScoped
public class BillListController implements Serializable {

    @Inject
    private BillService billService;

    private List<Bill> bills;

    @PostConstruct
    public void init() {
        bills = billService.findAll();
    }

    public List<Bill> list() { return billService.findAll(); }

}
