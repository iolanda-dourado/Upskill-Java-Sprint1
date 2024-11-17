package criterios;

import interfaces.Identificavel;
import java.util.Comparator;

/**
 * Classe que implementa o critério de comparação para objetos que implementam a interface {@link Identificavel}.
 * A comparação é feita com base no identificador gerado para cada objeto. Se os identificadores forem numéricos,
 * a comparação será feita numericamente. Caso contrário, a comparação será feita lexicograficamente.
 */
public class Criterio3_CodigoID implements Comparator<Identificavel> {
    /**
     * @param identificavel1 o primeiro objeto a ser comparado
     * @param identificavel2 o segundo objeto a ser comparado
     * @return um valor negativo, zero ou positivo, dependendo se o primeiro identificador é menor,
     *         igual ou maior que o segundo identificador.
     *         A comparação numérica é feita se os identificadores forem inteiros,
     *         ou uma comparação lexicográfica se os identificadores não forem inteiros.
     */
    @Override
    public int compare(Identificavel identificavel1, Identificavel identificavel2) {
        String id1 = identificavel1.gerarIdentificador();
        String id2 = identificavel2.gerarIdentificador();

        try {
            int num1 = Integer.parseInt(id1);
            int num2 = Integer.parseInt(id2);
            return Integer.compare(num1, num2);
        } catch (NumberFormatException e) {
            return id1.compareTo(id2);
        }
    }
}