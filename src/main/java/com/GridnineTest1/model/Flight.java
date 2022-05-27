package com.GridnineTest1.model;

import com.GridnineTest1.model.flight.*;

import java.util.List;

public class Flight {

    private Carrier carrier;
    private Price price;
    private ServicesStatuses servicesStatuses;
    private List<Legs> legs;
    private Exchange exchange;
    private boolean isTripartiteContractDiscountApplied;
    private boolean international;
    private List<Seats> seats;
    private Refund refund;

    public Flight(Carrier carrier, Price price, List<Legs> legs) {
        this.carrier = carrier;
        this.price = price;
        this.legs = legs;
    }

    public Carrier getCarrier() { return carrier;    }
    public void setCarrier(Carrier carrier) { this.carrier = carrier;    }
    public Price getPrice() { return price;    }
    public void setPrice(Price price) { this.price = price;    }
    public ServicesStatuses getServicesStatuses() { return servicesStatuses;    }
    public void setServicesStatuses(ServicesStatuses servicesStatuses) {  this.servicesStatuses = servicesStatuses;    }
    public List<Legs> getLegs() {  return legs;    }
    public void setLegs(List<Legs> legs) {  this.legs = legs;    }
    public Exchange getExchange() {   return exchange; }
    public void setExchange(Exchange exchange) {  this.exchange = exchange;    }
    public boolean isTripartiteContractDiscountApplied() {  return isTripartiteContractDiscountApplied;   }
    public void setTripartiteContractDiscountApplied(boolean tripartiteContractDiscountApplied) { isTripartiteContractDiscountApplied = tripartiteContractDiscountApplied;  }
    public boolean isInternational() {return international;    }
    public void setInternational(boolean international) {  this.international = international;   }
    public List<Seats> getSeats() {  return seats;   }
    public void setSeats(List<Seats> seats) {  this.seats = seats;   }
    public Refund getRefund() {  return refund;   }
    public void setRefund(Refund refund) {  this.refund = refund;  }


}
