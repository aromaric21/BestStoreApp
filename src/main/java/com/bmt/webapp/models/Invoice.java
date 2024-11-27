package com.bmt.webapp.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String storageFilname;

    private Date createdAt;

    @ManyToOne
    private Client client;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStorageFilname() {
        return storageFilname;
    }

    public void setStorageFilname(String storageFilname) {
        this.storageFilname = storageFilname;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
