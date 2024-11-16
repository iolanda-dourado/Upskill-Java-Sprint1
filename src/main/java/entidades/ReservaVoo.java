package entidades;

import utilidades.Data;

import java.util.Objects;

/**
 * Representa uma reserva de voo, incluindo informações sobre o voo e o cliente.
 * Esta classe herda de {@link Reserva}.
 */
public class ReservaVoo extends Reserva {
    private Voo voo;

    private static final String PREFIXO_RESERVA_VOO = "R_VOO-";

    private int reservaVooCount = 0;

    /**
     * Constrói uma nova reserva de voo com os dados fornecidos.
     *
     * @param dataReserva A data da reserva.
     * @param qntPessoas A quantidade de pessoas na reserva.
     * @param cliente O cliente responsável pela reserva.
     * @param voo O voo reservado.
     */
    public ReservaVoo(Data dataReserva, int qntPessoas, Cliente cliente, Voo voo) {
        super(dataReserva, qntPessoas, cliente);
        ++reservaVooCount;
        this.setCodigoReserva(gerarIdentificador());
        this.voo = voo;
        this.voo.setQntLugaresDisponiveis(atualizarNumLugaresDisponiveis());
    }

    /**
     * Constrói uma nova reserva de voo com valores padrão.
     */
    public ReservaVoo() {
        super();
        ++reservaVooCount;
        this.setCodigoReserva(gerarIdentificador());
        this.voo = new Voo();
    }

    /**
     * Constrói uma nova reserva de voo a partir de outra reserva.
     *
     * @param outra A reserva de voo a ser copiada.
     */
    public ReservaVoo(ReservaVoo outra) {
        super(outra);
        this.voo = new Voo(outra.voo);
    }

    /**
     * Retorna o voo associado à reserva.
     *
     * @return O voo da reserva.
     */
    public Voo getVoo() {
        return voo;
    }

    /**
     * Define o voo da reserva.
     *
     * @param voo O voo a ser associado à reserva.
     */
    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    /**
     * Retorna uma representação textual da reserva de voo.
     *
     * @return Uma string que representa a reserva de voo.
     */
    @Override
    public String toString() {
        return String.format(
                """
                Reserva de Voo
                %sIdentificador da Reserva: %s
                %s
                """,
                super.toString(), getCodigoReserva(), voo
        );
    }

    /**
     * Compara se duas reservas de voo são iguais.
     *
     * @param outraReservaVoo O objeto a ser comparado.
     * @return True se as reservas forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object outraReservaVoo) {
        if (outraReservaVoo == null || getClass() != outraReservaVoo.getClass()) return false;
        if (!super.equals(outraReservaVoo)) return false;
        ReservaVoo that = (ReservaVoo) outraReservaVoo;
        return Objects.equals(voo, that.voo);
    }

    /**
     * Gera um identificador único para a reserva de voo.
     *
     * @return O identificador gerado para a reserva de voo.
     */
    @Override
    public String gerarIdentificador() {
        return String.format("%s%s", PREFIXO_RESERVA_VOO, reservaVooCount);
    }


    /**
     * Atualiza a quantidade de lugares disponíveis no voo, verificando se há lugares suficientes.
     *
     * @return O número de lugares disponíveis após a reserva.
     * @throws ArithmeticException Se não houver lugares suficientes.
     */
    public int atualizarNumLugaresDisponiveis() {
        if (this.getQntPessoas() > voo.getQntLugaresDisponiveis()) {
            throw new ArithmeticException("O avião não tem lugares disponíveis suficientes.");
        }

        return this.voo.getQntLugaresDisponiveis() - this.getQntPessoas();
    }

    /**
     * Calcula o custo total da reserva de voo, incluindo a quantidade de pessoas e o preço do bilhete.
     *
     * @return O custo total da reserva de voo.
     */
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

    /**
     * Retorna a quantidade de reservas de voo realizadas até o momento.
     *
     * @return O número total de reservas de voo.
     */
    public int getReservaVooCount() {
        return reservaVooCount;
    }
}