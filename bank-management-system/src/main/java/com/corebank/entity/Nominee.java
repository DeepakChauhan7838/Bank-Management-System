package com.corebank.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Nominee {
    private String nomineeName;
    private String nomineeRelation;
    private String nomineeDOb;
    private String nomineeContact;
    public Nominee() {
    }
    public Nominee(String nomineeName, String nomineeRelation, String nomineeDOb, String nomineeContact) {
        this.nomineeName = nomineeName;
        this.nomineeRelation = nomineeRelation;
        this.nomineeDOb = nomineeDOb;
        this.nomineeContact = nomineeContact;
    }
    public String getNomineeName() {
        return nomineeName;
    }
    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }
    public String getNomineeRelation() {
        return nomineeRelation;
    }
    public void setNomineeRelation(String nomineeRelation) {
        this.nomineeRelation = nomineeRelation;
    }
    public String getNomineeDOb() {
        return nomineeDOb;
    }
    public void setNomineeDOb(String nomineeDOb) {
        this.nomineeDOb = nomineeDOb;
    }
    public String getNomineeContact() {
        return nomineeContact;
    }
    public void setNomineeContact(String nomineeContact) {
        this.nomineeContact = nomineeContact;
    }

    
}
