package org.acme.controller;



import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.CustomerDTO;
import org.acme.service.CustomerService;

import java.util.List;
import java.util.Optional;

@Path("/api/customers")
public class CustomerController {

    @Inject
    CustomerService customerService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON) //RETORNA PARA O FRONTEND A INFORMAÇÃO DO TIPO JSON
    public Response ResponsefindById(Long id){
        return Response.ok(customerService.findByid(id)).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON) //RETORNA PARA O FRONTEND A INFORMAÇÃO DO TIPO JSON
    public Response findAllCustomers(){
        return Response.ok(customerService.findAllCustomers()).build();
    }

    @POST
    @Transactional
    public Response saveCustomer(CustomerDTO customerDTO) throws Exception {
        customerService.creatNewCustomer(customerDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response changeCustomer(Long id, CustomerDTO customerDTO){
        try {
            customerService.changeCustomer(id,customerDTO);
            return Response.accepted().build();
        } catch (Exception e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCustomer(@PathParam("id") Long id){
        try {
            customerService.deleteCustomer(id);
            return Response.accepted().build();
        } catch (Exception e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }


}
