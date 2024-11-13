package entidades;

import utilidades.Data;

import java.util.Objects;

public class ReservaHotel extends Reserva {
    private Hotel hotel;
    private Data dataChegada;
    private int numNoitesEstadia;


    private static final int NUM_NOITES_ESTADIA_OMISSAO = -1;
    private static final String PREFIXO_RESERVA_HOTEL = "R_HTL-";
    private static int reservaHotelCount = 0;
    private static int capacidadeMax = 2;
    private static double descontoDiaria = 30;


    public ReservaHotel(Data dataReserva, int qntPessoas, Cliente cliente, Hotel hotel, Data dataChegada, int numNoitesEstadia) {
        super(dataReserva, qntPessoas, cliente);
        ++reservaHotelCount;
        this.setCodigoReserva(gerarIdentificador());
        this.hotel = hotel;
        this.dataChegada = dataChegada;
        this.numNoitesEstadia = numNoitesEstadia;
    }

    public ReservaHotel() {
        super();
        ++reservaHotelCount;
        this.setCodigoReserva(gerarIdentificador());
        this.hotel = new Hotel();
        this.dataChegada = new Data();
        this.numNoitesEstadia = NUM_NOITES_ESTADIA_OMISSAO;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Data getDataChegada() {
        return dataChegada;
    }

    public int getNumNoitesEstadia() {
        return numNoitesEstadia;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setDataChegada(Data dataChegada) {
        this.dataChegada = dataChegada;
    }

    public void setNumNoitesEstadia(int numNoitesEstadia) {
        this.numNoitesEstadia = numNoitesEstadia;
    }

    @Override
    public String toString() {
        return String.format(
                """     
                        %s--- Reserva de Hotel ---
                        Identificador da Reserva: %s
                        %s
                        Data de Chegada: %s
                        Número de Noites de Estadia: %d
                        Capacidade Máxima: %d""",
                super.toString(), getCodigoReserva(), hotel, dataChegada, numNoitesEstadia, capacidadeMax
        );
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReservaHotel that = (ReservaHotel) o;
        return numNoitesEstadia == that.numNoitesEstadia && Objects.equals(hotel, that.hotel) && Objects.equals(dataChegada, that.dataChegada);
    }

    public static int getReservaHotelCount() {
        return reservaHotelCount;
    }

    public static int getCapacidadeMax() {
        return capacidadeMax;
    }

    public static double getDescontoDiaria() {
        return descontoDiaria;
    }

    public static void setCapacidadeMax(int capacidadeMax) {
        ReservaHotel.capacidadeMax = capacidadeMax;
    }

    public static void setDescontoDiaria(double descontoDiaria) {
        ReservaHotel.descontoDiaria = descontoDiaria;
    }

    @Override
    public String gerarIdentificador() {
        return String.format("%s%s", PREFIXO_RESERVA_HOTEL, reservaHotelCount);
    }

    @Override
    public double calcularCustoReserva() {
        double precoPorNoite = getHotel().getPrecoPorQuarto();
        return 0;
    }
}
