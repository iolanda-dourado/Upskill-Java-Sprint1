package entidades;

import enums.CompanhiaAerea;
import interfaces.Descontavel;
import utilidades.Data;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Representa um voo com detalhes sobre a companhia aérea, número de lugares,
 * aeroportos de partida e chegada, distância entre aeroportos, preço do bilhete,
 * e informações de data e hora de partida.
 *
 * @author  Iolanda Dourado e Marianna Ramos
 */

public class Voo implements Descontavel, Serializable {

    /** Código do voo. */
    private String codigoVoo;

    /** Companhia aérea responsável pelo voo. */
    private CompanhiaAerea companhiaAerea;

    /** Quantidade total de lugares no voo. */
    private int qntLugares;

    /** Quantidade de lugares ainda disponíveis no voo. */
    private int qntLugaresDisponiveis;

    /** Aeroporto de saída do voo. */
    private Aeroporto aeroportoSaida;

    /** Aeroporto de chegada do voo. */
    private Aeroporto aeroportoChegada;

    /** Distância em quilômetros entre os aeroportos de saída e chegada. */
    private double distanciaKmAeroporto;

    /** Preço do bilhete do voo. */
    private double precoBilhete;

    /** Data de partida do voo. */
    private Data dataPartida;

    /** Hora de partida do voo. */
    private LocalTime horaPartida;



    /** Código padrão para um voo. */
    private static final String CODIGO_VOO_OMISSAO = "VOO-XXXX";

    /** Companhia aérea padrão. */
    private static final CompanhiaAerea COMPANHIA_OMISSAO = CompanhiaAerea.TAP;

    /** Quantidade de lugares padrão. */
    private static final int QNT_LUGARES_OMISSAO = -1;

    /** Quantidade de lugares disponíveis padrão. */
    private static final int QNT_LUGARES_DISPONIVEIS_OMISSAO = -1;

    /** Aeroporto de saída padrão. */
    private static final Aeroporto AEROPORTO_SAIDA_OMISSAO = new Aeroporto();

    /** Aeroporto de chegada padrão. */
    private static final Aeroporto AEROPORTO_CHEGADA_OMISSAO = new Aeroporto();

    /** Distância padrão entre aeroportos. */
    private static final double DISTANCIA_KM_OMISSAO = -1;

    /** Preço do bilhete padrão. */
    private static final double PRECO_BILHETE_OMISSAO = 0;

    /** Data de partida padrão. */
    private static final Data DATA_PARTIDA_OMISSAO = new Data();

    /** Hora de partida padrão. */
    private static final LocalTime HORA_PARTIDA_OMISSAO = LocalTime.of(0, 0);

    /** Quantidade de quilômetros para determinar desconto. */
    private static final int KMS_PARA_DAR_DESCONTO = 1000;

    /** Taxa de desconto para voo de regresso. */
    private static final double TAXA_DESCONTO_VOO_REGRESSO = 0.01;

    /** Desconto promocional aplicado ao voo. */
    private static double descontoVoo = 0.3;


    /**
     * Construtor que inicializa um voo com os valores fornecidos.
     *
     * @param codigoVoo código do voo
     * @param companhia companhia aérea
     * @param qntLugares quantidade total de lugares
     * @param aeroportoSaida aeroporto de saída
     * @param aeroportoChegada aeroporto de chegada
     * @param distanciaKmAeroporto distância entre os aeroportos
     * @param precoBilhete preço do bilhete
     * @param dataPartida data de partida
     * @param horaPartida hora de partida
     */
    public Voo(String codigoVoo, CompanhiaAerea companhia, int qntLugares, Aeroporto aeroportoSaida, Aeroporto aeroportoChegada, double distanciaKmAeroporto, double precoBilhete, Data dataPartida, LocalTime horaPartida) {
        this.codigoVoo = codigoVoo;
        companhiaAerea = companhia;
        this.qntLugares = qntLugares;
        setQntLugaresDisponiveis(qntLugares);
        this.aeroportoSaida = aeroportoSaida;
        this.aeroportoChegada = aeroportoChegada;
        this.distanciaKmAeroporto = distanciaKmAeroporto;
        this.precoBilhete = precoBilhete;
        this.dataPartida = new Data(dataPartida);
        this.horaPartida = horaPartida;
    }

    /**
     * Construtor por omissão com valores padrão.
     */
    public Voo() {
        this.codigoVoo = CODIGO_VOO_OMISSAO;
        companhiaAerea = COMPANHIA_OMISSAO;
        this.qntLugares = QNT_LUGARES_OMISSAO;
        this.qntLugaresDisponiveis = QNT_LUGARES_DISPONIVEIS_OMISSAO;
        this.aeroportoSaida = AEROPORTO_SAIDA_OMISSAO;
        this.aeroportoChegada = AEROPORTO_CHEGADA_OMISSAO;
        this.distanciaKmAeroporto = DISTANCIA_KM_OMISSAO;
        this.precoBilhete = PRECO_BILHETE_OMISSAO;
        this.dataPartida = DATA_PARTIDA_OMISSAO;
        this.horaPartida = HORA_PARTIDA_OMISSAO;
    }

    /**
     * Construtor de cópia que inicializa um voo com os valores de outro voo.
     *
     * @param outro outro objeto do tipo Voo a ser copiado
     */
    public Voo(Voo outro) {
        this.codigoVoo = outro.codigoVoo;
        this.companhiaAerea = outro.companhiaAerea;
        this.qntLugares = outro.qntLugares;
        this.qntLugaresDisponiveis = outro.qntLugaresDisponiveis;
        this.aeroportoSaida = new Aeroporto(outro.aeroportoSaida);
        this.aeroportoChegada = new Aeroporto(outro.aeroportoChegada);
        this.distanciaKmAeroporto = outro.distanciaKmAeroporto;
        this.precoBilhete = outro.precoBilhete;
        this.dataPartida = new Data(outro.dataPartida);
        this.horaPartida = outro.horaPartida;
    }

    /**
     * Obtém o código do voo.
     *
     * @return código do voo
     */
    public String getCodigoVoo() {
        return codigoVoo;
    }

    /**
     * Obtém a companhia aérea do voo.
     *
     * @return companhia aérea do voo
     */
    public CompanhiaAerea getCompanhiaAerea() {
        return companhiaAerea;
    }

    /**
     * Obtém a quantidade total de lugares no voo.
     *
     * @return quantidade de lugares
     */
    public int getQntLugares() {
        return qntLugares;
    }

    /**
     * Obtém a quantidade de lugares disponíveis no voo.
     *
     * @return quantidade de lugares disponíveis
     */
    public int getQntLugaresDisponiveis() {
        return qntLugaresDisponiveis;
    }

    /**
     * Obtém o aeroporto de saída do voo.
     *
     * @return aeroporto de saída
     */
    public Aeroporto getAeroportoSaida() {
        return aeroportoSaida;
    }

    /**
     * Obtém o aeroporto de chegada do voo.
     *
     * @return aeroporto de chegada
     */
    public Aeroporto getAeroportoChegada() {
        return aeroportoChegada;
    }

    /**
     * Obtém a distância entre os aeroportos de saída e chegada em quilômetros.
     *
     * @return distância entre aeroportos
     */
    public double getDistanciaKmAeroporto() {
        return distanciaKmAeroporto;
    }

    /**
     * Obtém o preço do bilhete do voo.
     *
     * @return preço do bilhete
     */
    public double getPrecoBilhete() {
        return precoBilhete;
    }

    /**
     * Obtém a data de partida do voo.
     *
     * @return data de partida
     */
    public Data getDataPartida() {
        return new Data (dataPartida);
    }

    /**
     * Obtém a hora de partida do voo.
     *
     * @return hora de partida
     */
    public LocalTime getHoraPartida() {
        return horaPartida;
    }

    /**
     * Define o código do voo.
     *
     * @param codigoVoo código do voo
     */
    public void setCodigoVoo(String codigoVoo) {
        this.codigoVoo = codigoVoo;
    }

    /**
     * Define a companhia aérea do voo.
     *
     * @param companhiaAerea companhia aérea
     */
    public void setCompanhiaAerea(CompanhiaAerea companhiaAerea) {
        this.companhiaAerea = companhiaAerea;
    }

    /**
     * Define a quantidade total de lugares no voo.
     *
     * @param qntLugares quantidade de lugares
     */
    public void setQntLugares(int qntLugares) {
        this.qntLugares = qntLugares;
    }

    /**
     * Define a quantidade de lugares disponíveis no voo.
     *
     * Este método permite ajustar a quantidade de lugares disponíveis para o voo,
     * mas se o valor fornecido for zero, uma exceção {@link ArithmeticException}
     * será lançada, indicando que não há mais lugares disponíveis.
     *
     * @param qntLugaresDisponiveis A quantidade de lugares disponíveis no voo.
     *                              Deve ser maior que zero.
     * @throws ArithmeticException Se a quantidade de lugares disponíveis for igual a zero.
     */
    public void setQntLugaresDisponiveis(int qntLugaresDisponiveis) {
        if (qntLugaresDisponiveis == 0) {
            throw new ArithmeticException("Não existem mais lugares disponíveis.");
        }

        this.qntLugaresDisponiveis = qntLugaresDisponiveis;
    }

    /**
     * Define o aeroporto de saída do voo.
     *
     * @param aeroportoSaida aeroporto de saída
     */
    public void setAeroportoSaida(Aeroporto aeroportoSaida) {
        this.aeroportoSaida = aeroportoSaida;
    }

    /**
     * Define o aeroporto de chegada do voo.
     *
     * Este método permite definir o aeroporto de chegada do voo, mas se o aeroporto
     * de chegada for o mesmo que o aeroporto de saída, será lançada uma exceção
     * {@link IllegalArgumentException} com uma mensagem informando que os aeroportos
     * devem ser diferentes.
     *
     * @param aeroportoChegada O aeroporto de chegada do voo.
     * @throws IllegalArgumentException Se o aeroporto de chegada for o mesmo que o aeroporto de saída.
     */
    public void setAeroportoChegada(Aeroporto aeroportoChegada) {
        if (this.aeroportoSaida == aeroportoChegada) {
            throw new IllegalArgumentException("O aeroporto da volta deve ser diferente do aeroporto da ida.");
        }
        this.aeroportoChegada = aeroportoChegada;
    }

    /**
     * Define a distância entre os aeroportos de saída e chegada em quilômetros.
     *
     * @param distanciaKmAeroporto distância entre aeroportos
     */
    public void setDistanciaKmAeroporto(double distanciaKmAeroporto) {
        this.distanciaKmAeroporto = distanciaKmAeroporto;
    }

    /**
     * Define o preço por quarto do hotel.
     *
     * @param precoBilhete Preço por quarto.
     * @throws IllegalArgumentException Se o preço for negativo.
     */
    public void setPrecoBilhete(double precoBilhete) {
        if (precoBilhete < 0) {
            throw new IllegalArgumentException("O preço do bilhete não pode ser negativo.");
        }

        this.precoBilhete = precoBilhete;
    }

    /**
     * Define a data de partida do voo.
     *
     * @param dataPartida data de partida
     */
    public void setDataPartida(Data dataPartida) {
        this.dataPartida.setData(dataPartida.getAno(), dataPartida.getMes(), dataPartida.getDia());
    }

    /**
     * Define a hora de partida do voo.
     *
     * @param horaPartida hora de partida
     */
    public void setHoraPartida(LocalTime horaPartida) {
        this.horaPartida = horaPartida;
    }

    /**
     * Retorna uma representação textual do voo.
     *
     * @return Descrição do voo
     */
    @Override
    public String toString() {
        return String.format("--- Voo ---\nCódigo do Voo: %s\nCompanhia Aérea: %s\nQuantidade de Lugares: %d\nQuantidade de Lugares Disponíveis: %d\nAeroporto de Saída: %s\nAeroporto de Chegada: %s\nDistância entre Aeroportos: %.2f km\nPreço do Bilhete: %.2f\nData de Partida: %s\nHora de Partida: %s", codigoVoo, companhiaAerea, qntLugares, qntLugaresDisponiveis, aeroportoSaida.getCodigoAeroporto(), aeroportoChegada.getCodigoAeroporto(), distanciaKmAeroporto, precoBilhete, dataPartida, horaPartida);
    }

    /**
     * Verifica a igualdade entre dois objetos Voo.
     *
     * @param outroVoo Objeto a ser comparado.
     * @return true se os objetos forem iguais, caso contrário false.
     */
    @Override
    public boolean equals(Object outroVoo) {
        if (this == outroVoo) return true;
        if (outroVoo == null || getClass() != outroVoo.getClass()) return false;
        Voo voo = (Voo) outroVoo;
        return qntLugares == voo.qntLugares && distanciaKmAeroporto == voo.distanciaKmAeroporto && Double.compare(precoBilhete, voo.precoBilhete) == 0 && Objects.equals(codigoVoo, voo.codigoVoo) && companhiaAerea == voo.companhiaAerea && Objects.equals(aeroportoSaida, voo.aeroportoSaida) && Objects.equals(aeroportoChegada, voo.aeroportoChegada) && Objects.equals(dataPartida, voo.dataPartida) && Objects.equals(horaPartida, voo.horaPartida);
    }

    /**
     * Formata a data em um formato inteiro no estilo "MMDD".
     *
     * Este método recebe uma data e retorna uma representação numérica da data no formato
     * "MMDD" (mês e dia), como um número inteiro. A data é formatada com dois dígitos para
     * o mês e dois dígitos para o dia.
     *
     * @param umaData O objeto {@link Data} que contém a data a ser formatada.
     * @return Um número inteiro representando a data no formato "MMDD".
     */
    @Override
    public int formatarData(Data umaData) {
         String st = String.format("%02d%02d",umaData.getMes(), umaData.getDia());
        return Integer.parseInt(st);
    }

    /**
     * Verifica se a data fornecida está dentro de algum período promocional.
     *
     * Este método recebe uma data formatada (como um número inteiro no formato "MMDD")
     * e verifica se essa data está dentro de um dos períodos promocionais definidos pelas
     * constantes de início e fim das temporadas. Existem três períodos promocionais,
     * e o método retorna `true` se a data fornecida estiver dentro de qualquer um desses
     * períodos e `false` caso contrário.
     *
     * @param dataFormatada A data a ser verificada, representada como um número inteiro
     *                      no formato "MMDD" (mês e dia).
     * @return `true` se a data fornecida estiver dentro de algum período promocional,
     *         e `false` caso contrário.
     */
    @Override
    public boolean isPromocao(int dataFormatada) {
        if (dataFormatada >= Descontavel.INICIO_TEMP1 && dataFormatada <= Descontavel.FINAL_TEMP1){
            return true;
        } else if (dataFormatada >= Descontavel.INICIO_TEMP2 && dataFormatada <= Descontavel.FINAL_TEMP2) {
            return true;
        } else return dataFormatada >= Descontavel.INICIO_TEMP3 && dataFormatada <= Descontavel.FINAL_TEMP3;
    }

    /**
     * Calcula o custo do bilhete de ida, aplicando o desconto da temporada, se aplicável.
     *
     * Este método calcula o valor do bilhete de ida, verificando se o voo está dentro de
     * uma temporada promocional. Se o voo ocorrer durante uma temporada promocional,
     * será aplicado um desconto no preço do bilhete, baseado na constante {@link #descontoVoo}.
     * Caso contrário, o preço do bilhete será o preço original sem desconto.
     *
     * @return O custo do bilhete de ida, com ou sem desconto, dependendo da temporada.
     */
    public double calcularCustoBilheteIda() {
        double valorDescontoTemporada = precoBilhete * descontoVoo;
        // 1 - Ter desconto da temporada
        if (isPromocao(formatarData(dataPartida))){
            return precoBilhete - valorDescontoTemporada;
        }

        return precoBilhete;
    }

    /**
     * Calcula o custo do bilhete de volta, aplicando os descontos da temporada e da distância.
     *
     * Este método calcula o valor do bilhete de volta, levando em consideração dois tipos de desconto:
     * 1. Desconto da temporada: Se o voo estiver dentro de um período promocional, será aplicado um desconto no bilhete,
     *    com base na constante {@link #descontoVoo}.
     * 2. Desconto baseado na distância do voo: A cada 1000 km percorridos, será aplicado um desconto adicional,
     *    com base na constante {@link #TAXA_DESCONTO_VOO_REGRESSO}.
     *
     * Se o voo estiver dentro de uma temporada promocional, ambos os descontos (da temporada e da distância) serão aplicados.
     * Caso contrário, somente o desconto de distância será aplicado.
     *
     * @return O custo do bilhete de volta, com os descontos aplicados (se houver).
     */
    public double calcularCustoBilheteVolta() {
       // Desconto da temporada
        double valorDescontoTemporada = precoBilhete * descontoVoo;
        boolean estaNaPromocao = isPromocao(formatarData(dataPartida));
        // Desconto da Kms
        int blocosDe1000km = (int)distanciaKmAeroporto / KMS_PARA_DAR_DESCONTO;
        double descontoKms = blocosDe1000km * TAXA_DESCONTO_VOO_REGRESSO;
        double valorDescontoKms = descontoKms * precoBilhete;

        if (estaNaPromocao) {
            return precoBilhete - (valorDescontoTemporada + valorDescontoKms);
        }
        return precoBilhete - (valorDescontoKms);
    }

    /**
     * Retorna o valor do desconto aplicado aos voos no período promocional.
     *
     * Este método é um getter estático que retorna o valor da constante {@link #descontoVoo},
     * que representa a porcentagem de desconto aplicada ao preço do bilhete durante as promoções.
     *
     * @return O valor do desconto aplicado aos voos no período promocional, representado
     * como um número decimal (por exemplo, 0.3 para 30%).
     */
    public static double getDescontoVoo() {
        return descontoVoo;
    }
}