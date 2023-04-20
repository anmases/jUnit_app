package org.example.jUnit_app.examples.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Banc {
    private String nom;
    private List<Compte> comptes;
    public Banc(){
        comptes = new ArrayList<>();
    }
    public Banc(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void transferir(Compte compte1, Compte compte2, BigDecimal bigDecimal){
        //Es resta la quantitat al primer compte:
           compte1.carrec(bigDecimal);
        //S'afegix al segon compte:
           compte2.abonament(bigDecimal);
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }
    public void setCompte_llista(Compte compte){
        compte.setBanc(this);
        this.comptes.add(compte);
    }
}
