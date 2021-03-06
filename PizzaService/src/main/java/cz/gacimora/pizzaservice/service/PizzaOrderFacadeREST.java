/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.gacimora.pizzaservice.service;

import cz.gacimora.pizzaservice.entities.Pizza;
import cz.gacimora.pizzaservice.entities.PizzaOrder;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author gacimora
 */
@Stateless
@Path("cz.gacimora.pizzaservice.entities.pizzaorder")
public class PizzaOrderFacadeREST extends AbstractFacade<PizzaOrder> {

    @PersistenceContext(unitName = "cz.gacimora_PizzaService_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public PizzaOrderFacadeREST() {
        super(PizzaOrder.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(PizzaOrder entity) {
        System.out.println("PizzaOrderFacadeREST: " + entity);
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, PizzaOrder entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public PizzaOrder find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PizzaOrder> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<PizzaOrder> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    // gacimora created
    @GET
    @Path("init")
    @Produces(MediaType.TEXT_PLAIN)
    public String initREST() {
        Pizza pizza = new Pizza();
        pizza.setName("Cheese_order");
        pizza.setPrice("129_order");
        
        PizzaOrder pizzaOrder = new PizzaOrder();
        List<Pizza> pizzaList = new ArrayList<>();
        
        pizzaList.add(pizza);
        
        pizzaOrder.setOrderedPizzas(pizzaList);        
        super.create(pizzaOrder);
        return "Ok";
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
