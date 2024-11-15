package enums;

import java.time.LocalTime;

public enum GerarAutomaticoVoo {

    VOO1("AB123", CompanhiaAerea.EASYJET, 180, 50, 800.0, 300.0, new Data(2024, 10, 12), LocalTime.of(10, 30)),
    VOO2("CD456", CompanhiaAerea.TAP, 160, 40, 650.0, 250.0, new Data(2024, 7, 1), LocalTime.of(15, 45)),
    VOO3("EF789", CompanhiaAerea.LATAM, 200, 100, 1200.0, 350.0, new Data(2025, 1, 2), LocalTime.of(12, 0)),
    VOO4("GH012", CompanhiaAerea.AZUL, 220, 80, 1500.0, 450.0, new Data(2025, 11, 28), LocalTime.of(18, 0)),
    VOO5("IJ345", CompanhiaAerea.RYANAIR, 150, 75, 500.0, 200.0, new Data(2025, 7, 2), LocalTime.of(9, 15)),
    VOO6("KL678", CompanhiaAerea.AZUL, 190, 120, 1800.0, 500.0, new Data(2025, 8, 10), LocalTime.of(14, 30)),
    VOO7("MN901", CompanhiaAerea.EASYJET, 210, 60, 950.0, 375.0, new Data(2024, 10, 25), LocalTime.of(20, 0)),
    VOO8("OP234", CompanhiaAerea.RYANAIR, 170, 90, 1300.0, 420.0, new Data(2024, 6, 20), LocalTime.of(16, 15)),
    VOO9("QR567", CompanhiaAerea.TAP, 180, 60, 1100.0, 300.0, new Data(2026, 8, 1), LocalTime.of(8, 0)),
    VOO10("ST890", CompanhiaAerea.LATAM, 200, 50, 1400.0, 460.0, new Data(2026, 9, 27), LocalTime.of(11, 45));

    private final String codigoVoo;
    private final CompanhiaAerea companhiaAerea;
    private final int qntLugares;
    private final int qntLugaresDisponiveis;
    private final double distanciaKmAeroporto;
    private final double precoBilhete;
    private final Data dataPartida;
    private final LocalTime horaPartida;

    GerarAutomaticoVoo(String codigoVoo, CompanhiaAerea companhiaAerea, int qntLugares, int qntLugaresDisponiveis, double distanciaKmAeroporto, double precoBilhete, Data dataPartida, LocalTime horaPartida) {
        this.codigoVoo = codigoVoo;
        this.companhiaAerea = companhiaAerea;
        this.qntLugares = qntLugares;
        this.qntLugaresDisponiveis = qntLugaresDisponiveis;
        this.distanciaKmAeroporto = distanciaKmAeroporto;
        this.precoBilhete = precoBilhete;
        this.dataPartida = dataPartida;
        this.horaPartida = horaPartida;
    }

    public String getCodigoVoo() {
        return codigoVoo;
    }

    public CompanhiaAerea getCompanhiaAerea() {
        return companhiaAerea;
    }

    public int getQntLugares() {
        return qntLugares;
    }

    public int getQntLugaresDisponiveis() {
        return qntLugaresDisponiveis;
    }

    public double getDistanciaKmAeroporto() {
        return distanciaKmAeroporto;
    }

    public double getPrecoBilhete() {
        return precoBilhete;
    }

    public Data getDataPartida() {
        return dataPartida;
    }

    public LocalTime getHoraPartida() {
        return horaPartida;
    }
}

