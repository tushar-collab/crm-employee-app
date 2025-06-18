package com.ps.assignment.employeeManagement.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bank")
public class Bank implements Serializable{

    private static Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "card_expire")
    private String cardExpire;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "currency")
    private String currency;

    @Column(name = "iban")
    private String iban;

    public Bank() {
    }

    // public Bank(Integer id, String cardExpire, String cardNumber, String cardType, String currency, String iban) {
    //     this.id = id;
    //     this.cardExpire = cardExpire;
    //     this.cardNumber = cardNumber;
    //     this.cardType = cardType;
    //     this.currency = currency;
    //     this.iban = iban;
    // }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardExpire() {
        return cardExpire;
    }

    public void setCardExpire(String cardExpire) {
        this.cardExpire = cardExpire;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "Bank [id=" + id + ", cardExpire=" + cardExpire + ", cardNumber=" + cardNumber + ", cardType=" + cardType
                + ", currency=" + currency + ", iban=" + iban + "]";
    }

    public Bank(String cardExpire, String cardNumber, String cardType, String currency, String iban) {
        this.cardExpire = cardExpire;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.currency = currency;
        this.iban = iban;
    }

    

}
