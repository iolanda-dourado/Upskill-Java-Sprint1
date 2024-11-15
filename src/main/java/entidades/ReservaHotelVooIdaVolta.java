package entidades;

import utilidades.Data;

import java.util.Objects;

public class ReservaHotelVooIdaVolta extends ReservaHotelVoo {
    private Voo vooRegresso;

    private static final String PREFIXO_RESERVA_HOTEL_VOO_IV = "R_HTL_VOO_IV-";
    private static int reservaHotelVooIdaVoltaCount = 0;


    public ReservaHotelVooIdaVolta(Data dataReserva, int qntPessoas, Cliente cliente, Hotel hotel, Data dataChegada, int numNoitesEstadia, Voo voo, Voo vooRegresso) {
        super(dataReserva, qntPessoas, cliente, hotel, dataChegada, numNoitesEstadia, voo);
        ++reservaHotelVooIdaVoltaCount;
        this.vooRegresso = vooRegresso;
    }

    public ReservaHotelVooIdaVolta() {
        super();
        ++reservaHotelVooIdaVoltaCount;
        this.vooRegresso = new Voo();
    }

    public ReservaHotelVooIdaVolta(ReservaHotelVooIdaVolta outra) {
        super(outra);
        this.vooRegresso = new Voo(outra.vooRegresso);
    }

    public Voo getVooRegresso() {
        return vooRegresso;
    }

    public void setVooRegresso(Voo vooRegresso) {
        this.vooRegresso = vooRegresso;
    }

    @Override
    public String toString() {
        return String.format("-- Reserva de Hotel com Voo de Ida e Volta --\n%s\nVoo de Regresso: %s", super.toString(), vooRegresso);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReservaHotelVooIdaVolta that = (ReservaHotelVooIdaVolta) o;
        return Objects.equals(vooRegresso, that.vooRegresso);
    }

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

    @Override
    public String gerarIdentificador() {
        return String.format("%s%s", PREFIXO_RESERVA_HOTEL_VOO_IV, reservaHotelVooIdaVoltaCount);
    }

    public static int getReservaHotelVooIdaVoltaCount() {
        return reservaHotelVooIdaVoltaCount;
    }
}
