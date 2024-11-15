package entidades;

import utilidades.Data;

import java.util.Objects;

public class ReservaVoo extends Reserva {
    private Voo voo;

    private static final String PREFIXO_RESERVA_VOO = "R_VOO-";

    private static int reservaVooCount = 0;

    public ReservaVoo(Data dataReserva, int qntPessoas, Cliente cliente, Voo voo) {
        super(dataReserva, qntPessoas, cliente);
        ++reservaVooCount;
        this.setCodigoReserva(gerarIdentificador());
        this.voo = voo;
        this.voo.setQntLugaresDisponiveis(atualizarNumLugaresDisponiveis());
    }

    public ReservaVoo() {
        super();
        ++reservaVooCount;
        this.setCodigoReserva(gerarIdentificador());
        this.voo = new Voo();
    }

    public ReservaVoo(ReservaVoo outra) {
        super(outra);
        this.voo = new Voo(outra.voo);
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    @Override
    public String toString() {
        return String.format("--- Reserva Voo ---\n%s\n%s", super.toString(), voo);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReservaVoo that = (ReservaVoo) o;
        return Objects.equals(voo, that.voo);
    }

    @Override
    public String gerarIdentificador() {
        return String.format("%s%s", PREFIXO_RESERVA_VOO, reservaVooCount);
    }


    public int atualizarNumLugaresDisponiveis() {
        if (this.getQntPessoas() > voo.getQntLugaresDisponiveis()) {
            throw new ArithmeticException("O avião não tem lugares disponíveis suficientes.");
        }

        return this.voo.getQntLugaresDisponiveis() - this.getQntPessoas();
    }

    @Override
    public double calcularCustoReserva() {
        double novoPreco = voo.calcularCustoBilheteIda();
        double custoReserva = (getQntPessoas()*novoPreco) + getTaxaReserva();

        if (saoReservasMultiplasDe5()) {
            double desconto = custoReserva * (getCliente().getPercentagemDesconto()/100);
            return custoReserva - desconto;
        } else{
            return custoReserva;
        }
    }

    public int getReservaVooCount() {
        return reservaVooCount;
    }
}