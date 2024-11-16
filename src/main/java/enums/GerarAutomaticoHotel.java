package enums;

/**
 * Enum {@code GerarAutomaticoHotel} representa hotéis com informações sobre nome, categoria, localidade,
 * disponibilidade de transfer e preço por quarto.
 */
public enum GerarAutomaticoHotel {

    /** Hotel Pôr do Sol, 3 estrelas, localizado em Lisboa, com transfer disponível e preço de 120.50 por quarto. */
    HOTEL1("Hotel Pôr do Sol", CategoriaHotel.TRES_ESTRELAS, "Lisboa", true, 120.50),

    /** Hotel Bela Vista, 4 estrelas, localizado no Porto, sem transfer e preço de 150.00 por quarto. */
    HOTEL2("Hotel Bela Vista", CategoriaHotel.QUATRO_ESTRELAS, "Porto", false, 150.00),

    /** Resort das Palmeiras, 5 estrelas, localizado em Faro, com transfer disponível e preço de 300.75 por quarto. */
    HOTEL3("Resort das Palmeiras", CategoriaHotel.CINCO_ESTRELAS, "Faro", true, 300.75),

    /** Hotel Montanha Azul, 1 estrela, localizado na Serra da Estrela, sem transfer e preço de 80.25 por quarto. */
    HOTEL4("Hotel Montanha Azul", CategoriaHotel.UMA_ESTRELA, "Serra da Estrela", false, 80.25),

    /** Albergue do Sol, 2 estrelas, localizado em Albufeira, sem transfer e preço de 95.00 por quarto. */
    HOTEL5("Albergue do Sol", CategoriaHotel.DUAS_ESTRELAS, "Albufeira", false, 95.00),

    /** Hotel Mar de Prata, 3 estrelas, localizado em Aveiro, com transfer disponível e preço de 110.00 por quarto. */
    HOTEL6("Hotel Mar de Prata", CategoriaHotel.TRES_ESTRELAS, "Aveiro", true, 110.00),

    /** Hotel Jardim Secreto, 4 estrelas, localizado em Sintra, com transfer disponível e preço de 200.45 por quarto. */
    HOTEL7("Hotel Jardim Secreto", CategoriaHotel.QUATRO_ESTRELAS, "Sintra", true, 200.45),

    /** Pousada do Caminho, 2 estrelas, localizada em Coimbra, sem transfer e preço de 85.00 por quarto. */
    HOTEL8("Pousada do Caminho", CategoriaHotel.DUAS_ESTRELAS, "Coimbra", false, 85.00),

    /** Refúgio das Águas, 4 estrelas, localizado em Peniche, com transfer disponível e preço de 175.80 por quarto. */
    HOTEL9("Refúgio das Águas", CategoriaHotel.QUATRO_ESTRELAS, "Peniche", true, 175.80),

    /** Hotel Lago Verde, 5 estrelas, localizado em Évora, com transfer disponível e preço de 320.00 por quarto. */
    HOTEL10("Hotel Lago Verde", CategoriaHotel.CINCO_ESTRELAS, "Évora", true, 320.00);

    /** O nome do hotel. */
    private final String nomeHotel;

    /** A categoria do hotel. */
    private final CategoriaHotel categoria;

    /** A localidade onde o hotel está localizado. */
    private final String localidade;

    /** Indica se o hotel oferece transfer (verdadeiro ou falso). */
    private final boolean transfer;

    /** O preço por quarto no hotel. */
    private final double precoPorQuarto;

    /**
     * Constrói uma nova instância de {@code GerarAutomaticoHotel} com as informações do hotel.
     *
     * @param nomeHotel O nome do hotel.
     * @param categoria A categoria do hotel.
     * @param localidade A localidade onde o hotel está localizado.
     * @param transfer Indica se o hotel oferece transfer.
     * @param precoPorQuarto O preço por quarto no hotel.
     */
    GerarAutomaticoHotel(String nomeHotel, CategoriaHotel categoria, String localidade, boolean transfer, double precoPorQuarto) {
        this.nomeHotel = nomeHotel;
        this.categoria = categoria;
        this.localidade = localidade;
        this.transfer = transfer;
        this.precoPorQuarto = precoPorQuarto;
    }

    /**
     * Retorna o nome do hotel.
     *
     * @return O nome do hotel.
     */
    public String getNomeHotel() {
        return nomeHotel;
    }

    /**
     * Retorna a categoria do hotel.
     *
     * @return A categoria do hotel.
     */
    public CategoriaHotel getCategoria() {
        return categoria;
    }

    /**
     * Retorna a localidade onde o hotel está localizado.
     *
     * @return A localidade do hotel.
     */
    public String getLocalidade() {
        return localidade;
    }

    /**
     * Retorna se o hotel oferece transfer.
     *
     * @return {@code true} se o hotel oferece transfer, {@code false} caso contrário.
     */
    public boolean getTransfer() {
        return transfer;
    }

    /**
     * Retorna o preço por quarto no hotel.
     *
     * @return O preço por quarto no hotel.
     */
    public double getPrecoPorQuarto() {
        return precoPorQuarto;
    }
}
