package upskill;

public class ReservaHotel extends Reserva {
    private static final String PREFIXO_RESERVA_HOTEL = "R_HTL-";

    @Override
    public int gerarIdentificador() {
        return 0;
    }

    @Override
    public double calcularCustoReserva() {
        return 0;
    }
}
