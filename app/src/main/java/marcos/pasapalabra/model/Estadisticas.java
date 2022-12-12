package marcos.pasapalabra.model;

public class Estadisticas {
    private static Estadisticas instance;
    private int acertadas;
    private int falladas;

    private Estadisticas() {
        this.acertadas = 0;
        this.falladas = 0;
    }

    public static Estadisticas getInstance() {
        if (instance == null) {
            instance = new Estadisticas();
        }
        return instance;
    }

    public void reset() {
        this.acertadas = 0;
        this.falladas = 0;
    }

    public void preguntaAcertada() {
        this.acertadas++;
    }

    public void preguntaFallada() {
        this.falladas++;
    }

    public int getAcertadas() {
        return acertadas;
    }

    public int getFalladas() {
        return falladas;
    }
}
