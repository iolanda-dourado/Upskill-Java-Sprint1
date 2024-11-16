package entidades;

import utilidades.Data;

import java.util.Objects;

/**
 * Representa uma reserva de voo de ida e volta.
 * Esta classe adiciona um voo de regresso (volta) à reserva,
 * permitindo calcular o custo total considerando ambos os voos.
 *
 * @author Iolanda Dourado e Marianna Ramos
 * @version 1.0
 *
 */
public class ReservaVooIdaVolta extends ReservaVoo {
    /**
     * Voo de regresso (volta) associado à reserva de voo ida e volta.
     *
     */
    private Voo vooRegresso;

    /**
     * Prefixo utilizado para identificar as reservas de voo ida e volta.
     * Este prefixo é utilizado na geração do identificador único para cada reserva
     * de voo ida e volta. O identificador é formado pelo prefixo seguido de um número
     * sequencial que é incrementado a cada nova reserva.
     */
    private static final String PREFIXO_RESERVA_VOO_IDA_VOLTA = "R_VOO_IV-";

    /**
     * Contador estático que armazena o número de reservas de voo ida e volta realizadas.
     * Esse contador é incrementado cada vez que uma nova reserva de voo ida e volta é criada.
     * O valor desse contador é utilizado para gerar identificadores únicos para cada reserva.
     */
    private static int reservaVooIdaVoltaCount = 0;

    /**
     * Constrói uma nova reserva de voo ida e volta.
     *
     * @param dataReserva A data da reserva, representando o momento em que a reserva foi feita.
     * @param qntPessoas A quantidade de pessoas que irão viajar.
     * @param cliente O cliente que fez a reserva, representado por um objeto {@link Cliente}.
     * @param voo O voo de ida associado à reserva, representado por um objeto {@link Voo}.
     * @param vooRegresso O voo de regresso associado à reserva, representado por um objeto {@link Voo}.
     */
    public ReservaVooIdaVolta(Data dataReserva, int qntPessoas, Cliente cliente, Voo voo, Voo vooRegresso) {
        super(dataReserva, qntPessoas, cliente, voo);
        ++reservaVooIdaVoltaCount;
        this.vooRegresso = vooRegresso;
    }

    /**
     * Constrói uma nova reserva de voo ida e volta com valores padrão.
     * A reserva é criada sem um voo específico, utilizando um objeto {@link Voo} vazio.
     */
    public ReservaVooIdaVolta() {
        super();
        ++reservaVooIdaVoltaCount;
        this.vooRegresso = new Voo();
    }

    /**
     * Constrói uma nova reserva de voo ida e volta a partir de outra reserva.
     *
     * @param outra A outra reserva de voo ida e volta que será copiada.
     */
    public ReservaVooIdaVolta(ReservaVooIdaVolta outra) {
        super(outra);
        this.vooRegresso = new Voo (outra.vooRegresso);
    }

    /**
     * Obtém o voo de regresso (volta) da reserva.
     *
     * @return O voo de regresso, representado por um objeto {@link Voo}.
     */
    public Voo getVooRegresso() {
        return vooRegresso;
    }

    /**
     * Define o voo de regresso (volta) para a reserva.
     *
     * @param vooRegresso O voo de regresso a ser atribuído à reserva, representado por um objeto {@link Voo}.
     */
    public void setVooRegresso(Voo vooRegresso) {
        this.vooRegresso = vooRegresso;
    }

    /**
     * Representa a reserva de voo ida e volta como uma string.
     *
     * @return Uma string formatada com as informações da reserva, incluindo o voo de regresso.
     */
    @Override
    public String toString() {
        return String.format("--- Reserva Voo Ida e Volta ---\n%s\nVoo de Regresso: %s", super.toString(), vooRegresso);
    }

    /**
     * Compara duas reservas de voo ida e volta para verificar se são iguais.
     *
     * @param o O objeto com o qual comparar a reserva.
     * @return Verdadeiro se as reservas forem iguais, falso caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReservaVooIdaVolta that = (ReservaVooIdaVolta) o;
        return Objects.equals(vooRegresso, that.vooRegresso);
    }

    /**
     * Gera um identificador único para a reserva de voo ida e volta.
     *
     * @return Um identificador único no formato "R_VOO_IV-{número}".
     */
    @Override
    public String gerarIdentificador() {
        return String.format("%s%s", PREFIXO_RESERVA_VOO_IDA_VOLTA, reservaVooIdaVoltaCount);
    }

    /**
     * Calcula o custo total da reserva de voo ida e volta.
     * Considera os custos do voo de ida e de regresso, o número de pessoas e qualquer possível desconto.
     *
     * @return O custo total da reserva, incluindo os custos dos voos e a taxa de reserva.
     */
    @Override
    public double calcularCustoReserva() {
        double custoBilheteIda = getVoo().calcularCustoBilheteIda();
        double custoBilheteVolta = vooRegresso.calcularCustoBilheteVolta();
        double custoReservaIda = getQntPessoas() * custoBilheteIda;
        double custoReservaVolta = getQntPessoas() * custoBilheteVolta;
        double percentagemDesconto = (getCliente().getPercentagemDesconto() / 100);
        double custoReservaTotal = custoReservaIda + custoReservaVolta + getTaxaReserva();

        if (saoReservasMultiplasDe5()) {
            return custoReservaTotal - (custoReservaTotal * percentagemDesconto);
        }
        return custoReservaTotal;
    }

    /**
     * Obtém o contador de reservas de voo ida e volta.
     *
     * @return O número total de reservas de voo ida e volta realizadas.
     */
    public int getReservaVooIdaVoltaCount() {
        return reservaVooIdaVoltaCount;
    }
}