package interfaces;

import utilidades.Data;

public interface Descontavel {
    int INICIO_TEMP1 = 115;
    int FINAL_TEMP1= 315;
    int INICIO_TEMP2 = 515;
    int FINAL_TEMP2= 615;
    int INICIO_TEMP3 = 915;
    int FINAL_TEMP3= 1130;

    int formatarData(Data data);
    boolean isPromocao(int a);
}