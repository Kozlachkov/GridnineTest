package com.GridnineTest1.model.flight.PriceClass;

public class Total {
    private String amount;
    private String currency;
    private String currencyCode;

    public Total(String amount) {
        this.amount = amount;
        this.currency = "";
        this.currencyCode="";
    }

    public String getAmount() {   return amount;    }
    public void setAmount(String amount) { this.amount = amount;    }
    public String getCurrency() {  return currency;    }
    public void setCurrency(String currency) { this.currency = currency;    }
    public String getCurrencyCode() {  return currencyCode;    }
    public void setCurrencyCode(String currencyCode) {this.currencyCode = currencyCode;    }
}
