package dmit2015.matbusby.assignment09.service;

import dmit2015.matbusby.assignment09.model.Bill;
import dmit2015.matbusby.assignment09.model.BillPayment;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class BillService {


    @PersistenceContext(unitName = "h2database-jpa-pu")
    private EntityManager em;

    public void create(Bill newBill) {
        em.persist(newBill);
    }

    public void update(Bill existingBill) {
        Optional<Bill> optionalQueryResult = findByID(existingBill.getBillId());
        if (optionalQueryResult.isPresent()) {
            Bill billToUpdate = optionalQueryResult.get();
            billToUpdate.setPayeeName(existingBill.getPayeeName());
            billToUpdate.setAmountBalance((existingBill.getAmountBalance()));
            billToUpdate.setAmountDue(existingBill.getAmountDue());
            billToUpdate.setDueDate(existingBill.getDueDate());
            em.merge(existingBill);
            em.flush();
        }
    }

    public void remove(Bill existingBill) {
        em.remove(em.merge(existingBill));
        em.flush();
    }

    public void remove(Long billId) {
        Optional<Bill> optionalBill = findByID(billId);
        if (optionalBill.isPresent()) {
            Bill existingBill = optionalBill.get();
            remove(existingBill);
        }
    }

    public void payBill(Long billId, BigDecimal paymentAmount) {
        BillPayment billPayment = new BillPayment();
        Optional<Bill> optionalBill = findByID(billId);
        if (optionalBill.isPresent()) {

            Bill existingBill = optionalBill.get();

            billPayment.setBill(existingBill);
            billPayment.setPaymentAmount(paymentAmount);
            billPayment.setPaymentDate(LocalDate.now());

            existingBill.setAmountBalance((existingBill.getAmountBalance().subtract(paymentAmount)));

            em.persist(billPayment);
            em.merge(existingBill);
            em.flush();
        }
    }

    public Optional<Bill> findByID(Long billId) {
        Optional<Bill> optionalBill = Optional.empty();
        try {
            Bill querySingleResult = em.find(Bill.class, billId);
            if (querySingleResult != null) {
                optionalBill = Optional.of(querySingleResult);
            }
        } catch (Exception ex) {
                ex.printStackTrace();
            }
        return optionalBill;
    }

    public List<Bill> findAll() {
        List<Bill> bills;
        bills = em.createQuery("SELECT b FROM Bill b ORDER BY b.payeeName", Bill.class).getResultList();
        return bills;
    }
}
