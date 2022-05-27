package com.GridnineTest1.model;

import java.util.Comparator;

public class Flights {
    private boolean hasExtendedFare;
    private Flight flight;
    private String flightToken;

    public Flights(boolean hasExtendedFare, Flight flight, String flightToken) {
        this.hasExtendedFare = hasExtendedFare;
        this.flight = flight;
        this.flightToken = flightToken;
    }

    public Flights(Flight flight) {
        this.flight = flight;
    }

    public boolean isHasExtendedFare() {  return hasExtendedFare;   }
    public void setHasExtendedFare(boolean hasExtendedFare) {  this.hasExtendedFare = hasExtendedFare;    }
    public Flight getFlight() {  return flight;    }
    public void setFlight(Flight flight) { this.flight = flight;    }
    public String getFlightToken() {  return flightToken;    }
    public void setFlightToken(String flightToken) {  this.flightToken = flightToken;    }

}
