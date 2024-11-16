package utilidades;

import entidades.*;
import enums.CategoriaHotel;
import enums.Genero;
import excecoes.DiaInvalidoException;
import excecoes.FormatoInvalidoException;
import excecoes.MesInvalidoException;
import excecoes.NifInvalidoException;
import serializacao.FicheiroEmpresa;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InteracaoEmpresaUtilizador implements Serializable {
    private Scanner teclado = new Scanner(System.in);
    private GeradorAutomatico gerador = new GeradorAutomatico();
    private Empresa empresa;

    private static final int QNT_MIN = 1;
    private static final int QNT_MAX_PESSOAS = 50;
    private static final int QNT_MAX = 400;
    private static final int QNT_MIN_MENU = 0;
    private static final int QNT_MAX_MENU = 10;
    private static final int QNT_MAX_MENU_RESERVAS = 7;


    public InteracaoEmpresaUtilizador(Empresa empresa) {
        this.empresa = empresa;
    }

    public void gerenciarResposta() {
        boolean invalido = true;
        int resposta = 0;

        gerador.gerarReservas(empresa);

        do {
            while (invalido) {
                try {
                    mostrarMenu();
                    resposta = obterOpcao("Insira a opção desejada:", QNT_MIN_MENU, QNT_MAX_MENU);
                    invalido = false;
                } catch (InputMismatchException | IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            switch (resposta) {
                case 1:
                    gerenciarReservas();
                    break;
                case 2:
                    listarHoteisPorCategoria();
                    break;
                case 3:
                    inserirNovoCliente();
                    break;
                case 4:
                    inserirNovoHotel();
                    break;
                case 5:
                    mostrarCustoTotalReservasHotelEVooIdaVolta();
                    break;
                case 0:
                    perguntaFinal();
                    break;
                default:
                    System.out.println("Opção não implementada.");
            }

            invalido = true;

        } while (resposta != 0);
    }


    public void gerenciarReservas() {
        int opcao = -1;
        boolean invalido = true;

        while (invalido) {
            try {
                opcao = obterOpcao(menuReservas(), QNT_MIN_MENU, QNT_MAX_MENU_RESERVAS);
                invalido = false;
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }

        if (opcao == 0) {
            System.out.println("Voltando ao menu principal...");
            return;
        }

        Data dataAtual = Data.dataAtual();
        Cliente cliente = new Cliente();
        int qntPessoas = 0;

        if (opcao == 6) {
            eliminarReserva();
        } else if (opcao == 7) {
            System.out.println(empresa.listarReservas());
        } else {
            try {
                System.out.println(empresa.listarClientes());
                String codigoCliente = obterString("Insira o código do cliente pretendido:");
                cliente = obterCliente(codigoCliente);
                qntPessoas = obterOpcao("Insira a quantidade de pessoas:", QNT_MIN, QNT_MAX_PESSOAS);
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }

            switch (opcao) {
                case 1:
                    carregarNovaReservaHotel(dataAtual, qntPessoas, cliente);
                    break;
                case 2:
                    carregarNovaReservaHotelVoo(dataAtual, qntPessoas, cliente);
                    break;
                case 3:
                    carregarNovaReservaHotelVooIdaVolta(dataAtual, qntPessoas, cliente);
                    break;
                case 4:
                    carregarNovaReservaVoo(dataAtual, qntPessoas, cliente);
                    break;
                case 5:
                    carregarNovaReservaVooIdaVolta(dataAtual, qntPessoas, cliente);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    public void carregarNovaReservaHotel(Data dataAtual, int qntPessoas, Cliente cliente) {
        Data dataChegada;
        boolean invalido = true;

        while (invalido) {
            try {
                System.out.println(empresa.listarHoteis());
                String codigoHotel = obterString("Insira o código do hotel pretendido:");
                Hotel hotel = obterHotel(codigoHotel);

                dataChegada = obterData("Insira a data de chegada ao hotel (YYYY/MM/DD):");

                int numNoites = obterOpcao("Insira o número de noites de estadia:", QNT_MIN, QNT_MAX);

                ReservaHotel reservaHotel = new ReservaHotel(dataAtual, qntPessoas, cliente, hotel, dataChegada, numNoites);
                empresa.adicionarReserva(reservaHotel);
                System.out.println("Reserva de hotel criada com sucesso!");
                invalido = false;
            } catch (IllegalArgumentException | InputMismatchException | FormatoInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void carregarNovaReservaHotelVoo(Data dataAtual, int qntPessoas, Cliente cliente) {
        Hotel hotel;
        Voo voo;

        try {
            System.out.println(empresa.listarHoteis());
            String codigoHotel = obterString("Insira o código do hotel pretendido: ");
            hotel = obterHotel(codigoHotel);

            Data dataChegada = obterData("Insira a data de chegada ao hotel (YYYY/MM/DD): ");

            int numNoites = obterOpcao("Insira o número de noites de estadia:", QNT_MIN, QNT_MAX);

            System.out.println(empresa.listarVoos());
            String codigoVoo = obterString("Insira o código do voo pretendido: ");
            voo = obterVoo(codigoVoo);

            ReservaHotelVoo resHotelVoo = new ReservaHotelVoo(dataAtual, qntPessoas, cliente, hotel, dataChegada, numNoites, voo);
            empresa.adicionarReserva(resHotelVoo);
            System.out.println("Reserva de hotel com voo criada com sucesso!");

        } catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public void carregarNovaReservaHotelVooIdaVolta(Data dataAtual, int qntPessoas, Cliente cliente) {
        Hotel hotel;
        Voo voo;
        Voo vooVolta;

        try {
            System.out.println(empresa.listarHoteis());
            String codigoHotel = obterString("Insira o código do hotel pretendido: ");
            hotel = obterHotel(codigoHotel);

            Data dataChegada = obterData("Insira a data de chegada ao hotel (YYYY/MM/DD): ");

            int numNoites = obterOpcao("Insira o número de noites de estadia:", QNT_MIN, QNT_MAX);

            System.out.println(empresa.listarVoos());
            String codigoVoo = obterString("Insira o código do voo pretendido: ");
            voo = obterVoo(codigoVoo);

            System.out.println(empresa.listarVoos());
            String codigoVooVolta = obterString("Insira o código do voo de volta pretendido: ");
            vooVolta = obterVoo(codigoVooVolta);

            ReservaHotelVooIdaVolta resHotelVooIdaVolta = new ReservaHotelVooIdaVolta(dataAtual, qntPessoas, cliente, hotel, dataChegada, numNoites, voo, vooVolta);
            empresa.adicionarReserva(resHotelVooIdaVolta);
            System.out.println("Reserva de hotel com voo de ida e volta criada com sucesso!");

        } catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public void carregarNovaReservaVoo(Data dataAtual, int qntPessoas, Cliente cliente) {
        Voo voo;

        try {
            System.out.println(empresa.listarVoos());
            String codigoVoo = obterString("Insira o código do voo pretendido: ");
            voo = obterVoo(codigoVoo);

            ReservaVoo resVoo = new ReservaVoo(dataAtual, qntPessoas, cliente, voo);
            empresa.adicionarReserva(resVoo);
            System.out.println("Reserva de voo criada com sucesso!");

        } catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public void carregarNovaReservaVooIdaVolta(Data dataAtual, int qntPessoas, Cliente cliente) {
        Voo voo;
        Voo vooVolta;

        try {
            System.out.println(empresa.listarVoos());
            String codigoVoo = obterString("Insira o código do voo pretendido: ");
            voo = obterVoo(codigoVoo);

            System.out.println(empresa.listarVoos());
            String codigoVooVolta = obterString("Insira o código do voo de volta pretendido: ");
            vooVolta = obterVoo(codigoVooVolta);

            ReservaVooIdaVolta resVooIdaVolta = new ReservaVooIdaVolta(dataAtual, qntPessoas, cliente, voo, vooVolta);
            empresa.adicionarReserva(resVooIdaVolta);
            System.out.println("Reserva de voo de ida e volta criada com sucesso!");

        } catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean eliminarReserva() {
        String codigoTemp;
        System.out.println("Digite o código da reserva que deseja eliminar: ");
        codigoTemp = teclado.nextLine().trim().toUpperCase();
        if (empresa.eliminarReserva(codigoTemp)) {
            System.out.printf("Reserva %s removida com sucesso!", codigoTemp);
            return true;
        } else {
            System.out.println("Falha ao remover! Reserva não encontrada.");
        }
        return false;
    }

    public void listarHoteisPorCategoria() {
        CategoriaHotel categoria = obterCategoriaHotel();
        StringBuilder strB = new StringBuilder();

        for (Hotel hotel : empresa.getListaHoteis()) {
            if (hotel.getCategoria().equals(categoria)) {
                strB.append(hotel);
                strB.append("\n");
            }
        }

        if (strB.isEmpty()) {
            strB.append("Nenhum hotel encontrado para a categoria: ").append(categoria.toString());
        }

        System.out.println(strB.toString());
    }

    private void inserirNovoCliente() {
        Cliente cliente = new Cliente();

        boolean nomeInvalido = true;
        do {
            try {
                String nome = obterString("Insira o nome do cliente: ");
                cliente.setNomeCliente(nome);
                nomeInvalido = false;
            } catch (IllegalArgumentException e) {
                System.out.printf("Erro: %s. Tente novamente.%n", e.getMessage());
            }
        } while (nomeInvalido);

        boolean dataInvalida = true;
        do {
            try {
                Data dataNasc = obterData("Insira a data de nascimento do cliente:");
                cliente.setDataNascimento(dataNasc);
                dataInvalida = false;
            } catch (DiaInvalidoException | MesInvalidoException | FormatoInvalidoException e) {
                System.out.println(e.getMessage());
            }
        } while (dataInvalida);

        boolean generoInvalido = true;
        do {
            try {
                Genero genero = obterGenero("Insira o gênero do cliente (Masculino/Feminino/Outro):");
                cliente.setGenero(genero);
                generoInvalido = false;
            } catch (IllegalArgumentException e) {
                System.out.printf(e.getMessage());
            }
        } while (generoInvalido);

        boolean nifInvalido = true;
        do {
            try {
                int nif = obterNif("Insira o NIF do cliente:");
                cliente.setNif(nif);
                nifInvalido = false;
            } catch (NifInvalidoException e) {
                System.out.printf(e.getMessage());
            }
        } while (nifInvalido);

        String numPassaporte = obterString("Insira o número do passaporte:");
        cliente.setNumPassaporte(numPassaporte);

        String email = obterString("Insira o email do cliente:");
        cliente.setEmail(email);

        boolean descontoInvalido = true;
        do {
            try {
                System.out.println("Insira a percentagem de desconto acordada com a empresa:");
                double percDesconto = teclado.nextDouble();
                teclado.nextLine();
                cliente.setPercentagemDesconto(percDesconto);
                descontoInvalido = false;
            } catch (IllegalArgumentException e) {
                System.out.printf(e.getMessage());
            }
        } while (descontoInvalido);

        empresa.adicionarCliente(cliente);
        System.out.println("Novo cliente adicionado com sucesso.");
    }

    private void inserirNovoHotel() {
        Hotel novoHotel = new Hotel();

        boolean nomeInvalido = true;
        do {
            try {
                String nome = obterString("Insira o nome do cliente: ");
                novoHotel.setNomeHotel(nome);
                nomeInvalido = false;
            } catch (IllegalArgumentException e) {
                System.out.printf("Erro: %s. Tente novamente.%n", e.getMessage());
            }
        } while (nomeInvalido);

        boolean categoriaInvalida = true;
        do {
            try {
                CategoriaHotel cat = obterCategoriaHotel();
                novoHotel.setCategoria(cat);
                categoriaInvalida = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (categoriaInvalida);

        boolean localidadeInvalida = true;
        do {
            try {
                System.out.println("Insira a localidade do Hotel: ");
                String local = teclado.nextLine();
                if (local.isEmpty()) {
                    throw new IllegalArgumentException("O hotel deve ter uma localidade!");
                }
                localidadeInvalida = false;
            } catch (IllegalArgumentException e) {
                System.out.printf("Erro: %s. Tente novamente.%n", e.getMessage());
            }
        } while (localidadeInvalida);

        boolean hasTransfer = false;
        boolean selecaoInvalida = true;
        int resposta;
        do {
            resposta = obterOpcao("O hotel possui serviço de transfer? (1 - SIM/2 - NÃO):", 1, 2);
            if (resposta == 1) {
                hasTransfer = true;
                novoHotel.setTransfer(hasTransfer);
                selecaoInvalida = false;
            } else if (resposta == 2) {
                novoHotel.setTransfer(hasTransfer);
                selecaoInvalida = false;
            }
        } while (selecaoInvalida);


        boolean precoInvalido = true;
        do {
            try {
                System.out.println("Insira o preço do quarto nesse hotel: ");
                double precoQuarto = teclado.nextDouble();
                novoHotel.setPrecoPorQuarto(precoQuarto);
                precoInvalido = false;
            } catch (IllegalArgumentException e) {
                System.out.printf(e.getMessage());
            }
        } while (precoInvalido);

        empresa.adicionarHotel(novoHotel);
    }

    private void mostrarCustoTotalReservasHotelEVooIdaVolta() {
        double custoRHotel = empresa.retornarCustoTodasReservasHoteis();
        double custoRVooIV = empresa.retornarCustoTodasReservasVooIV();
        System.out.printf("Custo total das reservas de hotel: %.2f\n", custoRHotel);
        System.out.printf("Custo total das reservas de voo ida e volta: %.2f\n", custoRVooIV);
        System.out.printf("Custo total dos dois tipos de reservas: %.2f\n", custoRHotel + custoRVooIV);
    }

    public Cliente obterCliente(String codigo) {
        Cliente cliente = null;
        for (Cliente c : empresa.getListaClientes()) {
            if (c.getCodigoCliente().equalsIgnoreCase(codigo)) {
                cliente = c;
            }
        }
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
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
            throw new IllegalArgumentException("Hotel não encontrado.");
        }
        return hotel;
    }

    public Voo obterVoo(String codigo) {
        Voo voo = null;
        for (Voo v : empresa.getListaVoos()) {
            if (v.getCodigoVoo().equalsIgnoreCase(codigo)) {
                voo = v;
            }
        }
        if (voo == null) {
            throw new IllegalArgumentException("Voo não encontrado.");
        }
        return voo;
    }

    public Data obterData(String mensagem) {
        System.out.println(mensagem);
        String entrada = teclado.nextLine();
        try {
            String[] partes = entrada.split("/");
            if (partes.length != 3) {
                throw new FormatoInvalidoException("Erro: Formato de data inválido. Use AAAA/MM/DD.");
            }

            int ano = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int dia = Integer.parseInt(partes[2]);
            return new Data(ano, mes, dia);
        } catch (Exception e) {
            throw new FormatoInvalidoException("Erro: Formato de data inválido. Use AAAA/MM/DD.");
        }
    }

    public CategoriaHotel obterCategoriaHotel() {
        System.out.println("Escolha a categoria do hotel:");
        String opcao = obterString("""
            - 1 - Uma Estrela
            - 2 - Duas Estrelas
            - 3 - Três Estrelas
            - 4 - Quatro Estrelas
            - 5 - Cinco Estrelas
            Insira o número da sua opção:
            """);

        switch (opcao) {
            case "1":
                return CategoriaHotel.UMA_ESTRELA;
            case "2":
                return CategoriaHotel.DUAS_ESTRELAS;
            case "3":
                return CategoriaHotel.TRES_ESTRELAS;
            case "4":
                return CategoriaHotel.QUATRO_ESTRELAS;
            case "5":
                return CategoriaHotel.CINCO_ESTRELAS;
            default:
                throw new IllegalArgumentException("Categoria inválida.");
        }
    }

    public Genero obterGenero(String mensagem) {
        String categ = obterString(mensagem).toUpperCase().trim();

        for (Genero genero : Genero.values()) {
            if (genero.name().equals(categ)) {
                return genero;
            }
        }
        throw new IllegalArgumentException("O gênero inserido está incorreto.\n");
    }

    public int obterNif(String mensagem) {
        System.out.println(mensagem);
        int nif = teclado.nextInt();
        teclado.nextLine();

        if (nif < 100000000 || nif > 999999999) {
            throw new NifInvalidoException();
        }
        return nif;
    }

    public int obterOpcao(String mensagem, int min, int max) {
        System.out.println(mensagem);
        String entrada = teclado.nextLine().trim();

        int numero;
        try {
            numero = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Entrada inválida.");
        }

        if (numero < min || numero > max || entrada.isEmpty()) {
            throw new IllegalArgumentException("Erro: Opção fora do intervalo permitido.");
        }
        return numero;
    }

    public String obterString(String mensagem) {
        System.out.println(mensagem);
        return teclado.nextLine().trim();
    }

    public void perguntaFinal() {
        int resposta = obterOpcao("Deseja salvar as informações da empresa em um ficheiro binário? (1- SIM / 2- NÃO):", 1, 2);

        if (resposta == 1) {
            serializarEmpresa();
        } else {
            System.out.println("Logout concluído!");
        }
    }

    public void serializarEmpresa() {
        FicheiroEmpresa ficheiro = new FicheiroEmpresa();
        String nome = "";
        do {
            System.out.println("Digite o nome do ficheiro (Ex:\"empresa.bin\"): ");
            nome = teclado.nextLine();
        } while (nome.isEmpty());

        if (ficheiro.serializar(nome, empresa)) {
            System.out.println("Ficheiro binário criado com sucesso!\n");
        } else {
            System.out.println("Falha na criação do ficheiro binário\n");
        }
    }

    public void mostrarMenu() {
        System.out.println("""
                
                ------------------ MENU ------------------
                1 - Gerenciar reservas
                2 - Listar hotéis por categoria
                3 - Inserir novo cliente
                4 - Inserir novo hotel
                5 - Visualizar custo total das reservas de hotel e voos de ida e regresso
                0 - Sair do programa
                ------------------------------------------
                """);
    }

    public String menuReservas() {
        return ("""
                -----------------RESERVAS-----------------
                1 - Criar Reserva de Hotel
                2 - Criar Reserva de Hotel e Voo
                3 - Criar Reserva Hotel e Voo Ida e Volta
                4 - Criar Reserva Voo
                5 - Criar Reserva Voo Ida e Volta
                6 - Eliminar reserva
                7 - Listar todas as reservas
                0 - Voltar ao menu inicial
                ------------------------------------------
                Insira a opção desejada:""");
    }
}