package entidades;

public class ReservaHotel extends Reserva {
    private Hotel hotel;
    private Data dataChegada;
    private int numNoitesEstadia;
    private int countResHotel;

    private static final int NUM_NOITES_ESTADIA_OMISSAO = -1;
    private static final String PREFIXO_RESERVA_HOTEL = "R_HTL-";
    private static int reservaHotelCount = 0;
    private static int capacidadeMax = 2;


    public ReservaHotel(Hotel hotel, Data dataChegada, int numNoitesEstadia) {
        ++countResHotel;
        this.setIdentificador(gerarIdentificador());
        this.hotel = hotel;
        this.dataChegada = new Data(dataChegada);
        this.numNoitesEstadia = numNoitesEstadia;
        ++reservaHotelCount;
    }

    public ReservaHotel() {
        this.setIdentificador(gerarIdentificador());
        this.hotel = new Hotel();
        this.dataChegada = new Data();
        this.numNoitesEstadia = NUM_NOITES_ESTADIA_OMISSAO;
        ++reservaHotelCount;
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
    public String gerarIdentificador() {
        return String.format("%s%s", PREFIXO_RESERVA_HOTEL, this.identificador());
    }

    @Override
    public double calcularCustoReserva() {
        return 0;
    }
}
