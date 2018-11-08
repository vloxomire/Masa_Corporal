package com.example.masa_corporal.Models_getter_y_setters;

public class MasaCorporalModels {
    private Integer Id;
    private String MasaCorporal;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getMasaCorporal() {
        return MasaCorporal;
    }

    public void setMasaCorporal(String masaCorporal) {
        MasaCorporal = masaCorporal;
    }

    public MasaCorporalModels(Integer id, String masaCorporal) {
        Id = id;
        MasaCorporal = masaCorporal;
    }
}
