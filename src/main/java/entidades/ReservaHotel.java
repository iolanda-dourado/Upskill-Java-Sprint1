package entidades;

import interfaces.Descontavel;
import utilidades.Data;

import java.util.Objects;

/**
 * Representa uma reserva de hotel, herdando as características básicas de uma reserva.
 * Inclui informações sobre o hotel, datas de chegada e número de noites de estadia.
 *
 * @author Iolanda Dourado e Marianna Ramos
 * @version 1.0
 */
public class ReservaHotel extends Reserva implements Descontavel {
    /**
     * Hotel associado à reserva.
     */
    private Hotel hotel;

    /**
     * Data de chegada para o início da estadia no hotel.
     */
    private Data dataChegada;

    /**
     * Número de noites de estadia na reserva.
     */
    private int numNoitesEstadia;

    /**
     * Valor padrão para número de noites de estadia.
     */
    private static final int NUM_NOITES_ESTADIA_OMISSAO = -1;

    /**
     * Prefixo utilizado para identificação de reservas de hotel.
     */
    private static final String PREFIXO_RESERVA_HOTEL = "R_HTL-";

    /**
     * Contador de reservas de hotel criadas.
     */
    private static int reservaHotelCount = 0;

    /**
     * Capacidade máxima padrão de hóspedes por quarto.
     */
    private static int capacidadeMax = 2;

    /**
     * Percentual de desconto aplicado a diárias promocionais.
     */
    private static double descontoDiaria = 0.3;

    /**
     * Construtor completo para criar uma reserva de hotel com todas as informações necessárias.
     *
     * @param dataReserva      Data da reserva.
     * @param qntPessoas       Quantidade de pessoas na reserva.
     * @param cliente          Cliente associado à reserva.
     * @param hotel            Hotel associado à reserva.
     * @param dataChegada      Data de chegada ao hotel.
     * @param numNoitesEstadia Número de noites de estadia no hotel.
     */
    public ReservaHotel(Data dataReserva, int qntPessoas, Cliente cliente, Hotel hotel, Data dataChegada, int numNoitesEstadia) {
        super(dataReserva, qntPessoas, cliente);
        ++reservaHotelCount;
        this.setCodigoReserva(gerarIdentificador());
        this.hotel = hotel;
        this.dataChegada = new Data(dataChegada);
        this.numNoitesEstadia = numNoitesEstadia;
    }

    /**
     * Construtor padrão que inicializa os valores com padrões predefinidos.
     */
    public ReservaHotel() {
        super();
        ++reservaHotelCount;
        this.setCodigoReserva(gerarIdentificador());
        this.hotel = new Hotel();
        this.dataChegada = new Data();
        this.numNoitesEstadia = NUM_NOITES_ESTADIA_OMISSAO;
    }

    /**
     * Construtor de cópia que cria uma nova reserva de hotel com base em outra existente.
     *
     * @param outra Reserva de hotel a ser copiada.
     */
    public ReservaHotel(ReservaHotel outra) {
        super(outra);
        this.hotel = new Hotel(outra.hotel);
        this.dataChegada = new Data(outra.dataChegada);
        this.numNoitesEstadia = outra.numNoitesEstadia;
    }

    /**
     * Retorna o hotel associado à reserva.
     *
     * @return O hotel da reserva.
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * Retorna a data de chegada ao hotel.
     *
     * @return Data de chegada.
     */
    public Data getDataChegada() {
        return new Data(dataChegada);
    }

    /**
     * Retorna o número de noites de estadia na reserva.
     *
     * @return Número de noites de estadia.
     */
    public int getNumNoitesEstadia() {
        return numNoitesEstadia;
    }

    /**
     * Define o hotel associado à reserva.
     *
     * @param hotel Novo hotel da reserva.
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Define a data de chegada ao hotel.
     *
     * @param dataChegada Nova data de chegada.
     */
    public void setDataChegada(Data dataChegada) {
        this.dataChegada.setData(dataChegada.getAno(), dataChegada.getMes(), dataChegada.getDia());
    }

    /**
     * Define o número de noites de estadia.
     *
     * @param numNoitesEstadia Novo número de noites de estadia.
     */
    public void setNumNoitesEstadia(int numNoitesEstadia) {
        this.numNoitesEstadia = numNoitesEstadia;
    }

    /**
     * Representa a reserva de hotel como uma string.
     *
     * @return Uma string com os detalhes da reserva de hotel.
     */
    @Override
    public String toString() {
        return String.format(
                """     
                        %s--- Reserva de Hotel ---
                        Identificador da Reserva: %s
                        %s
                        Data de Chegada: %s
                        Número de Noites de Estadia: %d
                        Capacidade Máxima: %d""",
                super.toString(), getCodigoReserva(), hotel, dataChegada, numNoitesEstadia, capacidadeMax
        );
    }

    /**
     * Verifica a igualdade entre esta reserva de hotel e outro objeto.
     *
     * @param outraReservaHotel Objeto a ser comparado.
     * @return {@code true} se os objetos forem iguais; {@code false} caso contrário.
     */
    @Override
    public boolean equals(Object outraReservaHotel) {
        if (this == outraReservaHotel) return true;
        if (outraReservaHotel == null || getClass() != outraReservaHotel.getClass()) return false;
        if (!super.equals(outraReservaHotel)) return false;
        ReservaHotel that = (ReservaHotel) outraReservaHotel;
        return numNoitesEstadia == that.numNoitesEstadia && Objects.equals(hotel, that.hotel) && Objects.equals(dataChegada, that.dataChegada);
    }

    /**
     * Verifica o número de diárias promocionais dentro do período de estadia.
     * O cálculo leva em consideração se a data de chegada está dentro de um ano bissexto,
     * e se as datas da estadia se sobrepõem com as datas de promoção.
     * A verificação é realizada para cada dia da estadia, e se a data estiver dentro
     * de um intervalo promocional, a diária é contada como promocional.
     *
     * A função percorre todos os dias do período de estadia e verifica se a data corresponde
     * a uma promoção, utilizando os dados de um ano bissexto se necessário. Para cada dia
     * promocional encontrado, o contador é incrementado.
     *
     * A data de chegada e o número de noites de estadia são usados para calcular a
     * quantidade de diárias promocionais.
     *
     * @return O número de diárias promocionais dentro do período de estadia.
     *         Se todas as diárias forem promocionais, retorna o número total de noites de estadia.
     *         Caso contrário, retorna o número de diárias promocionais encontradas.
     */
    public int verificaDiariasPromocao() {
        int anoTemp = dataChegada.getAno();
        int[] diasPorMes = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (Data.isAnoBissexto(anoTemp)) {
            diasPorMes[2] = 29;
        }

        int diaTemp = dataChegada.getDia();
        int mesTemp = dataChegada.getMes();

        int diasParametro = diasPorMes[mesTemp];
        boolean bool = false;
        int count = 0;
        int dataTemp = formatarData(dataChegada);
        int countTemp = 0;

        for (int i = 0; i < numNoitesEstadia; i++) {
            if (countTemp > 0) {
                dataTemp += 1;
                diaTemp += 1;
            }
            if (diasParametro < diaTemp) {
                if (mesTemp == 12) {
                    mesTemp = 0;
                }
                mesTemp += 1;
                String st = String.format("%d01", mesTemp);
                dataTemp = Integer.parseInt(st);
                diaTemp = 1;
                diasParametro = diasPorMes[mesTemp];
            }
            bool = isPromocao(dataTemp);
            if (bool) {
                count++;
            }
            countTemp++;
        }
        if (count == numNoitesEstadia) {
            return numNoitesEstadia;
        }
        return count;
    }

    /**
     * Calcula a quantidade de quartos necessária para acomodar as pessoas na reserva.
     *
     * @return o número de quartos necessários.
     */
    public int verificaQuantidadeQuartos() {
        int numQuartos = getQntPessoas() / capacidadeMax;
        if (getQntPessoas() % capacidadeMax == 1) {
            numQuartos = (getQntPessoas() / capacidadeMax) + 1;
        }

        return numQuartos;
    }

    /**
     * Gera um identificador único para a reserva de hotel.
     *
     * @return o identificador gerado no formato definido.
     */
    @Override
    public String gerarIdentificador() {
        return String.format("%s%s", PREFIXO_RESERVA_HOTEL, reservaHotelCount);
    }

    /**
     * Formata uma data no formato MMDD para ser usado em promoções.
     *
     * @param umaData a data a ser formatada.
     * @return a data formatada no formato MMDD.
     */
    @Override
    public int formatarData(Data umaData) {
        String st = String.format("%02d%02d", umaData.getMes(), umaData.getDia());
        return Integer.parseInt(st);
    }

    /**
     * Verifica se uma data formatada está em um período de promoção.
     *
     * @param dataFormatada a data formatada no formato MMDD.
     * @return true se a data estiver em promoção, caso contrário false.
     */
    @Override
    public boolean isPromocao(int dataFormatada) {

        if (dataFormatada >= Descontavel.INICIO_TEMP1 && dataFormatada <= Descontavel.FINAL_TEMP1) {
            return true;
        } else if (dataFormatada >= Descontavel.INICIO_TEMP2 && dataFormatada <= Descontavel.FINAL_TEMP2) {
            return true;
        } else if (dataFormatada >= Descontavel.INICIO_TEMP3 && dataFormatada <= Descontavel.FINAL_TEMP3) {
            return true;
        } else return false;
    }

    /**
     * Calcula o custo total da reserva de hotel, considerando fatores como:
     * - Preço promocional das diárias, quando aplicável.
     * - Número de quartos necessários com base na quantidade de pessoas.
     * - Taxa de reserva fixa.
     * - Desconto adicional para reservas múltiplas de 5.
     *
     * O cálculo é dividido em duas partes:
     * 1. Diárias promocionais: aplica um desconto fixo configurado em {@code descontoDiaria}.
     * 2. Diárias regulares: utiliza o preço padrão do quarto.
     *
     * Caso todas as noites sejam promocionais, todo o custo é calculado com base no preço promocional.
     * Se houver noites fora da promoção, o custo é dividido entre diárias promocionais e regulares.
     *
     * Finalmente, se a reserva for múltipla de 5 (verificada pelo método {@link #saoReservasMultiplasDe5()}),
     * um desconto adicional é aplicado com base no percentual de desconto do cliente.
     *
     * @return O custo total da reserva, considerando todas as condições mencionadas.
     */
    @Override
    public double calcularCustoReserva() {
        double precoQuarto = hotel.getPrecoPorQuarto();
        double precoPromocao = precoQuarto - (precoQuarto * descontoDiaria);
        int quartos = verificaQuantidadeQuartos();
        int diariasPromocao = verificaDiariasPromocao();
        double valorTotal = 0;

        if (numNoitesEstadia == diariasPromocao) {
            valorTotal = (quartos * precoPromocao * numNoitesEstadia) + getTaxaReserva();

        } else {
            int diariasRestantes = numNoitesEstadia - diariasPromocao;
            valorTotal= (quartos * precoPromocao * diariasPromocao) + (quartos * precoQuarto * diariasRestantes) + getTaxaReserva();
        }

        if (saoReservasMultiplasDe5()) {
            double desconto = valorTotal * (getCliente().getPercentagemDesconto()/100);
            return valorTotal - desconto;
        } else{
            return valorTotal ;
        }
    }

    /**
     * Obtém o número total de reservas de hotel criadas.
     *
     * @return o número total de reservas de hotel.
     */
    public static int getReservaHotelCount() {
        return reservaHotelCount;
    }

    /**
     * Obtém a capacidade máxima de pessoas por quarto.
     *
     * @return a capacidade máxima de pessoas por quarto.
     */
    public static int getCapacidadeMax() {
        return capacidadeMax;
    }

    /**
     * Obtém a porcentagem de desconto aplicada nas diárias promocionais.
     *
     * @return a porcentagem de desconto aplicada nas diárias promocionais.
     */
    public static double getDescontoDiaria() {
        return descontoDiaria;
    }

    /**
     * Define a capacidade máxima de pessoas por quarto.
     *
     * @param capacidadeMax a nova capacidade máxima de pessoas por quarto.
     */
    public static void setCapacidadeMax(int capacidadeMax) {
        ReservaHotel.capacidadeMax = capacidadeMax;
    }

    /**
     * Define a porcentagem de desconto para as diárias promocionais.
     *
     * @param descontoDiaria a nova porcentagem de desconto.
     */
    public static void setDescontoDiaria(double descontoDiaria) {
        ReservaHotel.descontoDiaria = descontoDiaria;
    }
}
