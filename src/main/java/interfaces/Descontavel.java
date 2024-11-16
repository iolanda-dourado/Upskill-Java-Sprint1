package interfaces;

import utilidades.Data;

/**
 * Interface que define métodos para aplicar e verificar descontos.
 * Contém constantes que representam intervalos de tempo para promoções.
 * Implementações desta interface devem fornecer lógica para formatar datas e verificar promoções.
 */
public interface Descontavel {

    /**
     * Constante que representa o início do primeiro intervalo de tempo para promoções.
     */
    int INICIO_TEMP1 = 115;

    /**
     * Constante que representa o final do primeiro intervalo de tempo para promoções.
     */
    int FINAL_TEMP1 = 315;

    /**
     * Constante que representa o início do segundo intervalo de tempo para promoções.
     */
    int INICIO_TEMP2 = 515;

    /**
     * Constante que representa o final do segundo intervalo de tempo para promoções.
     */
    int FINAL_TEMP2 = 615;

    /**
     * Constante que representa o início do terceiro intervalo de tempo para promoções.
     */
    int INICIO_TEMP3 = 915;

    /**
     * Constante que representa o final do terceiro intervalo de tempo para promoções.
     */
    int FINAL_TEMP3 = 1130;

    /**
     * Método responsável por formatar a data de acordo com um critério específico.
     *
     * @param data A data a ser formatada.
     * @return Um valor inteiro representando a data formatada.
     */
    int formatarData(Data data);

    /**
     * Método responsável por verificar se o valor passado corresponde a um período promocional.
     *
     * @param a O valor que representa um intervalo de tempo.
     * @return {@code true} se o valor corresponder a um intervalo promocional, {@code false} caso contrário.
     */
    boolean isPromocao(int a);
}
