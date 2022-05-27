package com.GridnineTest1.model.flight;

public class Carrier {
    private String uid;
    private String caption;
    private String airlineCode;

    public Carrier(String caption) {
        this.caption = caption;
    }

    public String getUid() {  return uid;   }
    public void setUid(String uid) { this.uid = uid;    }
    public String getCaption() {  return caption;    }
    public void setCaption(String caption) {  this.caption = caption;    }
    public String getAirlineCode() { return airlineCode;    }
    public void setAirlineCode(String airlineCode) {  this.airlineCode = airlineCode;    }
}
