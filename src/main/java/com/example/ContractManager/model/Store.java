package com.example.ContractManager.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String name;

    @OneToMany(mappedBy = "store", cascade = CascadeType.PERSIST)
    private List<Contract> contracts;

    public Store() {
    }

    public Store(long id, String name, List<Contract> contracts) {
        this.id = id;
        this.name = name;
        this.contracts = contracts;
    }

    public Store(String name) {
        this.name = name;
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

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
}
