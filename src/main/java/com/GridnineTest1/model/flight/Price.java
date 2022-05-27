package com.GridnineTest1.model.flight;

import com.GridnineTest1.model.flight.PriceClass.PassengerPrices;
import com.GridnineTest1.model.flight.PriceClass.Rates;
import com.GridnineTest1.model.flight.PriceClass.Total;
import com.GridnineTest1.model.flight.PriceClass.TotalFeeAndTaxes;

import java.util.List;

public class Price {
    private Total total;
    private TotalFeeAndTaxes totalFeeAndTaxes;
    private Rates rates;
    private List<PassengerPrices> passengerPrices;

    public Price(Total total) {
        this.total = total;
    }

    public Total getTotal() {return total;  }
    public void setTotal(Total total) {  this.total = total;    }
    public TotalFeeAndTaxes getTotalFeeAndTaxes() {  return totalFeeAndTaxes;    }
    public void setTotalFeeAndTaxes(TotalFeeAndTaxes totalFeeAndTaxes) {  this.totalFeeAndTaxes = totalFeeAndTaxes;    }
    public Rates getRates() {  return rates;    }
    public void setRates(Rates rates) {  this.rates = rates;    }
    public List<PassengerPrices> getPassengerPrices() {  return passengerPrices;  }
    public void setPassengerPrices(List<PassengerPrices> passengerPrices) { this.passengerPrices = passengerPrices;  }
}
