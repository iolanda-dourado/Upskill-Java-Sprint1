package entidades;

import enums.CategoriaHotel;
import interfaces.Identificacavel;

import java.io.Serializable;
import java.util.Objects;

public class Hotel implements Identificacavel, Serializable {
    private String codigoHotel;
    private String nomeHotel;
    private CategoriaHotel categoria;
    private String localidade;
    private boolean transfer;
    private double precoPorQuarto;

    private static final String NOME_HOTEL_POR_OMISSAO = "Sem nome";
    private static final CategoriaHotel CATEGORIA_HOTEL_POR_OMISSAO = CategoriaHotel.UMA_ESTRELA;
    private static final String LOCALIDADE_POR_OMISSAO = "Desconhecida";
    private static final boolean TRANSFER_POR_OMISSAO = false;
    private static final double PRECO_QUARTO_POR_OMISSAO = -1;

    private static int contadorHotel = 0;

    public Hotel(String nomeHotel, CategoriaHotel categoria, String localidade, boolean transfer, double precoPorQuarto) {
        this.codigoHotel = gerarIdentificador();
        this.nomeHotel = nomeHotel;
        setCategoria(categoria);
        this.localidade = localidade;
        this.transfer = transfer;
        setPrecoPorQuarto(precoPorQuarto);
    }

    public Hotel() {
        this.codigoHotel = gerarIdentificador();
        this.nomeHotel = NOME_HOTEL_POR_OMISSAO;
        this.categoria = CATEGORIA_HOTEL_POR_OMISSAO;
        this.localidade = LOCALIDADE_POR_OMISSAO;
        this.transfer = TRANSFER_POR_OMISSAO;
        this.precoPorQuarto = PRECO_QUARTO_POR_OMISSAO;
    }

    public Hotel(Hotel outro) {
        this.codigoHotel = outro.codigoHotel;
        this.nomeHotel = outro.nomeHotel;
        this.categoria = outro.categoria;
        this.localidade = outro.localidade;
        this.transfer = outro.transfer;
        this.precoPorQuarto = outro.precoPorQuarto;
    }

    public String getCodigoHotel() {
        return codigoHotel;
    }

    public String getNomeHotel() {
        return nomeHotel;
    }

    public CategoriaHotel getCategoria() {
        return categoria;
    }

    public String getLocalidade() {
        return localidade;
    }

    public boolean isTransfer() {
        return transfer;
    }

    public double getPrecoPorQuarto() {
        return precoPorQuarto;
    }

    public void setNomeHotel(String nomeHotel) {
        this.nomeHotel = nomeHotel;
    }

    public void setCategoria(CategoriaHotel categoria) {
        boolean categoriaInvalida = true;
        for (CategoriaHotel cat : CategoriaHotel.values()) {
            if (cat == categoria) {
                categoriaInvalida = false;
                break;
            }
        }
        if (categoriaInvalida) {
            throw new IllegalArgumentException("A categoria informada não é válida.");
        }
        this.categoria = categoria;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public void setTransfer(boolean transfer) {
        this.transfer = transfer;
    }

    public void setPrecoPorQuarto(double precoPorQuarto) {
        if (precoPorQuarto < 0) {
            throw new IllegalArgumentException("O preço do quarto não pode ser negativo.");
        }

        this.precoPorQuarto = precoPorQuarto;
    }

    @Override
    public String toString() {
        String resposta = transfer ? "Sim" : "Não";
        return String.format("--- HOTEL ---\nCódigo do Hotel: %s\nNome do Hotel: %s\nCategoria do Hotel: %s\nLocalidade: %s\nTem transfer? %s\nPreço por quarto: %.2f", codigoHotel, nomeHotel, categoria, localidade, resposta, precoPorQuarto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return transfer == hotel.transfer && Double.compare(precoPorQuarto, hotel.precoPorQuarto) == 0 && Objects.equals(nomeHotel, hotel.nomeHotel) && categoria == hotel.categoria && Objects.equals(localidade, hotel.localidade);
    }

    @Override
    public String gerarIdentificador() {
        return String.valueOf(++contadorHotel);
    }
}
