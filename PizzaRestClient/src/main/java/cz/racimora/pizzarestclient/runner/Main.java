/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.racimora.pizzarestclient.runner;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import cz.racimora.pizzarestclient.CustomerClient;
import cz.racimora.pizzarestclient.entities.Customer;
import cz.racimora.pizzarestclient.entities.Customers;
import cz.racimora.pizzarestclient.entities.Pizza;
import cz.racimora.pizzarestclient.entities.PizzaOrder;
import java.util.ArrayList;
import javax.ws.rs.ClientErrorException;

/**
 *
 * @author radim
 */
public class Main {
    public static void main (String[] args) {
        CustomerClient restClient = new CustomerClient();
        System.out.println("Pocet klientu: " + restClient.countREST());
        restClient.addCustomer();
        System.out.println("Pocet klientu: " + restClient.countREST());
        
        printAllCustomers(restClient);

        // create a newCustomer object and initialize it
        Customer newCustomer  = new Customer();
        newCustomer.setName("Marios");
        newCustomer.setSurname("Lemieux2");        
        
        
        // create a new Pizza object and initiliaze it
        Pizza p = new Pizza();
        p.setName("Funghi");
        p.setPrice("110");
        
        // create PizzaOrder object and assign Pizza in it
        PizzaOrder po = new PizzaOrder();        
        po.getPizza().add(p);      
        
        // assign PizzaOrder to the newCustomer object
        newCustomer.getMyOrder().add(po);
        
        // call REST API to create a new customer and all cascaded objects
        restClient.create_JSON(newCustomer);
        
        // edit existing customer
        String customerId = "5";
        Customer existingCustomer = restClient.find_JSON(Customer.class, customerId);
        existingCustomer.setSurname("Rajska");
        restClient.edit_JSON(existingCustomer, customerId);
        
        printAllCustomers(restClient);
    }

    private static void printAllCustomers(CustomerClient restClient) throws ClientErrorException {
        System.out.println("********** Start of customer list ************");
        Customers customers = restClient.findAllCustomers_XML(Customers.class);
        for (Customer customer : customers.getCustomers()) {
            System.out.println("id: " + customer.getId() +  ", name: " + customer.getName() + ", surname: " + customer.getSurname());
        }
        System.out.println("********** End of customer list ************");
    }
}
