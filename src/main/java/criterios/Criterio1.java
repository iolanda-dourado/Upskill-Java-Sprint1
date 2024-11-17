package criterios;

import entidades.Cliente;

import java.util.Comparator;

/**
 * Implementa um critério de comparação entre objetos do tipo {@link Cliente}.
 * O critério considera inicialmente a data de nascimento dos clientes, sendo ordenados em ordem decrescente.
 * Em caso de empate, é considerado o número de reservas concretizadas, em ordem decrescente.
 * Por fim, se ainda houver empate, a ordenação é feita com base no nome dos clientes em ordem alfabética.
 *
 * @author Iolanda Dourado e Marianna Ramos
 */

public class Criterio1 implements Comparator<Cliente> {

    /**
     * Compara dois clientes com base no critério descrito.
     *
     * @param c1 Primeiro cliente a ser comparado.
     * @param c2 Segundo cliente a ser comparado.
     * @return Um valor positivo se c1 for maior que c2, um valor negativo se c1 for menor que c2,
     * e zero se forem iguais de acordo com os critérios definidos.
     */
    public int compare(Cliente c1, Cliente c2) {
        if (c1.getDataNascimento().isMaior(c2.getDataNascimento())) {
            return 1;
        } else if (c2.getDataNascimento().isMaior(c1.getDataNascimento())) {
            return -1;
        } else {
            if (c1.getNumReservasConcretizadas() > c2.getNumReservasConcretizadas()) {
                return -1;
            } else if (c1.getNumReservasConcretizadas() < c2.getNumReservasConcretizadas()) {
                return 1;
            } else {
                return (c1.getNomeCliente()).compareTo(c2.getNomeCliente());
            }
        }
    }
}
