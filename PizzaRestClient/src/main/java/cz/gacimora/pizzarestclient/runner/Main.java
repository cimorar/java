/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.gacimora.pizzarestclient.runner;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import cz.gacimora.pizzarestclient.CustomerClient;
import cz.gacimora.pizzarestclient.entities.Customer;
import cz.gacimora.pizzarestclient.entities.Customers;
import cz.gacimora.pizzarestclient.entities.Pizza;
import cz.gacimora.pizzarestclient.entities.PizzaOrder;
import java.util.ArrayList;
import javax.ws.rs.ClientErrorException;

/**
 *
 * @author gacimora
 */
public class Main {

    public static void main(String[] args) {
        CustomerClient restClient = new CustomerClient();
        System.out.println("Number of records found: " + restClient.countREST());
        PrintAllCustomers(restClient);

        // create a newCustomer object and initialize it
        Customer newCustomer = new Customer();
        newCustomer.setName("Jaromir");
        newCustomer.setSurname("Jagr");

        // create a new Pizza object and initiliaze it
        Pizza p = new Pizza();
        p.setName("Pepperoni");
        p.setPrice("233");

        // create PizzaOrder object and assign Pizza to it
        PizzaOrder po = new PizzaOrder();
        po.getOrderedPizzas().add(p);

        // assign PizzaOrder to the newCustomer object
        newCustomer.getMyOrders().add(po);

        // call REST API to create a new customer and all cascaded objects
        restClient.create_JSON(newCustomer);

        // edit existing customer
        String customerId = "7";
        try {
            Customer existingCustomer = restClient.find_JSON(Customer.class, customerId);
            existingCustomer.setSurname("Rajska");
            restClient.edit_JSON(existingCustomer, customerId);
            System.out.println("Customer with id = " + customerId + " has been successfully edited.");
        } catch (Exception e) {
            System.out.println("Error when editing customer with id = " + customerId);
        }

        // display all customer again
        PrintAllCustomers(restClient);
    }

    // created by gacimora
    private static void PrintAllCustomers(CustomerClient restClient) throws ClientErrorException {
        System.out.println("********** Start of customer list ************");
        Customers customers = restClient.findAllCustomers_XML(Customers.class);
        for (Customer customer : customers.getCustomers()) {
            System.out.println("id: " + customer.getId() + ", name: " + customer.getName() + ", surname: " + customer.getSurname());
        }
        System.out.println("********** End of customer list ************");
    }
}
