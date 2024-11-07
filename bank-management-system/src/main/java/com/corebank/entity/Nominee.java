package com.corebank.entity;

import javax.persistence.*;

@Entity
@Table(name = "nominee")
public class Nominee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "nominee_id")
    private Long nomineeId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "relation", nullable = false)
    private String relation; // Relationship with the account holder

    @Column(name = "dob", nullable = false)
    private String dob; // Consider using LocalDate for better date handling

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account; // Linking to the Account entity

    // Constructors
    public Nominee() {
    }

    public Nominee(String name, String relation, String dob, String phoneNumber, Account account) {
        this.name = name;
        this.relation = relation;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.account = account;
    }

    // Getters and Setters
    public Long getNomineeId() {
        return nomineeId;
    }

    public void setNomineeId(Long nomineeId) {
        this.nomineeId = nomineeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Nominee{" +
                "nomineeId=" + nomineeId +
                ", name='" + name + '\'' +
                ", relation='" + relation + '\'' +
                ", dob='" + dob + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
