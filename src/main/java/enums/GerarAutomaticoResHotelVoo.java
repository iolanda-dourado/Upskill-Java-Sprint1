package enums;

import utilidades.Data;

public enum GerarAutomaticoResHotelVoo {

    RES_HOTEL_VOO_1(new Data(2024, 1, 2), 3, new Data(2024, 10, 18), 5),
    RES_HOTEL_VOO_2(new Data(2024, 2, 15), 4, new Data(2024, 11, 20), 7),
    RES_HOTEL_VOO_3(new Data(2024, 3, 5), 2, new Data(2024, 12, 10), 3),
    RES_HOTEL_VOO_4(new Data(2024, 4, 20), 6, new Data(2024, 1, 10), 8),
    RES_HOTEL_VOO_5(new Data(2024, 5, 10), 5, new Data(2024, 2, 25), 6),
    RES_HOTEL_VOO_6(new Data(2024, 6, 1), 7, new Data(2024, 3, 15), 4),
    RES_HOTEL_VOO_7(new Data(2024, 7, 14), 3, new Data(2024, 4, 5), 5),
    RES_HOTEL_VOO_8(new Data(2024, 8, 25), 2, new Data(2024, 5, 30), 7),
    RES_HOTEL_VOO_9(new Data(2024, 9, 30), 8, new Data(2024, 6, 18), 2),
    RES_HOTEL_VOO_10(new Data(2024, 10, 18), 4, new Data(2024, 7, 22), 3);

    private final Data dataReserva;
    private final int qntPessoas;
    private final Data dataChegada;
    private final int numNoitesEstadia;

    GerarAutomaticoResHotelVoo(Data dataReserva, int qntPessoas, Data dataChegada, int numNoitesEstadia) {
        this.dataReserva = dataReserva;
        this.qntPessoas = qntPessoas;
        this.dataChegada = dataChegada;
        this.numNoitesEstadia = numNoitesEstadia;
    }

    public Data getDataReserva() {
        return dataReserva;
    }

    public int getQntPessoas() {
        return qntPessoas;
    }

    public Data getDataChegada() {
        return dataChegada;
    }

    public int getNumNoitesEstadia() {
        return numNoitesEstadia;
    }
}
