import entidades.*;
import enums.CategoriaHotel;
import enums.CompanhiaAerea;
import enums.Genero;
import utilidades.Data;
import utilidades.InteracaoEmpresaUtilizador;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Empresa empresa = new Empresa("Travel Plus", "Avenida Brasil, 2024", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        InteracaoEmpresaUtilizador interacao = new InteracaoEmpresaUtilizador(empresa);

        interacao.gerenciarResposta();
        empresa.listarReservas();

    }
}