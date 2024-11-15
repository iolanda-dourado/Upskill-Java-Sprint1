package entidades;

import utilidades.Data;

import java.util.Objects;

public class ReservaHotelVoo extends ReservaHotel {
    private Voo voo;

    private static final String PREFIXO_RESERVA_HOTEL_VOO = "R_HTL_VOO-";
    private static int reservaHotelVooCount = 0;

    public ReservaHotelVoo(Data dataReserva, int qntPessoas, Cliente cliente, Hotel hotel, Data dataChegada, int numNoitesEstadia, Voo voo) {
        super(dataReserva, qntPessoas, cliente, hotel, dataChegada, numNoitesEstadia);
        ++reservaHotelVooCount;
        this.voo = voo;
    }

    public ReservaHotelVoo() {
        super();
        ++reservaHotelVooCount;
        this.voo = new Voo();
    }

    public ReservaHotelVoo(ReservaHotelVoo outro) {
        super(outro);
        this.voo = new Voo(outro.voo);
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    @Override
    public String toString() {
        return String.format("--- Reserva Hotel Voo ---\n%s\nVoo: %s", super.toString(), voo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReservaHotelVoo that = (ReservaHotelVoo) o;
        return Objects.equals(voo, that.voo);
    }

    @Override
    public String gerarIdentificador() {
        return String.format("%s%s", PREFIXO_RESERVA_HOTEL_VOO, reservaHotelVooCount);
    }

    @Override
    public double calcularCustoReserva() {
        int quartos = verificaQuantidadeQuartos();
        double precoQuarto = getHotel().getPrecoPorQuarto();
        double precoPromocao = precoQuarto-(precoQuarto*getDescontoDiaria());
        int diariasPromocao = verificaDiariasPromocao();
        double reservaHotel = 0;

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
            return reservaTotal - desconto;
        } else{
            return reservaHotel + custoReservaVoo+getTaxaReserva();
        }
    }

    public static int getReservaHotelVooCount() {
        return reservaHotelVooCount;
    }
}
