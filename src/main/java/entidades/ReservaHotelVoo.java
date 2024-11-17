package entidades;

import utilidades.Data;

import java.util.Objects;

/**
 * Representa uma reserva combinada de hotel e voo.
 * @author Iolanda Dourado e Marianna Ramos
 *
 */
public class ReservaHotelVoo extends ReservaHotel {
    /**
     * Voo associado à reserva.
     */
    private Voo voo;

    /**
     * Prefixo usado para identificar reservas combinadas de hotel e voo.
     */
    private static final String PREFIXO_RESERVA_HOTEL_VOO = "R_HTL_VOO-";

    /**
     * Contador estático para identificar o número total de reservas combinadas de hotel e voo.
     */
    private static int reservaHotelVooCount = 1;

    /**
     * Constrói uma nova reserva de hotel com voo de ida.
     * Esta reserva inclui os dados do cliente, do hotel, a data da reserva,
     * a quantidade de pessoas, a data de chegada e o número de noites de estadia.
     * Além disso, associa um voo de ida à reserva.
     *
     * @param dataReserva A data da reserva, representando o momento em que a reserva foi feita.
     * @param qntPessoas A quantidade de pessoas que irão se hospedar e utilizar o voo.
     * @param cliente O cliente responsável pela reserva, representado por um objeto {@link Cliente}.
     * @param hotel O hotel onde a estadia será realizada, representado por um objeto {@link Hotel}.
     * @param dataChegada A data de chegada ao hotel, representada por um objeto {@link Data}.
     * @param numNoitesEstadia O número de noites que o cliente ficará hospedado no hotel.
     * @param voo O voo de ida associado à reserva, representado por um objeto {@link Voo}.
     */
    public ReservaHotelVoo(Data dataReserva, int qntPessoas, Cliente cliente, Hotel hotel, Data dataChegada, int numNoitesEstadia, Voo voo) {
        super(dataReserva, qntPessoas, cliente, hotel, dataChegada, numNoitesEstadia);
        ++reservaHotelVooCount;
        this.voo = voo;
    }

    /**
     * Construtor padrão que inicializa uma reserva de hotel e voo com valores padrão.
     */
    public ReservaHotelVoo() {
        super();
        ++reservaHotelVooCount;
        this.voo = new Voo();
    }

    /**
     * Construtor de cópia para criar uma nova reserva baseada em outra existente.
     */
    public ReservaHotelVoo(ReservaHotelVoo outro) {
        super(outro);
        this.voo = new Voo(outro.voo);
    }

    /**
     * Obtém o voo associado à reserva.
     *
     * @return o voo da reserva.
     */
    public Voo getVoo() {
        return voo;
    }

    /**
     * Define o voo associado à reserva.
     *
     * @param voo o voo a ser associado à reserva.
     */
    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    /**
     * Retorna uma representação textual da reserva de hotel e voo.
     *
     * @return uma string representando a reserva.
     */
    @Override
    public String toString() {
        return String.format(
                """
                Reserva Hotel e Voo
                %s
                %s
                """,
                super.toString(), voo
        );
    }

    /**
     * Verifica a igualdade entre esta reserva e outro objeto.
     *
     * @param outraResHotelVoo o objeto a ser comparado.
     * @return true se forem iguais, caso contrário false.
     */
    @Override
    public boolean equals(Object outraResHotelVoo) {
        if (this == outraResHotelVoo) return true;
        if (outraResHotelVoo == null || getClass() != outraResHotelVoo.getClass()) return false;
        if (!super.equals(outraResHotelVoo)) return false;
        ReservaHotelVoo that = (ReservaHotelVoo) outraResHotelVoo;
        return Objects.equals(voo, that.voo);
    }

    /**
     * Gera um identificador único para a reserva combinada de hotel e voo.
     *
     * @return o identificador único gerado.
     */
    @Override
    public String gerarIdentificador() {
        return String.format("%s%s", PREFIXO_RESERVA_HOTEL_VOO, getReservaCount());
    }

    /**
     * Calcula o custo total da reserva de hotel com voo.
     *
     * Este método calcula o custo total da reserva, levando em consideração o preço do quarto de hotel,
     * a quantidade de pessoas, o número de noites de estadia, o voo e possíveis promoções. O valor é
     * calculado em duas partes: o custo da estadia no hotel e o custo do voo. Além disso, descontos
     * podem ser aplicados com base em promoções e em reservas múltiplas.
     *
     * O cálculo do custo total envolve:
     * - O preço do quarto de hotel, com ou sem desconto.
     * - O custo do voo, multiplicado pelo número de pessoas.
     * - O custo total da reserva é ajustado se o cliente tiver um desconto especial.
     *
     * @return O custo total da reserva de hotel com voo, considerando todos os custos e descontos aplicáveis.
     */
    @Override
    public double calcularCustoReserva() {
        int quartos = verificaQuantidadeQuartos();
        double precoQuarto = getHotel().getPrecoPorQuarto();
        double precoPromocao = precoQuarto-(precoQuarto*getDescontoDiaria());
        int diariasPromocao = verificaDiariasPromocao();
        double reservaHotel = 0;
        double valorFinal = 0;

        double novoPreco = voo.calcularCustoBilheteIda();
        double custoReservaVoo = getQntPessoas()*novoPreco;

        if(getNumNoitesEstadia() == diariasPromocao) {
            reservaHotel = quartos * precoPromocao *getNumNoitesEstadia();

        } else {
            int diariasRestantes = getNumNoitesEstadia() - diariasPromocao;
            reservaHotel = (quartos * precoPromocao * diariasPromocao)+(quartos * precoQuarto * diariasRestantes);
        }

        if (saoReservasMultiplasDe5()) {
            double reservaTotal = reservaHotel + custoReservaVoo+getTaxaReserva();
            double desconto = reservaTotal * (getCliente().getPercentagemDesconto()/100);
            valorFinal = reservaTotal - desconto;
            return valorFinal;
        } else{
            valorFinal = reservaHotel + custoReservaVoo+getTaxaReserva();
            return valorFinal;
        }

    }

    /**
     * Obtém o número total de reservas combinadas de hotel e voo criadas.
     *
     * @return o número total de reservas combinadas.
     */
    public static int getReservaHotelVooCount() {
        return reservaHotelVooCount;
    }
}
