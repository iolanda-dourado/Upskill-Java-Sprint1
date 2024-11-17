import entidades.*;

import utilidades.InteracaoEmpresaUtilizador;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Empresa empresa = new Empresa("Travel Plus", "Avenida Brasil, 2024", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        InteracaoEmpresaUtilizador interacao = new InteracaoEmpresaUtilizador(empresa);

        interacao.gerenciarResposta();
        empresa.listarReservas();

    }
}