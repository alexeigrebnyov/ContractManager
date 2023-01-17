package com.example.ContractManager.model;

import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    @ManyToOne (fetch = FetchType.LAZY)
    private YurLitso rekvisity;

    private String postavshik;
    private String nomer;
    private String predmet;
    private String righted;
    private String original;


    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contragent contragent;


    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    @OneToOne
    private ImageContract image;

    public Contract() {
    }

    public Contract(String name, String date, Contragent contragent, Store store, YurLitso rekvisity) {
        this.name = name;
        this.date = date;
        this.contragent = contragent;
        this.store = store;
        this.rekvisity=rekvisity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Contragent getContragent() {
        return contragent;
    }

    public void setContragent(Contragent contragent) {
        this.contragent = contragent;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public YurLitso getRekvisity() {
        return rekvisity;
    }

    public void setRekvisity(YurLitso rekvisity) {
        this.rekvisity = rekvisity;
    }

    public String getPostavshik() {
        return postavshik;
    }

    public void setPostavshik(String postavshik) {
        this.postavshik = postavshik;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getRighted() {
        if (righted !=null)
        return righted;
        return "";
    }

    public void setRighted(String righted) {
        this.righted = righted;
    }

    public String getOriginal() {

        if (original !=null)
            return original;
        return "";
    }

    public void setOriginal(String original) {
        this.original = original;
    }
    @Transient
    public ImageContract getImage() {
        return image;

    }

    public void setImage(ImageContract image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return Objects.equals(name, contract.name) && Objects.equals(date, contract.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date);
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rekvisity='" + rekvisity.getAlias()+ '\'' +
                ", postavshik='" + postavshik + '\'' +
                ", nomer='" + nomer + '\'' +
                ", predmet='" + predmet + '\'' +
                ", righted=" + righted +
                ", original=" + original +
                ", date='" + date + '\'' +
                ", contragent=" + contragent.getAlias() +
                ", store=" + store +
                ", image=" + image.getName() +
                '}';
    }
    // standard constructors / setters / getters / toString

}
