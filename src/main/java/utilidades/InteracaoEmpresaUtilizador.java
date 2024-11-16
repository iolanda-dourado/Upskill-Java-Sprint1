package utilidades;

import entidades.*;
import enums.CategoriaHotel;
import enums.Genero;
import excecoes.DiaInvalidoException;
import excecoes.FormatoInvalidoException;
import excecoes.MesInvalidoException;
import excecoes.NifInvalidoException;
import serializacao.FicheiroEmpresa;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InteracaoEmpresaUtilizador {
    private Scanner teclado = new Scanner(System.in);
    private GeradorAutomatico gerador = new GeradorAutomatico();
    private Empresa empresa;

    private static final int QNT_MIN = 1;
    private static final int QNT_MAX_PESSOAS = 50;
    private static final int QNT_MAX = 400;

    public InteracaoEmpresaUtilizador(Empresa empresa) {
        this.empresa = empresa;
    }

    public void gerenciarResposta() {
        boolean invalido;
        int resposta = 0;

        gerador.gerarReservas(empresa);

        do {
            do {
                try {
                    mostrarMenu();
                    resposta = obterOpcao("Insira a opção desejada: ", QNT_MIN, 15);
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
                    gerenciarReservas();
                    break;
                case 2:
                    System.out.println(listarHoteisPorCategoria());
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
                case 6:
                    break;
                case 0:
                    perguntaFinal();
                    break;
                default:
                    System.out.println("Opção não implementada.");
                    break;
            }
        } while (resposta != 0);
    }

    public void gerenciarReservas() {
        int opcao = obterOpcao("""
                Insira o tipo de reserva a ser criada:
                1 - Reserva Hotel
                2 - Reserva Hotel e Voo
                3 - Reserva Hotel e Voo Ida e Volta
                4 - Reserva Voo
                5 - Reserva Voo Ida e Volta
                6 - Eliminar reserva
                7 - Listar todas as reservas""", 1, 7);

        Data dataAtual = Data.dataAtual();
        Cliente cliente = new Cliente();
        int qntPessoas = 0;
        try {
            System.out.println(empresa.listarClientes());
            String codigoCliente = obterString("Insira o código do cliente pretendido: ");
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
            case 6:
                eliminarReserva();
                break;
            case 7:
                empresa.listarReservas();
                break;
        }
    }


    public void carregarNovaReservaHotel(Data dataAtual, int qntPessoas, Cliente cliente) {
        Data dataChegada;

        try {
            System.out.println(empresa.listarHoteis());
            String codigoHotel = obterString("Insira o código do hotel pretendido: ");
            Hotel hotel = obterHotel(codigoHotel);

            dataChegada = obterData("Insira a data de chegada ao hotel (YYYY/MM/DD): ");

            int numNoites = obterOpcao("Insira o número de noites de estadia: ", QNT_MIN, QNT_MAX);

            ReservaHotel reservaHotel = new ReservaHotel(dataAtual, qntPessoas, cliente, hotel, dataChegada, numNoites);
            empresa.adicionarReserva(reservaHotel);
            System.out.println("Reserva de hotel com voo de ida e volta criada com sucesso!");

        } catch (IllegalArgumentException | InputMismatchException | FormatoInvalidoException e) {
            System.out.println(e.getMessage());
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

            int numNoites = obterOpcao("Insira o número de noites de estadia: ", QNT_MIN, QNT_MAX);

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

            int numNoites = obterOpcao("Insira o número de noites de estadia: ", QNT_MIN, QNT_MAX);

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

    public String listarHoteisPorCategoria() {
        CategoriaHotel categoria = obterCategoriaHotel();
        StringBuilder strB = new StringBuilder();
        boolean respostaInvalida = true;

        do {
            try {
                for (Hotel hotel : empresa.getListaHoteis()) {
                    if (hotel.getCategoria().equals(categoria)) {
                        strB.append(hotel);
                        strB.append("\n");
                    }
                }
                respostaInvalida = false;
            } catch (IllegalArgumentException e) {
                System.out.printf(e.getMessage());
            }
        } while (respostaInvalida);


        return strB.toString();
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
                cliente.setPercentagemDesconto(percDesconto);
                descontoInvalido = false;
            } catch (IllegalArgumentException e) {
                System.out.printf(e.getMessage());
            }
        } while (descontoInvalido);

        empresa.adicionarCliente(cliente);
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
            resposta = obterOpcao("O hotel possui serviço de transfer? 1- SIM / 2- NÃO", 1, 2);
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
        System.out.printf("Custo total das reservas de hotel: %.2f", custoRHotel);
        System.out.printf("Custo total das reservas de voo ida e volta: %.2f", custoRVooIV);
        System.out.printf("Custo total dos dois tipos de reservas: %.2f", custoRHotel + custoRVooIV);
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
            throw new IllegalArgumentException("* Hotel não encontrado.");
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
            throw new IllegalArgumentException("* Hotel não encontrado.");
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
        System.out.println("De seguida, escolha a categoria do hotel:");
        String categ = obterString("- Uma Estrela\n- Duas Estrelas\n- Três Estrelas\n- Quatro Estrelas\n- Cinco estrelas\nInsira sua opção:").toUpperCase().trim();
        String categReplace = categ.replace("", "_").replace("Ê", "E");

        for (CategoriaHotel categoria : CategoriaHotel.values()) {
            if (categoria.name().equals(categReplace)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("A categoria inserida está incorreta.");
    }

    public Genero obterGenero(String mensagem) {
        String categ = obterString(mensagem).toUpperCase().trim();

        for (Genero genero : Genero.values()) {
            if (genero.name().equals(categ)) {
                return genero;
            }
        }
        throw new IllegalArgumentException("O gênero inserido está incorreto.");
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

    public void perguntaFinal() {
        int resposta = obterOpcao("Deseja salvar as informações da empresa em um ficheiro binário? 1- SIM / 2- NÃO", 1, 2);
        teclado.nextLine();

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
            System.out.println("Ficheiro binário criado com sucesso!%n");
        } else {
            System.out.println("Falha na criação do ficheiro binário%n");
        }
    }

    public void mostrarMenu() {
        System.out.println("------------------ MENU ------------------");
        System.out.println("1 - Gerenciar reservas");
        System.out.println("2 - Listar hotéis por categoria");
        System.out.println("3 - Inserir novo cliente");
        System.out.println("4 - Inserir novo hotel");
        System.out.println("5 - Visualizar custo total das reservas de hotel e voos de ida e regresso");
        System.out.println("0 - Sair do programa");
        System.out.println("------------------------------------------");
    }
}