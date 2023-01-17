package com.example.ContractManager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public
class ImageContract {

    @Id
    @GeneratedValue
    Long id;

    @Lob
    byte[] content;

    String name;
    String location;

    public ImageContract(String imageName, String location) {
        this.name=imageName;
        this.location=location;
    }

    public ImageContract() {

    }

    public Long getId() {
        if (id != null)
        return id;
        return 0L;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        if (content != null)
        this.content = content;
        return;
    }

    public String getName() {
        if (name != null)
        return name;
        return "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
