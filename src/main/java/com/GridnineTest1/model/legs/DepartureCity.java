package com.GridnineTest1.model.legs;

public class DepartureCity {
    private String uid;
    private String caption;

    public DepartureCity(String caption) {
        this.uid="";
        this.caption = caption;
    }

    public String getUid() { return uid;  }
    public void setUid(String uid) {  this.uid = uid;  }
    public String getCaption() {   return caption;  }
    public void setCaption(String caption) { this.caption = caption;    }
}
