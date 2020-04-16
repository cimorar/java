/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.racimora.pizzarestclient.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author radim
 */
@XmlRootElement
public class PizzaOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    
    private List<Pizza> pizza = new ArrayList();


    public List<Pizza> getPizza() {
        return pizza;
    }

    public void setPizza(List<Pizza> pizza) {
        this.pizza = pizza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PizzaOrder)) {
            return false;
        }
        PizzaOrder other = (PizzaOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PizzaOrder{" + "id=" + id + ", pizza=" + pizza + '}';
    }
    
}
