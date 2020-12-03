package dmit2015.matbusby.assignment09.resource;

import dmit2015.matbusby.assignment09.model.Bill;
import dmit2015.matbusby.assignment09.service.BillService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Optional;

@ApplicationScoped
@Path("Bills")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BillResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private BillService billService;

    @POST
    public Response postBill(@Valid Bill newBill) {
        if (newBill == null) {
            throw new BadRequestException();
        }
        billService.create(newBill);
        URI billUri = uriInfo.getAbsolutePathBuilder().path(newBill.getBillId().toString()).build();
        return Response.created(billUri).build();
    }

    @GET
    @Path("{id}")
    public Response getBill(@PathParam("id") Long id) {
        Optional<Bill> optionalBill = billService.findByID(id);

        if (optionalBill.isEmpty()) {
            throw new NotFoundException();
        }
        Bill existingBill = optionalBill.get();

        return Response.ok(existingBill).build();
    }

    @GET
    public Response getAllBills() {
        return Response.ok(billService.findAll()).build();
    }

    @PUT
    @Path("{id}")
    public Response updateBill(@PathParam("id") Long id, @Valid Bill updatedBill) {
        if (!id.equals(updatedBill.getBillId())) {
            throw new BadRequestException();
        }

        Optional<Bill> optionalBill = billService.findByID(id);

        if (optionalBill.isEmpty()) {
            throw new NotFoundException();
        }

        billService.update(updatedBill);
        return  Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteTodoItem(@PathParam("id") Long id) {
        Optional<Bill> optionalBill = billService.findByID(id);

        if (optionalBill.isEmpty()) {
            throw new NotFoundException();
        }

        billService.remove(id);

        return Response.noContent().build();
    }
}
