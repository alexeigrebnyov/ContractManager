package com.example.ContractManager.model;

public class Filter {
    String contactName;
    String contactNameOr;
    String contragentAlias;
    String contragentAliasOr;
    String store;
    String storeOr;
    String nomer;
    String nomerOr;
    String predmet;
    String predmetOr;
    String postavshik;
    String postavshikOr;
    String original;
    String originalOr;
    String righted;
    String rightedOr;
    String rekvisity;
    String rekvisityOr;
    String dateFrom;
    String dateFromOr;
    String dateTo;
    String dateToOr;

    public Filter(String contactName, String contragentAlias, String contactNameOr, String contragentAliasOr,
                  String store, String storeOr, String nomer, String dateFrom, String dateTo, String dateFromOr, String dateToOr, String nomerOr, String predmet, String predmetOr, String postavshik,
                  String postavshikOr,  String original,  String originalOr, String righted, String rightedOr, String rekvisity, String rekvisityOr) {
        this.contactName = contactName;
        this.contragentAlias = contragentAlias;
        this.contactNameOr = contactNameOr;
        this.contragentAliasOr = contragentAliasOr;
        this.store = store;
        this.storeOr = storeOr;
        this.nomer = nomer;
        this.nomerOr = nomerOr;
        this.predmet = predmet;
        this.predmetOr = predmetOr;
        this.postavshik = postavshik;
        this.postavshikOr = postavshikOr;
        this.original = original;
        this.originalOr = originalOr;
        this.righted = righted;
        this.rightedOr = rightedOr;
        this.rekvisity = rekvisity;
        this.rekvisityOr = rekvisityOr;
        this.dateFrom=dateFrom;
        this.dateTo=dateTo;
        this.dateFromOr=dateFromOr;
        this.dateToOr=dateToOr;
    }

    public Filter() {

    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContragentAlias() {
        return contragentAlias;
    }

    public void setContragentAlias(String contragentAlias) {
        this.contragentAlias = contragentAlias;
    }

    public String getContactNameOr() {
        return contactNameOr;
    }

    public void setContactNameOr(String contactNameOr) {
        this.contactNameOr = contactNameOr;
    }

    public String getContragentAliasOr() {
        return contragentAliasOr;
    }

    public void setContragentAliasOr(String contragentAliasOr) {
        this.contragentAliasOr = contragentAliasOr;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getStoreOr() {
        return storeOr;
    }

    public void setStoreOr(String storeOr) {
        this.storeOr = storeOr;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public String getNomerOr() {
        return nomerOr;
    }

    public void setNomerOr(String nomerOr) {
        this.nomerOr = nomerOr;
    }

    public String getPredmet() {
        return predmet;
    }

    public void setPredmet(String predmet) {
        this.predmet = predmet;
    }

    public String getPredmetOr() {
        return predmetOr;
    }

    public void setPredmetOr(String predmetOr) {
        this.predmetOr = predmetOr;
    }

    public String getPostavshik() {
        return postavshik;
    }

    public void setPostavshik(String postavshik) {
        this.postavshik = postavshik;
    }

    public String getPostavshikOr() {
        return postavshikOr;
    }

    public void setPostavshikOr(String postavshikOr) {
        this.postavshikOr = postavshikOr;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getOriginalOr() {
        return originalOr;
    }

    public void setOriginalOr(String originalOr) {
        this.originalOr = originalOr;
    }

    public String getRighted() {
        return righted;
    }

    public void setRighted(String righted) {
        this.righted = righted;
    }

    public String getRekvisity() {
        return rekvisity;
    }

    public void setRekvisity(String rekvisity) {
        this.rekvisity = rekvisity;
    }

    public String getRekvisityOr() {
        return rekvisityOr;
    }

    public void setRekvisityOr(String rekvisityOr) {
        this.rekvisityOr = rekvisityOr;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getDateFromOr() {
        return dateFromOr;
    }

    public void setDateFromOr(String dateFromOr) {
        this.dateFromOr = dateFromOr;
    }

    public String getDateToOr() {
        return dateToOr;
    }

    public void setDateToOr(String dateToOr) {
        this.dateToOr = dateToOr;
    }

    public String getRightedOr() {
        return rightedOr;
    }

    public void setRightedOr(String rightedOr) {
        this.rightedOr = rightedOr;
    }
}
