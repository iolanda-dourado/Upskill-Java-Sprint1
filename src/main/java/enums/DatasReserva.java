package enums;

import utilidades.Data;

public enum DatasReserva {
    DATA1(new Data(2024, 11, 23)),
    DATA2(new Data(2024, 11, 24)),
    DATA3(new Data(2024, 11, 25)),
    DATA4(new Data(2024, 11, 26)),
    DATA5(new Data(2024, 11, 27)),
    DATA6(new Data(2024, 11, 28)),
    DATA7(new Data(2024, 11, 29)),
    DATA8(new Data(2024, 11, 30)),
    DATA9(new Data(2024, 12, 1)),
    DATA10(new Data(2024, 12, 2)),
    DATA11(new Data(2024, 12, 3)),
    DATA12(new Data(2024, 12, 4)),
    DATA13(new Data(2024, 12, 5));

    private final Data data;

    DatasReserva(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }
}
