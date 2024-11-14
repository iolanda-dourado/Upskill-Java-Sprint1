package criterios;

import entidades.Cliente;

import java.util.Comparator;

public class Criterio1 implements Comparator<Cliente> {

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
