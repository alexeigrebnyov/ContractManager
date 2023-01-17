package com.example.ContractManager.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class YurLitso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String alias;
    private String inn;
    private String kpp;
    private String bik;
    private String ogrn;
    private String okpo;
    private String rs;
    private String ks;
    private String bank;
    private String addres;
    private String tel;
    private String dir;

    @OneToMany (mappedBy = "rekvisity", cascade = CascadeType.PERSIST)
    private List<Contract> contracts;

    public YurLitso() {
    }

    public YurLitso(long id, String name, String inn, String kpp, String bik, String ogrn, String okpo, String rs, String ks, String bank,
                    String addres, String tel, String dir, List<Contract> contracts, String alias) {
        this.id = id;
        this.name = name;
        this.inn = inn;
        this.kpp = kpp;
        this.bik = bik;
        this.ogrn = ogrn;
        this.okpo = okpo;
        this.rs = rs;
        this.ks = ks;
        this.bank = bank;
        this.addres = addres;
        this.tel = tel;
        this.dir = dir;
        this.contracts = contracts;
        this.alias = alias;
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

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getBik() {
        return bik;
    }

    public void setBik(String bik) {
        this.bik = bik;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getKs() {
        return ks;
    }

    public void setKs(String ks) {
        this.ks = ks;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
