package org.example.jUnit_app.examples.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de tests per als bancs
 * @author Antonio Mas Esteve
 */
class BancTest {

    @Test
    void transferirDinersCompte() {
        //Es creen els objectes:
        Compte compte1 = new Compte("Antonio", new BigDecimal("2500.54321"));
        Compte compte2 = new Compte("Alejandro", new BigDecimal("1000.1234"));
        Banc banc = new Banc();
        banc.setNom("Banc Chabas");
        //Es crea la transferència (El métode no está implementat)
        banc.transferir(compte1, compte2, new BigDecimal("1000"));
        //Es comparen els saldos amb el que hauría de donar després de la transacció:
        assertEquals(new BigDecimal("1500.54321"), compte1.getSaldo());
        assertEquals(new BigDecimal("2000.1234"), compte2.getSaldo());
    }
}