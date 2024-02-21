package com.example.banco;

public class Transfer {

    private int destiny;
    private int cuantity;
    private String date;


    public Transfer(int destiny, int cuantity, String date) {
        this.destiny = destiny;
        this.cuantity = cuantity;
        this.date = date;
    }

    public int getDestiny() {
        return destiny;
    }

    public void setDestiny(int destiny) {
        this.destiny = destiny;
    }

    public int getCuantity() {
        return cuantity;
    }

    public void setCuantity(int cuantity) {
        this.cuantity = cuantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
