package entidades;

import enums.CategoriaHotel;
import interfaces.Identificacavel;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representa um hotel com detalhes sobre sua identificação, categoria, localidade,
 * disponibilidade de transfer e preço por quarto.
 *
 * @Author Iolanda Dourado e Marianna Ramos
 */
public class Hotel implements Identificacavel, Serializable {
    /**
     * Código único de identificação do hotel.
     */
    private String codigoHotel;

    /**
     * Nome do hotel.
     */
    private String nomeHotel;

    /**
     * Categoria do hotel baseada no enum {@link CategoriaHotel}.
     */
    private CategoriaHotel categoria;

    /**
     * Localidade onde o hotel está situado.
     */
    private String localidade;

    /**
     * Indica se o hotel oferece serviço de transfer.
     */
    private boolean transfer;

    /**
     * Preço por quarto no hotel.
     */
    private double precoPorQuarto;



    /**
     * Nome padrão para hotéis não informados.
     */
    private static final String NOME_HOTEL_POR_OMISSAO = "Sem nome";

    /**
     * Categoria padrão para hotéis não informados.
     */
    private static final CategoriaHotel CATEGORIA_HOTEL_POR_OMISSAO = CategoriaHotel.UMA_ESTRELA;

    /**
     * Localidade padrão para hotéis não informados.
     */
    private static final String LOCALIDADE_POR_OMISSAO = "Desconhecida";

    /**
     * Valor padrão para o serviço de transfer (false por omissão).
     */
    private static final boolean TRANSFER_POR_OMISSAO = false;

    /**
     * Preço padrão por quarto para hotéis não informados.
     */
    private static final double PRECO_QUARTO_POR_OMISSAO = -1;

    /**
     * Contador estático usado para gerar identificadores únicos para hotéis.
     */
    private static int contadorHotel = 0;

    /**
     * Construtor que inicializa um hotel com os valores fornecidos.
     *
     * @param nomeHotel Nome do hotel.
     * @param categoria Categoria do hotel.
     * @param localidade Localidade do hotel.
     * @param transfer Indica se o hotel oferece serviço de transfer.
     * @param precoPorQuarto Preço por quarto no hotel.
     */
    public Hotel(String nomeHotel, CategoriaHotel categoria, String localidade, boolean transfer, double precoPorQuarto) {
        this.codigoHotel = gerarIdentificador();
        this.nomeHotel = nomeHotel;
        setCategoria(categoria);
        this.localidade = localidade;
        this.transfer = transfer;
        setPrecoPorQuarto(precoPorQuarto);
    }

    /**
     * Construtor por omissão que inicializa um hotel com valores padrão.
     */
    public Hotel() {
        this.codigoHotel = gerarIdentificador();
        this.nomeHotel = NOME_HOTEL_POR_OMISSAO;
        this.categoria = CATEGORIA_HOTEL_POR_OMISSAO;
        this.localidade = LOCALIDADE_POR_OMISSAO;
        this.transfer = TRANSFER_POR_OMISSAO;
        this.precoPorQuarto = PRECO_QUARTO_POR_OMISSAO;
    }

    /**
     * Construtor de cópia que inicializa um hotel com os valores de outro hotel.
     *
     * @param outro Hotel a ser copiado.
     */
    public Hotel(Hotel outro) {
        this.codigoHotel = outro.codigoHotel;
        this.nomeHotel = outro.nomeHotel;
        this.categoria = outro.categoria;
        this.localidade = outro.localidade;
        this.transfer = outro.transfer;
        this.precoPorQuarto = outro.precoPorQuarto;
    }

    /**
     * Obtém o código único do hotel.
     *
     * @return Código do hotel.
     */
    public String getCodigoHotel() {
        return codigoHotel;
    }

    /**
     * Obtém o nome do hotel.
     *
     * @return Nome do hotel.
     */
    public String getNomeHotel() {
        return nomeHotel;
    }

    /**
     * Obtém a categoria do hotel.
     *
     * @return Categoria do hotel.
     */
    public CategoriaHotel getCategoria() {
        return categoria;
    }

    /**
     * Obtém a localidade do hotel.
     *
     * @return Localidade do hotel.
     */
    public String getLocalidade() {
        return localidade;
    }

    /**
     * Verifica se o hotel oferece serviço de transfer.
     *
     * @return true se o hotel oferece transfer, caso contrário false.
     */
    public boolean isTransfer() {
        return transfer;
    }

    /**
     * Obtém o preço por quarto no hotel.
     *
     * @return Preço por quarto.
     */
    public double getPrecoPorQuarto() {
        return precoPorQuarto;
    }

    /**
     * Define o nome do hotel.
     *
     * @param nomeHotel Nome do hotel.
     */
    public void setNomeHotel(String nomeHotel) {
        this.nomeHotel = nomeHotel;
    }

    /**
     * Define a categoria do hotel.
     *
     * @param categoria Categoria do hotel.
     */
    public void setCategoria(CategoriaHotel categoria) {
        this.categoria = categoria;
    }

    /**
     * Define a localidade do hotel.
     *
     * @param localidade Localidade do hotel.
     */
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    /**
     * Define se o hotel oferece serviço de transfer.
     *
     * @param transfer true se oferece transfer, caso contrário false.
     */
    public void setTransfer(boolean transfer) {
        this.transfer = transfer;
    }

    /**
     * Define o preço por quarto do hotel.
     *
     * @param precoPorQuarto Preço por quarto.
     * @throws IllegalArgumentException Se o preço for negativo.
     */
    public void setPrecoPorQuarto(double precoPorQuarto) {
        if (precoPorQuarto < 0) {
            throw new IllegalArgumentException("O preço do quarto não pode ser negativo.");
        }

        this.precoPorQuarto = precoPorQuarto;
    }

    /**
     * Retorna uma representação textual do hotel.
     *
     * @return Representação textual do hotel.
     */
    @Override
    public String toString() {
        String resposta = transfer ? "Sim" : "Não";
        return String.format("--- HOTEL ---\nCódigo do Hotel: %s\nNome do Hotel: %s\nCategoria do Hotel: %s\nLocalidade: %s\nTem transfer? %s\nPreço por quarto: %.2f", codigoHotel, nomeHotel, categoria, localidade, resposta, precoPorQuarto);
    }

    /**
     * Verifica a igualdade entre dois objetos Hotel.
     *
     * @param outroHotel Objeto a ser comparado.
     * @return true se os objetos forem iguais, caso contrário false.
     */
    @Override
    public boolean equals(Object outroHotel) {
        if (this == outroHotel) return true;
        if (outroHotel == null || getClass() != outroHotel.getClass()) return false;
        Hotel hotel = (Hotel) outroHotel;
        return transfer == hotel.transfer && Double.compare(precoPorQuarto, hotel.precoPorQuarto) == 0 && Objects.equals(nomeHotel, hotel.nomeHotel) && categoria == hotel.categoria && Objects.equals(localidade, hotel.localidade);
    }

    /**
     * Gera um identificador único para o hotel.
     *
     * @return Identificador único.
     */
    @Override
    public String gerarIdentificador() {
        return String.valueOf(++contadorHotel);
    }
}
