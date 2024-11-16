package utilidades;

import entidades.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InteracaoEmpresaUtilizador {
    private Scanner teclado = new Scanner(System.in);
    private GeradorAutomatico gerador = new GeradorAutomatico();
    private Empresa empresa;

    public InteracaoEmpresaUtilizador(Empresa empresa) {
        this.empresa = empresa;
    }

    public void gerenciarResposta() {
        boolean invalido;
        int resposta = 0;

        gerador.gerarReservas(empresa);

        do {
            try {
                mostrarMenu();
                resposta = obterOpcao("Insira a opção desejada: ", 1, 15);
                invalido = false;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Insira um número.");
                teclado.nextLine();
                invalido = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                invalido = true;
            }
        } while (invalido);

        switch (resposta) {
            case 1:
                carregarNovaReservaHotel();
                break;
            case 2:
//                carregarNovaReservaHV();
                break;
            default:
                System.out.println("Opção não implementada.");
                break;
        }
    }

    public void carregarNovaReservaHotel() {
        Data dataAtual = Data.dataAtual();
        Hotel hotel;
        Cliente cliente;

        try {
            int qntPessoas = obterOpcao("Insira a quantidade de pessoas: ", 1, Integer.MAX_VALUE);

            System.out.println(empresa.listarClientes());
            String codigoCliente = obterString("Insira o código do cliente pretendido: ");
            cliente = obterCliente(codigoCliente);

            System.out.println(empresa.listarHoteis());
            String codigoHotel = obterString("Insira o código do hotel pretendido: ");
            hotel = obterHotel(codigoHotel);

            Data dataChegada = obterData("Insira a data de chegada ao hotel (YYYY-MM-DD): ");

            int numNoites = obterOpcao("Insira o número de noites de estadia: ", 1, 365);

            ReservaHotel resHotel = new ReservaHotel(dataAtual, qntPessoas, cliente, hotel, dataChegada, numNoites);
            empresa.adicionarReserva(resHotel);
            System.out.println("Reserva de hotel criada com sucesso!");

        } catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public Cliente obterCliente(String codigo) {
        Cliente cliente = null;
        for (Cliente c : empresa.getListaClientes()) {
            if (c.getCodigoCliente().equalsIgnoreCase(codigo)) {
                cliente = c;
            }
        }
        if (cliente == null) {
            throw new IllegalArgumentException("* Cliente não encontrado.");
        }
        return cliente;
    }

    public Hotel obterHotel(String codigo) {
        Hotel hotel = null;
        for (Hotel h : empresa.getListaHoteis()) {
            if (h.getCodigoHotel().equalsIgnoreCase(codigo)) {
                hotel = h;
            }
        }
        if (hotel == null) {
            throw new IllegalArgumentException("* Hotel não encontrado.");
        }
        return hotel;
    }

    public Data obterData(String mensagem) {
        System.out.println(mensagem);
        String input = teclado.nextLine();
        try {
            String[] partes = input.split("/");
            int ano = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int dia = Integer.parseInt(partes[2]);
            return new Data(ano, mes, dia);
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro: Formato de data inválido. Use AAAA/MM/DD.");
        }
    }

    public int obterOpcao(String mensagem, int min, int max) {
        System.out.println(mensagem);
        String entrada = teclado.nextLine();

        int numero;
        try {
            numero = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Entrada inválida. Insira apenas números.");
        }

        if (numero < min || numero > max) {
            throw new IllegalArgumentException("Erro: Opção fora do intervalo permitido.");
        }
        return numero;
    }

    public String obterString(String mensagem) {
        System.out.println(mensagem);
        return teclado.nextLine().trim();
    }

    public void mostrarMenu() {
        System.out.println("------------------ MENU ------------------");
        System.out.println("1 - Carregar nova reserva de hotel");
        System.out.println("2 - Carregar nova reserva de hotel com voo de ida");
        System.out.println("3 - Carregar nova reserva de hotel com voo de ida e volta");
        System.out.println("4 - Carregar nova reserva de voo de ida");
        System.out.println("5 - Carregar nova reserva de voo de ida e volta");
        System.out.println("6 - Listar aeroportos");
        System.out.println("7 - Listar hotéis");
        System.out.println("8 - Listar voos");
        System.out.println("9 - Listar clientes");
        System.out.println("10 - Listar reservas de hotel");
        System.out.println("------------------------------------------");
    }
}