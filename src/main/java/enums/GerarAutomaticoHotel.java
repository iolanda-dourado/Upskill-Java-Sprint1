package enums;

public enum GerarAutomaticoHotel {
    HOTEL1("Hotel Pôr do Sol", CategoriaHotel.TRES_ESTRELAS, "Lisboa", true, 120.50),
    HOTEL2("Hotel Bela Vista", CategoriaHotel.QUATRO_ESTRELAS, "Porto", false, 150.00),
    HOTEL3("Resort das Palmeiras", CategoriaHotel.CINCO_ESTRELAS, "Faro", true, 300.75),
    HOTEL4("Hotel Montanha Azul", CategoriaHotel.UMA_ESTRELA, "Serra da Estrela", false, 80.25),
    HOTEL5("Albergue do Sol", CategoriaHotel.DUAS_ESTRELAS, "Albufeira", false, 95.00),
    HOTEL6("Hotel Mar de Prata", CategoriaHotel.TRES_ESTRELAS, "Aveiro", true, 110.00),
    HOTEL7("Hotel Jardim Secreto", CategoriaHotel.QUATRO_ESTRELAS, "Sintra", true, 200.45),
    HOTEL8("Pousada do Caminho", CategoriaHotel.DUAS_ESTRELAS, "Coimbra", false, 85.00),
    HOTEL9("Refúgio das Águas", CategoriaHotel.QUATRO_ESTRELAS, "Peniche", true, 175.80),
    HOTEL10("Hotel Lago Verde", CategoriaHotel.CINCO_ESTRELAS, "Évora", true, 320.00);

    private final String nomeHotel;
    private final CategoriaHotel categoria;
    private final String localidade;
    private final boolean transfer;
    private final double precoPorQuarto;

    GerarAutomaticoHotel(String nomeHotel, CategoriaHotel categoria, String localidade, boolean transfer, double precoPorQuarto) {
        this.nomeHotel = nomeHotel;
        this.categoria = categoria;
        this.localidade = localidade;
        this.transfer = transfer;
        this.precoPorQuarto = precoPorQuarto;
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

    public boolean getTransfer() {
        return transfer;
    }

    public double getPrecoPorQuarto() {
        return precoPorQuarto;
    }
}
