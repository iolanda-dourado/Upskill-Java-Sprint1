package upskill;

public class ReservaVoo extends Reserva {
    private Voo voo;
    private static final String PREFIXO_RESERVA_VOO = "R_VOO-";

    public ReservaVoo (Data dataReserva, int qntPessoas, Cliente cliente) {
        super(dataReserva, qntPessoas, cliente);
    }

    public ReservaVoo() {
    }

    @Override
    public int gerarIdentificador() {
        return 0;
    }


    @Override
    public double calcularCustoReserva() {
        return 0;
    }
}
