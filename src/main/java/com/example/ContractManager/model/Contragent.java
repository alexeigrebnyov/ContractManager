package com.example.ContractManager.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
public class Contragent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String alias;
    private String inn;
    @OneToMany(mappedBy = "contragent", cascade = CascadeType.PERSIST)
    private List<Contract> contractList;

    public Contragent() {
    }

    public Contragent(String name, String alias, String inn) {
        this.name = name;
        this.alias = alias;
        this.inn = inn;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contragent that = (Contragent) o;
        return Objects.equals(name, that.name) && Objects.equals(alias, that.alias) && Objects.equals(inn, that.inn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, alias, inn);
    }

    @Override
    public String toString() {
        return "Contragent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", inn='" + inn + '\'' +
                ", contractList='" + contractList + '\'' +
                '}';
    }

    // standard constructors / setters / getters / toString
}
