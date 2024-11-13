package interfaces;

import utilidades.Data;

public interface Descontavel {
    String INICIO_TEMP1 = "0115";
    String FINAL_TEMP1= "0315";
    String INICIO_TEMP2 = "0515";
    String FINAL_TEMP2= "0615";
    String INICIO_TEMP3 = "0915";
    String FINAL_TEMP3= "1130";

    int formatarData(Data data);
    boolean isPromocao(int a, Data umaData);
}