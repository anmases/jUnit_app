package org.example.jUnit_app.examples.models;

import org.example.exceptions.SaldoNegatiu;

import java.math.BigDecimal;

public class Compte {
    private String persona;
    private BigDecimal saldo;
    private Banc banc;



    public Compte(){}

    public Compte(String persona, BigDecimal saldo) {
        this.persona = persona;
        this.saldo = saldo;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Banc getBanc() {
        return banc;
    }

    public void setBanc(Banc banc) {
        this.banc = banc;
    }

    @Override
    public boolean equals(Object obj) {
        Compte c = (Compte) obj;
        if(obj == null || (obj instanceof Compte)==false){
            return false;
        }
        if(this.persona.equals(c.getPersona()) && this.saldo.equals(c.getSaldo())){
           return true;
        }
        return false;
    }
    //////////////////////////////Regles de negoci////////////////////////////////////

    /**
     * Métode que afegeix un càrrec
     * @author Antonio Mas Esteve
     * @throw SaldoNegatiu
     */
    public void carrec(BigDecimal valor){
        //Creem una nova variable que será la resta:
        BigDecimal saldoAuxiliar = saldo.subtract(valor);
        //condició, que llançe una excepció si es negatiu:
        if(saldoAuxiliar.compareTo(BigDecimal.ZERO)<0){
            throw new SaldoNegatiu("saldo negatiu");
        }
        //Recalcula el saldo:
        saldo = saldoAuxiliar;
    }
   public void abonament(BigDecimal valor){
       saldo = saldo.add(valor);
   }



}
