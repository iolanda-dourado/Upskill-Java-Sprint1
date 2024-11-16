package enums;

import utilidades.Data;

/**
 * Enum {@code GerarAutomaticoResHotelVoo} representa reservas de hotel e voo, contendo informações sobre a data de reserva,
 * a quantidade de pessoas, a data de chegada e o número de noites de estadia.
 */
public enum GerarAutomaticoResHotelVoo {

    /** Reserva 1: Data de reserva 02/01/2024, 3 pessoas, data de chegada 18/10/2024, 5 noites de estadia. */
    RES_HOTEL_VOO_1(new Data(2024, 1, 2), 3, new Data(2024, 10, 18), 5),

    /** Reserva 2: Data de reserva 15/02/2024, 4 pessoas, data de chegada 20/11/2024, 7 noites de estadia. */
    RES_HOTEL_VOO_2(new Data(2024, 2, 15), 4, new Data(2024, 11, 20), 7),

    /** Reserva 3: Data de reserva 05/03/2024, 2 pessoas, data de chegada 10/12/2024, 3 noites de estadia. */
    RES_HOTEL_VOO_3(new Data(2024, 3, 5), 2, new Data(2024, 12, 10), 3),

    /** Reserva 4: Data de reserva 20/04/2024, 6 pessoas, data de chegada 10/01/2024, 8 noites de estadia. */
    RES_HOTEL_VOO_4(new Data(2024, 4, 20), 6, new Data(2024, 1, 10), 8),

    /** Reserva 5: Data de reserva 10/05/2024, 5 pessoas, data de chegada 25/02/2024, 6 noites de estadia. */
    RES_HOTEL_VOO_5(new Data(2024, 5, 10), 5, new Data(2024, 2, 25), 6),

    /** Reserva 6: Data de reserva 01/06/2024, 7 pessoas, data de chegada 15/03/2024, 4 noites de estadia. */
    RES_HOTEL_VOO_6(new Data(2024, 6, 1), 7, new Data(2024, 3, 15), 4),

    /** Reserva 7: Data de reserva 14/07/2024, 3 pessoas, data de chegada 05/04/2024, 5 noites de estadia. */
    RES_HOTEL_VOO_7(new Data(2024, 7, 14), 3, new Data(2024, 4, 5), 5),

    /** Reserva 8: Data de reserva 25/08/2024, 2 pessoas, data de chegada 30/05/2024, 7 noites de estadia. */
    RES_HOTEL_VOO_8(new Data(2024, 8, 25), 2, new Data(2024, 5, 30), 7),

    /** Reserva 9: Data de reserva 30/09/2024, 8 pessoas, data de chegada 18/06/2024, 2 noites de estadia. */
    RES_HOTEL_VOO_9(new Data(2024, 9, 30), 8, new Data(2024, 6, 18), 2),

    /** Reserva 10: Data de reserva 18/10/2024, 4 pessoas, data de chegada 22/07/2024, 3 noites de estadia. */
    RES_HOTEL_VOO_10(new Data(2024, 10, 18), 4, new Data(2024, 7, 22), 3);

    /** A data da reserva. */
    private final Data dataReserva;

    /** A quantidade de pessoas para a reserva. */
    private final int qntPessoas;

    /** A data de chegada ao local de hospedagem. */
    private final Data dataChegada;

    /** O número de noites de estadia. */
    private final int numNoitesEstadia;

    /**
     * Constrói uma nova instância de {@code GerarAutomaticoResHotelVoo} com as informações da reserva de hotel e voo.
     *
     * @param dataReserva A data da reserva.
     * @param qntPessoas A quantidade de pessoas na reserva.
     * @param dataChegada A data de chegada ao hotel.
     * @param numNoitesEstadia O número de noites de estadia.
     */
    GerarAutomaticoResHotelVoo(Data dataReserva, int qntPessoas, Data dataChegada, int numNoitesEstadia) {
        this.dataReserva = dataReserva;
        this.qntPessoas = qntPessoas;
        this.dataChegada = dataChegada;
        this.numNoitesEstadia = numNoitesEstadia;
    }

    /**
     * Retorna a data da reserva.
     *
     * @return A data da reserva.
     */
    public Data getDataReserva() {
        return dataReserva;
    }

    /**
     * Retorna a quantidade de pessoas para a reserva.
     *
     * @return A quantidade de pessoas.
     */
    public int getQntPessoas() {
        return qntPessoas;
    }

    /**
     * Retorna a data de chegada ao hotel.
     *
     * @return A data de chegada.
     */
    public Data getDataChegada() {
        return dataChegada;
    }

    /**
     * Retorna o número de noites de estadia.
     *
     * @return O número de noites de estadia.
     */
    public int getNumNoitesEstadia() {
        return numNoitesEstadia;
    }
}
