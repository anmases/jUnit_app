package org.example.jUnit_app.examples.models;

import org.example.exceptions.SaldoNegatiu;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.math.BigDecimal;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de test per als comptes
 * @author Antonio Mas Esteve
 */
class CompteTest {

///////////////////////////////////////////Proves before/after////////////////////////////////////////////////////

Compte compte;

    @BeforeAll
    public static void initAll(){
        System.out.println("Iniciant casos de prova -- Antonio Mas");
    }
    @AfterAll
    public static void endAll(){
        System.out.println("Finalitzant casos de prova -- Antonio Mas");
    }

    @BeforeEach
    void initMetodeTest(){
        this.compte = new Compte("Antonio", new BigDecimal("2000.00"));
        System.out.println("Inicia el métode");
    }
    @AfterEach
    void afterCadaMetode(){
        System.out.println("finalitzat el métode de prova");
    }
    @Nested
    class testSistemaOperatiu {
/////////////////////////////////////Test condicionals/////////////////////////////////////////////////

        /**
         * @author Antonio Mas
         */
        @Test
        @EnabledOnOs(OS.WINDOWS)
        void testNomesWindows() {
            System.out.println("Test de windows, si estás en Linux no s'executa");
        }

        /**
         * @author Antonio Mas
         */
        @Test
        @EnabledOnOs({OS.LINUX, OS.MAC})
        void testNomesUnix() {
            System.out.println("Test de Linux/Mac, si estás en Windows no s'executa");
        }

        /**
         * @author Antonio Mas
         */
        @Test
        @DisabledOnOs({OS.LINUX, OS.MAC})
        void testNomesUnixDis() {
            System.out.println("Este test no funciona en Linux/Mac");
        }
    }
    @Nested
    class versioJava {
    /**
     * @author Antonio Mas
     */
    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void testJava8() {
        //No el pasará ya que tenim java 17
        System.out.println("Este test es per a JAVA 8");
    }

    /**
     * @author Antonio Mas
     */
    @Test
    @EnabledOnJre(JRE.JAVA_17)
    @DisplayName("prova de imprimir en java les propietats - Antonio Mas")
    void testJava17() {
        Properties properties = System.getProperties();
        properties.forEach((k, v) -> System.out.println(k + ":" + v));
    }

}




    @Test
    @DisplayName("prova de nom del compte, Antonio Mas")
    void testNomCompte(){
        //Compte compteTest = new Compte("Abrines", new BigDecimal("3000"));
        //compteTest.setPersona("Abrines");

       String valorEsperat = "Antonio";
        String valorReal = this.compte.getPersona();

        assertNotNull(valorReal, "El compte ha de tindre un nom");

        assertEquals(valorEsperat, valorReal);
        assertTrue(valorReal.equals("Antonio"));
    }

    @Test
    @DisplayName("prova de saldo del compte, Antonio Mas")
    void testSaldoCompte(){
        //Compte compte = new Compte("Abrines", new BigDecimal("3000.302303"));
        assertEquals(2000.00, compte.getSaldo().doubleValue());
        assertFalse(compte.getSaldo().compareTo(BigDecimal.ZERO)<0);
        assertTrue(compte.getSaldo().compareTo(BigDecimal.ZERO)>0);
    }
    @Test
    @DisplayName("prova de comparación de comptes, Antonio Mas")
    void compareComptes(){
        Compte compte1 = new Compte("Antonio", new BigDecimal(3333.0909));
        Compte compte2 = new Compte("Antonio", new BigDecimal(3333.0909));

        //assertNotEquals(compte1, compte2);
        assertEquals(compte1, compte2);
    }
    //////////////////////////////Test de Carrec i abonament///////////////////////////
    @Test
    @DisplayName("prova de carrec del compte, Antonio Mas")
    void testCarrecCompte(){
        Compte compte = new Compte("Antonio", new BigDecimal("1234.5678"));
        compte.carrec(new BigDecimal("100"));
        assertNotNull(compte.getSaldo());
        assertEquals(new BigDecimal("1134.5678"), compte.getSaldo());

    }
    @Test
    @DisplayName("prova de abonament del compte, Antonio Mas")
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
    @DisplayName("prova de saldo negatiu del compte, Antonio Mas")
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
    @DisplayName("prova de vinculació bancs-comptes, Antonio Mas")
    @Disabled
    void testComptesBanc(){
        //Es creen els objectes:
        Compte compte1 = new Compte("Antonio", new BigDecimal("2500.54321"));
        Compte compte2 = new Compte("Alejandro", new BigDecimal("1000.1234"));
        
        Banc banc = new Banc();
        banc.setNom("Banc Ibars, error");
        //s'afegixen al llistat:
        banc.setCompte_llista(compte1);
        banc.setCompte_llista(compte2);

        //Tests:
        assertAll(
        //Es comproven les quantitats:
                ()-> assertEquals(new BigDecimal("2500.54321"), compte1.getSaldo(), ()->"El saldo no es l'esperat"),

                ()-> assertEquals(new BigDecimal("1000.1234"), compte2.getSaldo(),()->"El saldo no es l'esperat"),

        //Es comprova la quantitat de comptes que hi ha:
                ()-> assertEquals(2, banc.getComptes().size(),()->"El llistat de comptes no es l'esperat"),
        //Es comprova el nom del banc asociat al compte:
                ()-> assertEquals("Banc Ibars", compte1.getBanc().getNom(),()->"El nom del banc no es l'esperat"),

                ()-> assertEquals("Banc Ibars", compte2.getBanc().getNom(), "el nom del banc no es correcte"),

                ()-> assertTrue(banc.getComptes().stream().anyMatch(c->c.getPersona().equals("Alejandro")))
        );
    }

    /**
     * Repetició del test 5 vegades
     * @author Antonio Mas
     */
    @RepeatedTest(value = 5, name = "repetició de Antonio Mas {currentRepetition} de {totalRepetitions}")
    @DisplayName("prova de carrec del compte 2, Antonio Mas")
    void testCarrecCompte2(){
        Compte compte = new Compte("Antonio", new BigDecimal("1234.5678"));
        compte.carrec(new BigDecimal("100"));
        assertNotNull(compte.getSaldo());
        assertEquals(new BigDecimal("1134.5678"), compte.getSaldo());

    }

}