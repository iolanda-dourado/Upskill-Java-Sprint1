package entidades;

import interfaces.Descontavel;
import utilidades.Data;

import java.util.Objects;

public class ReservaHotel extends Reserva implements Descontavel {
    private Hotel hotel;
    private Data dataChegada;
    private int numNoitesEstadia;

    private static final int NUM_NOITES_ESTADIA_OMISSAO = -1;
    private static final String PREFIXO_RESERVA_HOTEL = "R_HTL-";
    private static int reservaHotelCount = 0;
    private static int capacidadeMax = 2;
    private static double descontoDiaria = 0.3;

    public ReservaHotel(Data dataReserva, int qntPessoas, Cliente cliente, Hotel hotel, Data dataChegada, int numNoitesEstadia) {
        super(dataReserva, qntPessoas, cliente);
        ++reservaHotelCount;
        this.setCodigoReserva(gerarIdentificador());
        this.hotel = hotel;
        this.dataChegada = new Data (dataChegada);
        this.numNoitesEstadia = numNoitesEstadia;
    }

    public ReservaHotel() {
        super();
        ++reservaHotelCount;
        this.setCodigoReserva(gerarIdentificador());
        this.hotel = new Hotel();
        this.dataChegada = new Data();
        this.numNoitesEstadia = NUM_NOITES_ESTADIA_OMISSAO;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Data getDataChegada() {
        return dataChegada;
    }

    public int getNumNoitesEstadia() {
        return numNoitesEstadia;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setDataChegada(Data dataChegada) {
        this.dataChegada = dataChegada;
    }

    public void setNumNoitesEstadia(int numNoitesEstadia) {
        this.numNoitesEstadia = numNoitesEstadia;
    }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReservaHotel that = (ReservaHotel) o;
        return numNoitesEstadia == that.numNoitesEstadia && Objects.equals(hotel, that.hotel) && Objects.equals(dataChegada, that.dataChegada);
    }

    public static int getReservaHotelCount() {
        return reservaHotelCount;
    }

    public static int getCapacidadeMax() {
        return capacidadeMax;
    }

    public static double getDescontoDiaria() {
        return descontoDiaria;
    }

    public static void setCapacidadeMax(int capacidadeMax) {
        ReservaHotel.capacidadeMax = capacidadeMax;
    }

    public static void setDescontoDiaria(double descontoDiaria) {
        ReservaHotel.descontoDiaria = descontoDiaria;
    }

    public int verificaDiariasPromocao() {
        int count = 0;
        int dataTemp = formatarData(dataChegada);
        for (int i = 0; i < numNoitesEstadia; i++) {
            if (isPromocao(dataTemp+i, dataChegada)) {
                count++;
            }
        }
        return count;
    }

    public int verificaQuantidadeQuartos() {
        int numQuartos = getQntPessoas()/capacidadeMax;
        if(getQntPessoas() % capacidadeMax == 1) {
            numQuartos = (getQntPessoas()/capacidadeMax) +1;
        }

        return numQuartos;
    }

    @Override
    public String gerarIdentificador() {
        return String.format("%s%s", PREFIXO_RESERVA_HOTEL, reservaHotelCount);
    }

    @Override
    public int formatarData(Data umaData) {
        String st = umaData.toAnoMesDiaString().replace("/", "");
        return Integer.parseInt(st);
    }


    @Override
    public boolean isPromocao(int a, Data umaData) {
        int dataTemp1 = Integer.parseInt(umaData.getAno() + Descontavel.INICIO_TEMP1);
        int dataTemp2 = Integer.parseInt(umaData.getAno() +  Descontavel.FINAL_TEMP1);
        int dataTemp3 = Integer.parseInt(umaData.getAno() +  Descontavel.INICIO_TEMP2);
        int dataTemp4 = Integer.parseInt(umaData.getAno() + Descontavel.FINAL_TEMP2);
        int dataTemp5 = Integer.parseInt(umaData.getAno() + Descontavel.INICIO_TEMP3);
        int dataTemp6 = Integer.parseInt(umaData.getAno() + Descontavel.FINAL_TEMP3);

        if (a >= dataTemp1 || a <= dataTemp2){
            return true;
        } else if (a >= dataTemp3 || a <= dataTemp4) {
            return true;
        } else return a >= dataTemp5 || a <= dataTemp6;
    }

    @Override
    public double calcularCustoReserva() {
        double precoQuarto = hotel.getPrecoPorQuarto();
        double precoPromocao = precoQuarto-(precoQuarto*descontoDiaria);
        int quartos = verificaQuantidadeQuartos();
        int diariasPromocao = verificaDiariasPromocao();

        if(numNoitesEstadia == diariasPromocao) {
            return (quartos * precoPromocao *numNoitesEstadia) + getTaxaReserva();

        } else {
            int diariasRestantes = numNoitesEstadia - diariasPromocao;
            return (quartos * precoPromocao * diariasPromocao)+(quartos * precoQuarto * diariasRestantes)+getTaxaReserva();
        }
    }
}
