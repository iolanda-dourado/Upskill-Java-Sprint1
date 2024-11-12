package upskill;

import enums.CompanhiaAerea;

import java.time.LocalTime;
import java.util.Objects;

public class Voo {
    private String codigoVoo;
    private CompanhiaAerea Companhia;
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

    public Voo(String codigoVoo, CompanhiaAerea companhia, int qntLugares, int qntLugaresDisponiveis, Aeroporto aeroportoSaida, Aeroporto aeroportoChegada, int distanciaKmAeroporto, double precoBilhete, Data dataPartida, LocalTime horaPartida) {
        this.codigoVoo = codigoVoo;
        Companhia = companhia;
        this.qntLugares = qntLugares;
        this.qntLugaresDisponiveis = qntLugaresDisponiveis;
        this.aeroportoSaida = aeroportoSaida;
        this.aeroportoChegada = aeroportoChegada;
        this.distanciaKmAeroporto = distanciaKmAeroporto;
        this.precoBilhete = precoBilhete;
        this.dataPartida = dataPartida;
        this.horaPartida = horaPartida;
    }

    public Voo() {
        this.codigoVoo = CODIGO_VOO_OMISSAO;
        Companhia = COMPANHIA_OMISSAO;
        this.qntLugares = QNT_LUGARES_OMISSAO;
        this.qntLugaresDisponiveis = QNT_LUGARES_DISPONIVEIS_OMISSAO;
        this.aeroportoSaida = AEROPORTO_SAIDA_OMISSAO;
        this.aeroportoChegada = AEROPORTO_CHEGADA_OMISSAO;
        this.distanciaKmAeroporto = DISTANCIA_KM_OMISSAO;
        this.precoBilhete = PRECO_BILHETE_OMISSAO;
        this.dataPartida = DATA_PARTIDA_OMISSAO;
        this.horaPartida = HORA_PARTIDA_OMISSAO;
    }

    public String getCodigoVoo() {
        return codigoVoo;
    }

    public CompanhiaAerea getCompanhia() {
        return Companhia;
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
        return dataPartida;
    }

    public LocalTime getHoraPartida() {
        return horaPartida;
    }

    public void setCodigoVoo(String codigoVoo) {
        this.codigoVoo = codigoVoo;
    }

    public void setCompanhia(CompanhiaAerea companhia) {
        Companhia = companhia;
    }

    public void setQntLugares(int qntLugares) {
        this.qntLugares = qntLugares;
    }

    public void setQntLugaresDisponiveis(int qntLugaresDisponiveis) {
        this.qntLugaresDisponiveis = qntLugaresDisponiveis;
    }

    public void setAeroportoSaida(Aeroporto aeroportoSaida) {
        this.aeroportoSaida = aeroportoSaida;
    }

    public void setAeroportoChegada(Aeroporto aeroportoChegada) {
        this.aeroportoChegada = aeroportoChegada;
    }

    public void setDistanciaKmAeroporto(int distanciaKmAeroporto) {
        this.distanciaKmAeroporto = distanciaKmAeroporto;
    }

    public void setPrecoBilhete(double precoBilhete) {
        this.precoBilhete = precoBilhete;
    }

    public void setDataPartida(Data dataPartida) {
        this.dataPartida = dataPartida;
    }

    public void setHoraPartida(LocalTime horaPartida) {
        this.horaPartida = horaPartida;
    }

    @Override
    public String toString() {
        return String.format("Voo: %s\nCompanhia: %s\nQuantidade de lugares: %d\nQuantidade de lugares ainda disponíveis: %d\nAeroporto de Saída: %s\nAeroporto de Chegada: %s\nQuilommetragem entre os aeroportos: %s\nPreço do bilhete: %f\nData de Saída: %s\nHora de Saída: %s\n", Companhia.toString(), qntLugares, qntLugaresDisponiveis, aeroportoSaida.getCodigoAeroporto(), aeroportoChegada.getCodigoAeroporto(), distanciaKmAeroporto, precoBilhete, dataPartida, horaPartida);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voo voo = (Voo) o;
        return qntLugares == voo.qntLugares && qntLugaresDisponiveis == voo.qntLugaresDisponiveis && distanciaKmAeroporto == voo.distanciaKmAeroporto && Double.compare(precoBilhete, voo.precoBilhete) == 0 && Objects.equals(codigoVoo, voo.codigoVoo) && Companhia == voo.Companhia && Objects.equals(aeroportoSaida, voo.aeroportoSaida) && Objects.equals(aeroportoChegada, voo.aeroportoChegada) && Objects.equals(dataPartida, voo.dataPartida) && Objects.equals(horaPartida, voo.horaPartida);
    }
}
