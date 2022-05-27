package com.GridnineTest1.model.legs;

import java.util.List;

public class Segments {
    private char classOfServiceCode;
    private ClassOfService classOfService;
    private DepartureAirport departureAirport;
    private DepartureCity departureCity;
    private Aircraft aircraft;
    private long travelDuration;
    private ArrivalCity arrivalCity;
    private ArrivalDate arrivalDate;
    private long flightNumber;
    private List<TechStopInfos> techStopInfos;
    private DepartureDate departureDate;
    private long stops;
    private ServicesDetails servicesDetails;
    private Airline airline;
    private boolean starting;
    private ArrivalAirport arrivalAirport;

    public Segments(DepartureCity departureCity, ArrivalCity arrivalCity) {
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
    }

    public char getClassOfServiceCode() {  return classOfServiceCode;    }
    public void setClassOfServiceCode(char classOfServiceCode) {  this.classOfServiceCode = classOfServiceCode;    }
    public ClassOfService getClassOfService() {  return classOfService;   }
    public void setClassOfService(ClassOfService classOfService) {   this.classOfService = classOfService;    }
    public DepartureAirport getDepartureAirport() { return departureAirport;    }
    public void setDepartureAirport(DepartureAirport departureAirport) { this.departureAirport = departureAirport;    }
    public DepartureCity getDepartureCity() {  return departureCity;    }
    public void setDepartureCity(DepartureCity departureCity) {   this.departureCity = departureCity;    }
    public Aircraft getAircraft() {   return aircraft;    }
    public void setAircraft(Aircraft aircraft) { this.aircraft = aircraft;    }
    public long getTravelDuration() {  return travelDuration;   }
    public void setTravelDuration(long travelDuration) {   this.travelDuration = travelDuration;    }
    public ArrivalCity getArrivalCity() {    return arrivalCity;    }
    public void setArrivalCity(ArrivalCity arrivalCity) {  this.arrivalCity = arrivalCity;   }
    public ArrivalDate getArrivalDate() {  return arrivalDate;   }
    public void setArrivalDate(ArrivalDate arrivalDate) {  this.arrivalDate = arrivalDate;    }
    public long getFlightNumber() {   return flightNumber;    }
    public void setFlightNumber(long flightNumber) {   this.flightNumber = flightNumber;    }
    public List<TechStopInfos> getTechStopInfos() {   return techStopInfos;    }
    public void setTechStopInfos(List<TechStopInfos> techStopInfos) {  this.techStopInfos = techStopInfos;  }
    public DepartureDate getDepartureDate() {    return departureDate;    }
    public void setDepartureDate(DepartureDate departureDate) {   this.departureDate = departureDate;    }
    public long getStops() {  return stops;   }
    public void setStops(long stops) { this.stops = stops;   }
    public ServicesDetails getServicesDetails() {  return servicesDetails;    }
    public void setServicesDetails(ServicesDetails servicesDetails) {  this.servicesDetails = servicesDetails;    }
    public Airline getAirline() { return airline; }
    public void setAirline(Airline airline) {  this.airline = airline;    }
    public boolean isStarting() {  return starting;    }
    public void setStarting(boolean starting) {   this.starting = starting;    }
    public ArrivalAirport getArrivalAirport() {   return arrivalAirport;    }
    public void setArrivalAirport(ArrivalAirport arrivalAirport) {  this.arrivalAirport = arrivalAirport; }


}
