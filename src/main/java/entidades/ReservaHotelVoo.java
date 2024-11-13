package entidades;

public class ReservaHotelVoo extends Reserva {
    private ReservaVoo reservaVoo;

    private static final String PREFIXO_RESERVA_HOTEL_VOO = "R_HTL_VOO-";
    @Override
    public String gerarIdentificador() {
        return "";
    }

    @Override
    public double calcularCustoReserva() {
        return 0;
    }
}
