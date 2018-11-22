package com.example.pbetkows.wms.companies.electropoli.model;

public class GoodsReceiptN {

    private int id;
    private String indeks;
    private String description;
    private String barcode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndeks() {
        return indeks;
    }

    public void setIndeks(String indeks) {
        this.indeks = indeks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "GoodsReceiptN{" +
                "id=" + id +
                ", indeks='" + indeks + '\'' +
                ", description='" + description + '\'' +
                ", barcode='" + barcode + '\'' +
                '}';
    }
}
