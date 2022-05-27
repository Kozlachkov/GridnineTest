package com.GridnineTest1.model.flight;

import com.GridnineTest1.model.legs.Segments;

import java.util.List;

public class Legs {
    private long duration;
    private List<Segments> segments;

    public Legs(long duration, List<Segments> segments) {
        this.duration = duration;
        this.segments = segments;
    }

    public long getDuration() { return duration;   }
    public void setDuration(long duration) { this.duration = duration;    }
    public List<Segments> getSegments() {  return segments;  }
    public void setSegments(List<Segments> segments) {  this.segments = segments;    }

}
