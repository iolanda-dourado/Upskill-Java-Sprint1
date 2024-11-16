package entidades;

import utilidades.Data;

import java.util.Objects;
/**
 * Classe que representa uma reserva de hotel com voo de ida e volta.
 *
 * Esta classe é uma especialização da classe {@link ReservaHotelVoo} e adiciona a funcionalidade
 * de um voo de regresso (volta). Ela permite calcular o custo total da reserva, levando em consideração
 * o custo do hotel e o voo de ida e volta, além de fornecer um identificador exclusivo para cada reserva.
 */
public class ReservaHotelVooIdaVolta extends ReservaHotelVoo {
    /**
     * Prefixo utilizado para identificar as reservas de hotel com voo de ida e volta.
     */
    private Voo vooRegresso;

    /**
     * Contador estático utilizado para gerar um identificador único para cada reserva de hotel com voo de ida e volta.
     */
    private static final String PREFIXO_RESERVA_HOTEL_VOO_IV = "R_HTL_VOO_IV-";

    /**
     * Contador estático utilizado para gerar um identificador único para cada reserva de hotel com voo de ida e volta.
     */
    private static int reservaHotelVooIdaVoltaCount = 0;


    /**
     * Constrói uma nova instância de reserva de hotel com voo de ida e volta.
     *
     * Este construtor inicializa a reserva de hotel com voo de ida e volta utilizando as informações
     * fornecidas, incluindo a data da reserva, a quantidade de pessoas, o cliente, o hotel, a data de chegada,
     * o número de noites de estadia, o voo de ida e o voo de regresso.
     *
     * O contador de reservas de hotel com voo de ida e volta é incrementado para garantir que cada reserva
     * tenha um identificador exclusivo.
     *
     * @param dataReserva A data em que a reserva foi feita.
     * @param qntPessoas A quantidade de pessoas para a reserva.
     * @param cliente O cliente que está fazendo a reserva.
     * @param hotel O hotel no qual a reserva será feita.
     * @param dataChegada A data de chegada do cliente ao hotel.
     * @param numNoitesEstadia O número de noites que o cliente ficará hospedado.
     * @param voo O voo de ida associado à reserva de hotel.
     * @param vooRegresso O voo de regresso associado à reserva de hotel.
     */
    public ReservaHotelVooIdaVolta(Data dataReserva, int qntPessoas, Cliente cliente, Hotel hotel, Data dataChegada, int numNoitesEstadia, Voo voo, Voo vooRegresso) {
        super(dataReserva, qntPessoas, cliente, hotel, dataChegada, numNoitesEstadia, voo);
        ++reservaHotelVooIdaVoltaCount;
        this.vooRegresso = vooRegresso;
    }

    /**
     * Constrói uma instância de reserva de hotel com voo de ida e volta com valores padrão.
     */
    public ReservaHotelVooIdaVolta() {
        super();
        ++reservaHotelVooIdaVoltaCount;
        this.vooRegresso = new Voo();
    }

    /**
     * Constrói uma instância de reserva de hotel com voo de ida e volta a partir de outra reserva existente.
     *
     * Este construtor cria uma nova instância copiando os dados da reserva fornecida, incluindo o voo de regresso.
     *
     * @param outra A outra reserva de hotel com voo de ida e volta a ser copiada.
     */
    public ReservaHotelVooIdaVolta(ReservaHotelVooIdaVolta outra) {
        super(outra);
        this.vooRegresso = new Voo(outra.vooRegresso);
    }

    /**
     * Obtém o voo de regresso associado à reserva.
     *
     * @return O voo de regresso associado à reserva de hotel.
     */
    public Voo getVooRegresso() {
        return vooRegresso;
    }

    /**
     * Define o voo de regresso para a reserva de hotel.
     *
     * @param vooRegresso O voo de regresso a ser associado à reserva de hotel.
     */
    public void setVooRegresso(Voo vooRegresso) {
        this.vooRegresso = vooRegresso;
    }

    /**
     * Retorna uma representação em formato de string da reserva de hotel com voo de ida e volta.
     *
     * @return Uma string representando a reserva de hotel com voo de ida e volta.
     */
    @Override
    public String toString() {
        return String.format("-- Reserva de Hotel com Voo de Ida e Volta --\n%s\nVoo de Regresso: %s", super.toString(), vooRegresso);
    }

    /**
     * Compara se dois objetos de reserva de hotel com voo de ida e volta são iguais.
     *
     * Este método compara os atributos da reserva de hotel com voo de ida e volta, incluindo o voo de regresso.
     *
     * @param o O objeto a ser comparado com a instância atual.
     * @return `true` se os objetos forem iguais, `false` caso contrário.
     */
    @Override
    public boolean equals(Object outraResHotelVooIV) {
        if (this == outraResHotelVooIV) return true;
        if (outraResHotelVooIV == null || getClass() != outraResHotelVooIV.getClass()) return false;
        if (!super.equals(outraResHotelVooIV)) return false;
        ReservaHotelVooIdaVolta that = (ReservaHotelVooIdaVolta) outraResHotelVooIV;
        return Objects.equals(vooRegresso, that.vooRegresso);
    }

    /**
     * Calcula o custo total da reserva de hotel com voo de ida e volta.
     *
     * O custo total é calculado levando em consideração:
     * - O preço do quarto de hotel, incluindo descontos de promoções.
     * - O custo do voo de ida e do voo de regresso.
     * - O número de pessoas que farão a reserva.
     * - Possíveis descontos para reservas múltiplas de 5 ou mais pessoas.
     *
     * @return O custo total da reserva, considerando o hotel e os voos.
     */
    @Override
    public double calcularCustoReserva() {
        int quartos = verificaQuantidadeQuartos();
        double precoQuarto = getHotel().getPrecoPorQuarto();
        double precoPromocao = precoQuarto-(precoQuarto*getDescontoDiaria());
        int diariasPromocao = verificaDiariasPromocao();
        double reservaHotel = 0;

        double precoIda = getVoo().calcularCustoBilheteIda();
        double precoVolta = vooRegresso.calcularCustoBilheteVolta();
        double custoReservaVoo = getQntPessoas()*(precoIda + precoVolta);

        if(getNumNoitesEstadia() == diariasPromocao) {
            reservaHotel = quartos * precoPromocao *getNumNoitesEstadia();

        } else {
            int diariasRestantes = getNumNoitesEstadia() - diariasPromocao;
            reservaHotel = (quartos * precoPromocao * diariasPromocao)+(quartos * precoQuarto * diariasRestantes);
        }

        if (saoReservasMultiplasDe5()) {
            double reservaTotal = reservaHotel + custoReservaVoo+getTaxaReserva();
            double desconto = reservaTotal * (getCliente().getPercentagemDesconto()/100);
            return reservaTotal - desconto;
        } else{
            return reservaHotel + custoReservaVoo+getTaxaReserva();
        }
    }

    /**
     * Gera um identificador exclusivo para a reserva de hotel com voo de ida e volta.
     *
     * O identificador gerado é baseado no prefixo {@link #PREFIXO_RESERVA_HOTEL_VOO_IV} seguido de um número sequencial.
     *
     * @return O identificador exclusivo da reserva de hotel com voo de ida e volta.
     */
    @Override
    public String gerarIdentificador() {
        return String.format("%s%s", PREFIXO_RESERVA_HOTEL_VOO_IV, reservaHotelVooIdaVoltaCount);
    }

    /**
     * Obtém o número total de reservas de hotel com voo de ida e volta realizadas.
     *
     * @return O contador de reservas de hotel com voo de ida e volta.
     */
    public static int getReservaHotelVooIdaVoltaCount() {
        return reservaHotelVooIdaVoltaCount;
    }
}