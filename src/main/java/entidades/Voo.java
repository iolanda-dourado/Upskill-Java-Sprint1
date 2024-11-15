package entidades;

import enums.CompanhiaAerea;
import interfaces.Descontavel;
import utilidades.Data;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

public class Voo implements Descontavel, Serializable {
    private String codigoVoo;
    private CompanhiaAerea companhiaAerea;
    private int qntLugares;
    private int qntLugaresDisponiveis;
    private Aeroporto aeroportoSaida;
    private Aeroporto aeroportoChegada;
    private double distanciaKmAeroporto;
    private double precoBilhete;
    private Data dataPartida;
    private LocalTime horaPartida;

    private static final String CODIGO_VOO_OMISSAO = "VOO-XXXX";
    private static final CompanhiaAerea COMPANHIA_OMISSAO = CompanhiaAerea.TAP;
    private static final int QNT_LUGARES_OMISSAO = -1;
    private static final int QNT_LUGARES_DISPONIVEIS_OMISSAO = -1;
    private static final Aeroporto AEROPORTO_SAIDA_OMISSAO = new Aeroporto();
    private static final Aeroporto AEROPORTO_CHEGADA_OMISSAO = new Aeroporto();
    private static final double DISTANCIA_KM_OMISSAO = -1;
    private static final double PRECO_BILHETE_OMISSAO = 0;
    private static final Data DATA_PARTIDA_OMISSAO = new Data();
    private static final LocalTime HORA_PARTIDA_OMISSAO = LocalTime.of(0,0);
    private static final int KMS_PARA_DAR_DESCONTO = 1000;
    private static final double TAXA_DESCONTO_VOO_REGRESSO = 0.01;

    private static double descontoVoo = 0.3;

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

    public String getCodigoVoo() {
        return codigoVoo;
    }

    public CompanhiaAerea getCompanhiaAerea() {
        return companhiaAerea;
    }

    public int getQntLugares() {
        return qntLugares;
    }

    public int getQntLugaresDisponiveis() {
        return qntLugaresDisponiveis;
    }

    public Aeroporto getAeroportoSaida() {
        return aeroportoSaida;
    }

    public Aeroporto getAeroportoChegada() {
        return aeroportoChegada;
    }

    public double getDistanciaKmAeroporto() {
        return distanciaKmAeroporto;
    }

    public double getPrecoBilhete() {
        return precoBilhete;
    }

    public Data getDataPartida() {
        return new Data (dataPartida);
    }

    public LocalTime getHoraPartida() {
        return horaPartida;
    }

    public void setCodigoVoo(String codigoVoo) {
        this.codigoVoo = codigoVoo;
    }

    public void setCompanhiaAerea(CompanhiaAerea companhiaAerea) {
        this.companhiaAerea = companhiaAerea;
    }

    public void setQntLugares(int qntLugares) {
        this.qntLugares = qntLugares;
    }

    public void setQntLugaresDisponiveis(int qntLugaresDisponiveis) {
        if (qntLugaresDisponiveis == 0) {
            throw new ArithmeticException("Não existem mais lugares disponíveis.");
        }

        this.qntLugaresDisponiveis = qntLugaresDisponiveis;
    }

    public void setAeroportoSaida(Aeroporto aeroportoSaida) {
        this.aeroportoSaida = aeroportoSaida;
    }

    public void setAeroportoChegada(Aeroporto aeroportoChegada) {
        if (this.aeroportoSaida == aeroportoChegada) {
            throw new IllegalArgumentException("O aeroporto da volta deve ser diferente do aeroporto da ida.");
        }
        this.aeroportoChegada = aeroportoChegada;
    }

    public void setDistanciaKmAeroporto(double distanciaKmAeroporto) {
        this.distanciaKmAeroporto = distanciaKmAeroporto;
    }

    public void setPrecoBilhete(double precoBilhete) {
        this.precoBilhete = precoBilhete;
    }

    public void setDataPartida(Data dataPartida) {
        this.dataPartida.setData(dataPartida.getAno(), dataPartida.getMes(), dataPartida.getDia());
    }

    public void setHoraPartida(LocalTime horaPartida) {
        this.horaPartida = horaPartida;
    }

    @Override
    public String toString() {
        return String.format("--- Voo ---\nCódigo do Voo: %s\nCompanhia Aérea: %s\nQuantidade de Lugares: %d\nQuantidade de Lugares Disponíveis: %d\nAeroporto de Saída: %s\nAeroporto de Chegada: %s\nDistância entre Aeroportos: %.2f km\nPreço do Bilhete: %.2f\nData de Partida: %s\nHora de Partida: %s", codigoVoo, companhiaAerea, qntLugares, qntLugaresDisponiveis, aeroportoSaida.getCodigoAeroporto(), aeroportoChegada.getCodigoAeroporto(), distanciaKmAeroporto, precoBilhete, dataPartida, horaPartida);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voo voo = (Voo) o;
        return qntLugares == voo.qntLugares && distanciaKmAeroporto == voo.distanciaKmAeroporto && Double.compare(precoBilhete, voo.precoBilhete) == 0 && Objects.equals(codigoVoo, voo.codigoVoo) && companhiaAerea == voo.companhiaAerea && Objects.equals(aeroportoSaida, voo.aeroportoSaida) && Objects.equals(aeroportoChegada, voo.aeroportoChegada) && Objects.equals(dataPartida, voo.dataPartida) && Objects.equals(horaPartida, voo.horaPartida);
    }

    @Override
    public int formatarData(Data umaData) {
         String st = String.format("%02d%02d",umaData.getMes(), umaData.getDia());
        return Integer.parseInt(st);
    }

    @Override
    public boolean isPromocao(int dataFormatada) {
        if (dataFormatada >= Descontavel.INICIO_TEMP1 && dataFormatada <= Descontavel.FINAL_TEMP1){
            return true;
        } else if (dataFormatada >= Descontavel.INICIO_TEMP2 && dataFormatada <= Descontavel.FINAL_TEMP2) {
            return true;
        } else return dataFormatada >= Descontavel.INICIO_TEMP3 && dataFormatada <= Descontavel.FINAL_TEMP3;
    }

    public double calcularCustoBilheteIda() {
        double valorDescontoTemporada = precoBilhete * descontoVoo;
        // 1 - Ter desconto da temporada
        if (isPromocao(formatarData(dataPartida))){
            return precoBilhete - valorDescontoTemporada;
        }

        return precoBilhete;
    }

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

    public static double getDescontoVoo() {
        return descontoVoo;
    }
}
