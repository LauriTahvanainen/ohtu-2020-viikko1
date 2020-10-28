package ohtu.ohtuvarasto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriNegatiivisellaTilavuudellaLuoNollaTilavuudenVaraston() {
        this.varasto = new Varasto(-1);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuKonstruktoriLuoOikeanTilavuudenVaraston() {
        this.varasto = new Varasto(10, 2);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuKonstruktoriNegatiivisellaTilavuudellaNollaTilavuudenVaraston() {
        this.varasto = new Varasto(-1, 10);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuKonstruktoriPienemmallaTilavuudellaKuinSaldollaLuoTaydenVarastonHukkaaYlimaaraisen() {
        this.varasto = new Varasto(5, 10);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuKonstruktoriTayttaaOikeanAlkuSaldon() {
        this.varasto = new Varasto(10, 5);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuNegatiivisellaAlkuSaldollaLuoTyhjanVaraston() {
        this.varasto = new Varasto(10, -1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisenMaaranOttaminenPalauttaaNolla() {
        varasto.lisaaVarastoon(8);

        assertEquals(0.0, varasto.otaVarastosta(-0.1), vertailuTarkkuus);
    }

    @Test
    public void yliVarastonOttaminenPalauttaaKokoVaraston() {
        varasto.lisaaVarastoon(8);

        assertEquals(8, varasto.otaVarastosta(20), vertailuTarkkuus);
    }

    @Test
    public void yliVarastonOttaminenTyhjentaaVaraston() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(20);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisenMaaranLisaaminenEiMuutaVarastonTilaa() {
        varasto.lisaaVarastoon(2);
        varasto.lisaaVarastoon(-1);

        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tilavuuttaSuuremmanMaaranLisaaminenTayttaaVarastonJaHukkaaLoput() {
        varasto.lisaaVarastoon(11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void merkkijonoEsitysPalautaaOikein() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(5);

        assertEquals("saldo = 3.0, vielä tilaa 7.0", varasto.toString());
    }

}
