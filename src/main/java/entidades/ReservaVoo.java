package entidades;

import utilidades.Data;

import java.util.Objects;

public class ReservaVoo extends Reserva {
    private Voo voo;

    private static final String PREFIXO_RESERVA_VOO = "R_VOO-";

    private int reservaVooCount = 0;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReservaVoo that = (ReservaVoo) o;
        return reservaVooCount == that.reservaVooCount && Objects.equals(voo, that.voo);
    }

    public int getReservaVooCount() {
        return reservaVooCount;
    }

    @Override
    public String gerarIdentificador() {
        return String.format("%s%s", PREFIXO_RESERVA_VOO, reservaVooCount);
    }


    public int atualizarNumLugaresDisponiveis() {
        if (voo.getQntLugaresDisponiveis() == 0) {
            throw new ArithmeticException("Não existem mais lugares disponíveis.");
        }
        if (this.getQntPessoas() > voo.getQntLugaresDisponiveis()) {
            throw new ArithmeticException("O avião não tem lugares disponíveis suficientes.");
        }

        return this.voo.getQntLugaresDisponiveis() - this.getQntPessoas();
    }

    @Override
    public double calcularCustoReserva() {
        double novoPreco = voo.calcularCustoBilheteIda();
        double custoReserva = getQntPessoas()*novoPreco + getTaxaReserva();
        if (saoReservasMultiplasDe5()) {
            double desconto = custoReserva * (getCliente().getPercentagemDesconto()/100);
            return custoReserva - desconto;
        } else{
            return custoReserva;
        }
    }


}
