package entidades;

public class ReservaVoo extends Reserva {
    private Voo voo;
    private static final String PREFIXO_RESERVA_VOO = "R_VOO-";
    private int contadorResVoo = 0;

    public ReservaVoo (Data dataReserva, int qntPessoas, Cliente cliente, Voo voo) {
        super(dataReserva, qntPessoas, cliente);
        this.voo = voo;
    }

    public ReservaVoo() {
        super();
        this.voo = new Voo();
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    @Override
    public String gerarIdentificador() {
        return "";
    }


    @Override
    public double calcularCustoReserva() {
        return 0;
    }
}
