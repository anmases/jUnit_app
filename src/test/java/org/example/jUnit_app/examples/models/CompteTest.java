package org.example.jUnit_app.examples.models;

import org.example.exceptions.SaldoNegatiu;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CompteTest {
    @Test
    void testNomCompte(){
        Compte compteTest = new Compte("Abrines", new BigDecimal("3000"));
        //compteTest.setPersona("Abrines");

        String valorEsperat = "Abrines";
        String valorReal = compteTest.getPersona();

        assertEquals(valorEsperat, valorReal);
        assertTrue(valorReal.equals("Abrines"));
    }

    @Test
    void testSaldoCompte(){
        Compte compte = new Compte("Abrines", new BigDecimal("3000.302303"));
        assertEquals(3000.302303, compte.getSaldo().doubleValue());
        assertFalse(compte.getSaldo().compareTo(BigDecimal.ZERO)<0);
        assertTrue(compte.getSaldo().compareTo(BigDecimal.ZERO)>0);
    }
    @Test
    void compareComptes(){
        Compte compte1 = new Compte("Antonio", new BigDecimal(3333.0909));
        Compte compte2 = new Compte("Antonio", new BigDecimal(3333.0909));

        //assertNotEquals(compte1, compte2);
        assertEquals(compte1, compte2);
    }
    //////////////////////////////Test de Carrec i abonament///////////////////////////
    @Test
    void testCarrecCompte(){
        Compte compte = new Compte("Antonio", new BigDecimal("1234.5678"));
        compte.carrec(new BigDecimal("100"));
        assertNotNull(compte.getSaldo());
        assertEquals(new BigDecimal("1134.5678"), compte.getSaldo());

    }
    @Test
    void testAbonamentCompte(){
        Compte compte = new Compte("Antonio", new BigDecimal("1234.5678"));
        compte.abonament(new BigDecimal("100"));
        assertNotNull(compte.getSaldo());
        assertEquals(new BigDecimal("1334.5678"), compte.getSaldo());
    }

    /**
     * @author Antonio Mas Esteve
     */
    @Test
    void testSaldoNegatiu(){
        //Contingut del test per al saldo negatiu:
        //Es crea el objecte:
        Compte compte = new Compte("Antonio", new BigDecimal("1000.1234"));
        //Es crea un objecte excepció que s'inicialitza si es complixen les condicions (ser negatiu)
        Exception exception = assertThrows(SaldoNegatiu.class, ()->{
           compte.carrec(new BigDecimal("1500"));
        });

        //Se definen los mensajes:
        String messageException = exception.getMessage();
        String missategeEsperat = "saldo negatiu";
        assertEquals(missategeEsperat, messageException);
    }

    @Test
    void testComptesBanc(){
        //Es creen els objectes:
        Compte compte1 = new Compte("Antonio", new BigDecimal("2500.54321"));
        Compte compte2 = new Compte("Alejandro", new BigDecimal("1000.1234"));
        
        Banc banc = new Banc();
        banc.setNom("Banc Ibars");
        //s'afegixen al llistat:
        banc.setCompte_llista(compte1);
        banc.setCompte_llista(compte2);
        //Es comprova la quantitat de comptes que hi ha:
        assertEquals(2, banc.getComptes().size());
        //Es comprova el nom del banc asociat al compte:
        assertEquals("Banc Ibars", compte1.getBanc().getNom());
        //Canviem el nom del banc del compte 2:
        compte2.setBanc(new Banc("Banc Chabas"));
        assertEquals("Banc Chabas", compte2.getBanc().getNom());

        assertTrue(banc.getComptes().stream().anyMatch(c->c.getPersona().equals("Alejandro")));
    }

}