package enums;

import utilidades.Data;

import java.time.LocalTime;

/**
 * Enum {@code GerarAutomaticoVoo} representa os voos disponíveis, tanto de ida quanto de volta, contendo informações
 * sobre o código do voo, a companhia aérea, a quantidade de lugares, os lugares disponíveis, a distância até o aeroporto,
 * o preço do bilhete, a data e a hora de partida.
 */
public enum GerarAutomaticoVoo {

    /** Voo de ida 1: Código AB123, companhia EASYJET, 180 lugares, 50 disponíveis, distância 800 km, preço 300.0, data 28/11/2024, hora 10:30. */
    VOO1("AB123", CompanhiaAerea.EASYJET, 180, 180, 800.0, 300.0, new Data(2024, 11, 28), LocalTime.of(10, 30)),

    /** Voo de ida 2: Código CD456, companhia TAP, 160 lugares, 40 disponíveis, distância 650 km, preço 250.0, data 05/12/2024, hora 15:45. */
    VOO2("CD456", CompanhiaAerea.TAP, 160, 160, 650.0, 250.0, new Data(2024, 12, 5), LocalTime.of(15, 45)),

    /** Voo de ida 3: Código EF789, companhia LATAM, 200 lugares, 100 disponíveis, distância 1200 km, preço 350.0, data 07/12/2025, hora 12:00. */
    VOO3("EF789", CompanhiaAerea.LATAM, 200, 200, 1200.0, 350.0, new Data(2025, 12, 7), LocalTime.of(12, 0)),

    /** Voo de ida 4: Código GH012, companhia AZUL, 220 lugares, 80 disponíveis, distância 1500 km, preço 450.0, data 10/12/2025, hora 18:00. */
    VOO4("GH012", CompanhiaAerea.AZUL, 220, 220, 1500.0, 450.0, new Data(2025, 12, 10), LocalTime.of(18, 0)),

    /** Voo de ida 5: Código IJ345, companhia RYANAIR, 150 lugares, 75 disponíveis, distância 500 km, preço 200.0, data 15/12/2025, hora 09:15. */
    VOO5("IJ345", CompanhiaAerea.RYANAIR, 150, 150, 500.0, 200.0, new Data(2025, 12, 15), LocalTime.of(9, 15)),

    // Voos de volta

    /** Voo de volta 1: Código KL678, companhia AZUL, 190 lugares, 120 disponíveis, distância 1800 km, preço 500.0, data 01/01/2025, hora 14:30. */
    VOO6("KL678", CompanhiaAerea.AZUL, 190, 190, 1800.0, 500.0, new Data(2025, 1, 1), LocalTime.of(14, 30)),

    /** Voo de volta 2: Código MN901, companhia EASYJET, 210 lugares, 60 disponíveis, distância 950 km, preço 375.0, data 06/02/2025, hora 20:00. */
    VOO7("MN901", CompanhiaAerea.EASYJET, 210, 210, 950.0, 375.0, new Data(2025, 2, 6), LocalTime.of(20, 0)),

    /** Voo de volta 3: Código OP234, companhia RYANAIR, 170 lugares, 90 disponíveis, distância 1300 km, preço 420.0, data 20/03/2025, hora 16:15. */
    VOO8("OP234", CompanhiaAerea.RYANAIR, 170, 170, 1300.0, 420.0, new Data(2025, 3, 20), LocalTime.of(16, 15)),

    /** Voo de volta 4: Código QR567, companhia TAP, 180 lugares, 60 disponíveis, distância 1100 km, preço 300.0, data 06/04/2025, hora 08:00. */
    VOO9("QR567", CompanhiaAerea.TAP, 180, 180, 1100.0, 300.0, new Data(2025, 4, 6), LocalTime.of(8, 0)),

    /** Voo de volta 5: Código ST890, companhia LATAM, 200 lugares, 50 disponíveis, distância 1400 km, preço 460.0, data 10/05/2025, hora 11:45. */
    VOO10("ST890", CompanhiaAerea.LATAM, 200, 200, 1400.0, 460.0, new Data(2025, 5, 10), LocalTime.of(11, 45));

    /** O código do voo. */
    private final String codigoVoo;

    /** A companhia aérea do voo. */
    private final CompanhiaAerea companhiaAerea;

    /** A quantidade total de lugares no voo. */
    private final int qntLugares;

    /** A quantidade de lugares disponíveis no voo. */
    private final int qntLugaresDisponiveis;

    /** A distância até o aeroporto de destino, em quilômetros. */
    private final double distanciaKmAeroporto;

    /** O preço do bilhete para o voo. */
    private final double precoBilhete;

    /** A data de partida do voo. */
    private final Data dataPartida;

    /** A hora de partida do voo. */
    private final LocalTime horaPartida;

    /**
     * Constrói uma nova instância de {@code GerarAutomaticoVoo} com as informações do voo.
     *
     * @param codigoVoo O código do voo.
     * @param companhiaAerea A companhia aérea do voo.
     * @param qntLugares A quantidade total de lugares no voo.
     * @param qntLugaresDisponiveis A quantidade de lugares disponíveis no voo.
     * @param distanciaKmAeroporto A distância até o aeroporto de destino.
     * @param precoBilhete O preço do bilhete.
     * @param dataPartida A data de partida do voo.
     * @param horaPartida A hora de partida do voo.
     */
    GerarAutomaticoVoo(String codigoVoo, CompanhiaAerea companhiaAerea, int qntLugares, int qntLugaresDisponiveis,
                       double distanciaKmAeroporto, double precoBilhete, Data dataPartida, LocalTime horaPartida) {
        this.codigoVoo = codigoVoo;
        this.companhiaAerea = companhiaAerea;
        this.qntLugares = qntLugares;
        this.qntLugaresDisponiveis = qntLugaresDisponiveis;
        this.distanciaKmAeroporto = distanciaKmAeroporto;
        this.precoBilhete = precoBilhete;
        this.dataPartida = dataPartida;
        this.horaPartida = horaPartida;
    }

    /**
     * Retorna o código do voo.
     *
     * @return O código do voo.
     */
    public String getCodigoVoo() {
        return codigoVoo;
    }

    /**
     * Retorna a companhia aérea do voo.
     *
     * @return A companhia aérea.
     */
    public CompanhiaAerea getCompanhiaAerea() {
        return companhiaAerea;
    }

    /**
     * Retorna a quantidade total de lugares no voo.
     *
     * @return A quantidade total de lugares.
     */
    public int getQntLugares() {
        return qntLugares;
    }

    /**
     * Retorna a quantidade de lugares disponíveis no voo.
     *
     * @return A quantidade de lugares disponíveis.
     */
    public int getQntLugaresDisponiveis() {
        return qntLugaresDisponiveis;
    }

    /**
     * Retorna a distância até o aeroporto de destino, em quilômetros.
     *
     * @return A distância em quilômetros.
     */
    public double getDistanciaKmAeroporto() {
        return distanciaKmAeroporto;
    }

    /**
     * Retorna o preço do bilhete para o voo.
     *
     * @return O preço do bilhete.
     */
    public double getPrecoBilhete() {
        return precoBilhete;
    }

    /**
     * Retorna a data de partida do voo.
     *
     * @return A data de partida.
     */
    public Data getDataPartida() {
        return dataPartida;
    }

    /**
     * Retorna a hora de partida do voo.
     *
     * @return A hora de partida.
     */
    public LocalTime getHoraPartida() {
        return horaPartida;
    }
}
