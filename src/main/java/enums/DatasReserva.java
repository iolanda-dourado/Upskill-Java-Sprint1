package enums;

import utilidades.Data;

/**
 * Enum {@code DatasReserva} representa as datas de reserva disponíveis.
 * Cada valor no enum corresponde a uma data específica de reserva.
 */
public enum DatasReserva {

    /** Representa a data de reserva para 23 de novembro de 2024. */
    DATA1(new Data(2024, 11, 23)),

    /** Representa a data de reserva para 24 de novembro de 2024. */
    DATA2(new Data(2024, 11, 24)),

    /** Representa a data de reserva para 25 de novembro de 2024. */
    DATA3(new Data(2024, 11, 25)),

    /** Representa a data de reserva para 26 de novembro de 2024. */
    DATA4(new Data(2024, 11, 26)),

    /** Representa a data de reserva para 27 de novembro de 2024. */
    DATA5(new Data(2024, 11, 27)),

    /** Representa a data de reserva para 28 de novembro de 2024. */
    DATA6(new Data(2024, 11, 28));

    /** A data associada ao valor de reserva. */
    private final Data data;

    /**
     * Constrói uma nova instância de {@code DatasReserva} com a data fornecida.
     *
     * @param data A data associada à reserva.
     */
    DatasReserva(Data data) {
        this.data = data;
    }

    /**
     * Retorna a data associada ao valor de reserva.
     *
     * @return A data da reserva.
     */
    public Data getData() {
        return data;
    }
}
