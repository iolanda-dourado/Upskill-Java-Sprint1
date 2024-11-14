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
        this.dataChegada = new Data(dataChegada);
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
                diaTemp+=1;
            }
            if (diasParametro < diaTemp) {
                if (mesTemp == 12) {
//                    anoTemp += 1;
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
        return count;
    }

    public int verificaQuantidadeQuartos() {
        int numQuartos = getQntPessoas() / capacidadeMax;
        if (getQntPessoas() % capacidadeMax == 1) {
            numQuartos = (getQntPessoas() / capacidadeMax) + 1;
        }

        return numQuartos;
    }

    @Override
    public String gerarIdentificador() {
        return String.format("%s%s", PREFIXO_RESERVA_HOTEL, reservaHotelCount);
    }

    @Override
    public int formatarData(Data umaData) {
        String st = String.format("%d%d",umaData.getMes(), umaData.getDia());
        return Integer.parseInt(st);
    }


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

    @Override
    public double calcularCustoReserva() {
        double precoQuarto = hotel.getPrecoPorQuarto();
        double precoPromocao = precoQuarto - (precoQuarto * descontoDiaria);
        int quartos = verificaQuantidadeQuartos();
        int diariasPromocao = verificaDiariasPromocao();

        if (numNoitesEstadia == diariasPromocao) {
            return (quartos * precoPromocao * numNoitesEstadia) + getTaxaReserva();

        } else {
            int diariasRestantes = numNoitesEstadia - diariasPromocao;
            return (quartos * precoPromocao * diariasPromocao) + (quartos * precoQuarto * diariasRestantes) + getTaxaReserva();
        }
    }
}
