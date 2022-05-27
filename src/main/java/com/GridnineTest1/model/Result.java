package com.GridnineTest1.model;

import java.util.List;

public class Result {
    private List<Flights> flights;
    private BestPrices bestPrices;

    public Result(List<Flights> flights) {
        this.flights = flights;
    }

    public BestPrices getBestPrices() {  return bestPrices;    }
    public void setBestPrices(BestPrices bestPrices) {   this.bestPrices = bestPrices;    }

    public List<Flights> getFlights() {return flights;    }
    public void setFlights(List<Flights> flights) {  this.flights = flights;   }

    @Override
    public String toString() {
        return "Result consist { flights=" + flights.size() + /* ", " +
                "bestPrices=" + bestPrices + */ '}';
    }


}
