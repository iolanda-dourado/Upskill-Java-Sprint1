package utilidades;

import entidades.*;
import enums.CategoriaHotel;
import enums.Genero;
import excecoes.DiaInvalidoException;
import excecoes.FormatoInvalidoException;
import excecoes.MesInvalidoException;
import excecoes.NifInvalidoException;
import serializacao.FicheiroEmpresa;

import java.util.*;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe responsável por gerar interação entre utilizador e a empresa.
 * Esta classe permite realizar diversas operações, como gerenciamento de reservas,
 * manipulação de dados de clientes e visualização de informações.
 * <p>
 * A classe utiliza diversas constantes para controle de menus e limites de operações,
 * além de fazer uso de várias entidades e enums para enriquecer a funcionalidade.
 *
 * @author Iolanda Dourado e Marianna Ramos
 */

public class InteracaoEmpresaUtilizador implements Serializable {

    /**
     * Scanner para leitura de dados do utilizador.
     */
    private Scanner teclado = new Scanner(System.in);

    /**
     * Gerador automático utilizado para criar dados automaticamente.
     */
    private GeradorAutomatico gerador = new GeradorAutomatico();

    /**
     * Instância da empresa com a qual a interação é realizada.
     */
    private Empresa empresa;

    /**
     * Quantidade mínima de pessoas permitida.
     */
    private static final int QNT_MIN_PESSOAS = 1;

    /**
     * Quantidade máxima de pessoas permitida.
     */
    private static final int QNT_MAX_PESSOAS = 20;

    /**
     * Quantidade máxima de diárias permitida.
     */
    private static final int QNT_MAX_DIARIAS = 365;

    /**
     * Quantidade mínima de opções de menu.
     */
    private static final int QNT_MIN_MENU = 0;

    /**
     * Quantidade máxima de opções no menu principal.
     */
    private static final int QNT_MAX_MENU = 7;

    /**
     * Quantidade máxima de opções no menu de reservas.
     */
    private static final int QNT_MAX_MENU_RESERVAS = 8;

    /**
     * Quantidade máxima de opções no menu de visualização.
     */
    private static final int QNT_MAX_MENU_VISUALIZAR = 8;


    /**
     * Construtor da classe InteracaoEmpresaUtilizador.
     * Inicializa a interação associando-a a uma instância de Empresa.
     *
     * @param empresa a instância de Empresa que será usada nas interações.
     */
    public InteracaoEmpresaUtilizador(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * Gerencia as interações com o utilizador através de um menu principal.
     * Este método exibe opções ao utilizador, processa a escolha, e executa a
     * ação correspondente, como gerenciamento de reservas, inserção de novos
     * clientes ou hotéis, e listagem de informações.
     * <p>
     * O fluxo permanece em execução até que o utilizador escolha sair.
     */
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
                    mostrarCustoTotalReservas();
                    break;
                case 6:
                    System.out.println(empresa.listarHoteisComTransfer());
                    break;
                case 7:
                    listarClientesOrdenados();
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

    /**
     * Gerencia as operações relacionadas a reservas.
     * <p>
     * Este método apresenta um menu de opções para realizar diferentes ações, como:
     * <ul>
     *   <li>Adicionar reservas com diferentes tipos de serviços.</li>
     *   <li>Eliminar reservas existentes.</li>
     *   <li>Visualizar e concretizar reservas.</li>
     *   <li>Pesquisar reservas por ID.</li>
     * </ul>
     * O método solicita dados do utilizador, como cliente associado e número de pessoas,
     * e valida as entradas antes de executar as ações correspondentes.
     */
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
            eliminarReserva("Digite o código da reserva que deseja eliminar: ");
        } else if (opcao == 7) {
            gerenciarVisualizacoes();
        } else if (opcao == 8) {
            concretizarReserva("Digite o código da reserva que deseja concretizar: ");
        } else if (opcao == 9) {
            pesquisarReservaPorID("Digite o código da reserva que deseja encontrar: ");
        } else {
            boolean resInvalida = true;
            boolean res2Invalida = true;
            System.out.println(empresa.listarClientes());
            do {
                try {
                    String codigoCliente = obterString("Insira o código do cliente pretendido:");
                    cliente = obterCliente(codigoCliente);
                    resInvalida = false;
                } catch (IllegalArgumentException | InputMismatchException e) {
                    System.out.println(e.getMessage());
                }
            } while (resInvalida);
            do {
                try {
                    qntPessoas = obterOpcao("Insira a quantidade de pessoas:", QNT_MIN_PESSOAS, QNT_MAX_PESSOAS);
                    res2Invalida = false;
                } catch (IllegalArgumentException | InputMismatchException e) {
                    System.out.println("Quantidade de pessoas inválida! Digite um número acima de zero.");
                }
            } while (res2Invalida);

            switch (opcao) {
                case 1:
                    int tipo1 = 1;
                    carregarNovaReserva(dataAtual, qntPessoas, cliente, tipo1);
                    break;
                case 2:
                    int tipo2 = 2;
                    carregarNovaReserva(dataAtual, qntPessoas, cliente, tipo2);
                    break;
                case 3:
                    int tipo3 = 3;
                    carregarNovaReserva(dataAtual, qntPessoas, cliente, tipo3);
                    break;
                case 4:
                    int tipo4 = 4;
                    carregarNovaReserva(dataAtual, qntPessoas, cliente, tipo4);
                    break;
                case 5:
                    int tipo5 = 5;
                    carregarNovaReserva(dataAtual, qntPessoas, cliente, tipo5);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    /**
     * Método responsável por gerenciar a visualização de diferentes tipos de reservas na empresa.
     * O usuário pode escolher entre visualizar reservas de hotel, reservas de hotel com voo, reservas de voo,
     * ou consultar reservas por data ou custo. A interação é feita por meio de um menu com diferentes opções,
     * e o sistema exibe as informações solicitadas de acordo com a escolha do usuário.
     * <p>
     * Dependendo da opção selecionada, o método chama os métodos apropriados da classe {@code Empresa} para listar
     * as reservas, com filtros baseados no tipo de reserva ou critérios como custo e data.
     * <p>
     * O método valida a opção inserida pelo usuário e trata exceções de entradas inválidas.
     */
    public void gerenciarVisualizacoes() {
        int opcao1 = -1;
        boolean invalido = true;

        while (invalido) {
            try {
                opcao1 = obterOpcao(menuVisualizarReservas(), QNT_MIN_MENU, QNT_MAX_MENU_VISUALIZAR);
                invalido = false;
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }

        if (opcao1 == 0) {
            System.out.println("Voltando ao menu anterior...\n");
            gerenciarReservas();
            return;
        }

        switch (opcao1) {
            case 1:
                String tipo1 = "ReservaHotel";
                System.out.println("\n========== LISTA DE RESERVAS DE HOTEL  ==========\n");
                System.out.println(empresa.listarReservasPorTipo(tipo1));
                break;
            case 2:
                String tipo2 = "ReservaHotelVoo";
                System.out.println("\n========== LISTA DE RESERVAS DE HOTEL + VOO  ==========\n");
                System.out.println(empresa.listarReservasPorTipo(tipo2));
                break;
            case 3:
                String tipo3 = "ReservaHotelVooIdaVolta";
                System.out.println("\n========== LISTA DE RESERVAS DE HOTEL + VOOS (IDA E VOLTA)  ==========\n");
                System.out.println(empresa.listarReservasPorTipo(tipo3));
                break;
            case 4:
                String tipo4 = "ReservaVooIdaVolta";
                System.out.println("\n========== LISTA DE RESERVAS DE VOOS (IDA E VOLTA)  ==========\n");
                System.out.println(empresa.listarReservasPorTipo(tipo4));
                break;
            case 5:
                String tipo5 = "ReservaVoo";
                System.out.println("\n========== LISTA DE RESERVAS DE VOO (APENAS IDA)  ==========\n");
                System.out.println(empresa.listarReservasPorTipo(tipo5));
                break;
            case 6:
                System.out.println("\n========== LISTA DE RESERVAS POR ORDEM DECRESCENTE DE CUSTO  ==========\n");
                System.out.println(empresa.listarReservasOrdenadas(empresa.retornarReservasCustoDecrescente()));
                break;
            case 7:
                System.out.println("\n========== LISTA DE RESERVAS POR ORDEM CRESCENTE DE DATA  ==========\n");
                System.out.println(empresa.listarReservasOrdenadas(empresa.retornarReservasEfetuadasDataCrescente()));
                break;
            case 8:
                String codigoCliente = obterString("Insira o código do cliente pretendido: ");
                System.out.println(empresa.listarReservaCliente(codigoCliente));
                break;
        }
    }

    /**
     * Método responsável por realizar o carregamento de uma nova reserva para o cliente.
     * Dependendo do tipo de reserva (hotel, voo, ou ambos), o processo envolve a coleta de informações
     * sobre o hotel, a data de chegada, o número de noites, e os voos (caso o tipo de reserva inclua voo).
     * O método valida as entradas do usuário e adiciona a reserva à empresa, ou exibe uma mensagem de erro
     * caso a reserva já exista para o cliente.
     *
     * @param dataAtual  A data atual do sistema (data da reserva).
     * @param qntPessoas O número de pessoas para a reserva.
     * @param cliente    O cliente que está realizando a reserva.
     * @param tipo       O tipo de reserva:
     *                   1 - Somente hotel
     *                   2 - Hotel com voo
     *                   3 - Hotel com voo de ida e volta
     *                   4 - Somente voo
     *                   5 - Voo de ida e volta
     */
    public void carregarNovaReserva(Data dataAtual, int qntPessoas, Cliente cliente, int tipo) {
        Data dataChegada = null;
        Hotel hotel = null;
        int numNoites = 0;
        Voo voo = null;
        Voo vooVolta = null;

        boolean isValido = true;

        while (isValido) {
            if (tipo == 1 || tipo == 2 || tipo == 3) {
                boolean resInvalida = true;
                System.out.println(empresa.listarHoteis());
                do {
                    try {
                        String codigoHotel = obterString("Insira o código do hotel pretendido:");
                        hotel = obterHotel(codigoHotel);
                        resInvalida = false;

                    } catch (IllegalArgumentException | InputMismatchException | FormatoInvalidoException e) {
                        System.out.println(e.getMessage());
                    }
                } while (resInvalida);

                boolean resInvalida2 = true;
                do {
                    try {
                        dataChegada = obterData("Insira a data de chegada ao hotel (AAAA/MM/DD):");
                        resInvalida2 = false;

                    } catch (IllegalArgumentException | InputMismatchException | FormatoInvalidoException e) {
                        System.out.println(e.getMessage());
                    }
                } while (resInvalida2);

                boolean resInvalida3 = true;
                do {
                    try {
                        numNoites = obterOpcao("Insira o número de noites de estadia:", QNT_MIN_PESSOAS, QNT_MAX_DIARIAS);
                        resInvalida3 = false;

                    } catch (IllegalArgumentException | InputMismatchException | FormatoInvalidoException e) {
                        System.out.println(e.getMessage());
                    }
                } while (resInvalida3);
            }

            if (tipo == 1) {
                ReservaHotel reservaHotel = new ReservaHotel(dataAtual, qntPessoas, cliente, hotel, dataChegada, numNoites);

                if (empresa.adicionarReserva(reservaHotel)) {
                    System.out.println("Reserva de hotel criada com sucesso!");
                } else {
                    System.out.println("Falha ao criar reserva! Reserva idêntica já existe para esse cliente!");
                }
            }

            if (tipo != 1) {
                System.out.println(empresa.listarVoos());
                boolean resInvalida4 = true;
                do {
                    try {
                        String codigoVoo = obterString("Insira o código do voo pretendido: ");
                        voo = obterVoo(codigoVoo);
                        resInvalida4 = false;
                    } catch (IllegalArgumentException | InputMismatchException | FormatoInvalidoException e) {
                        System.out.println(e.getMessage());
                    }
                } while (resInvalida4);

                if (tipo == 2) {
                    ReservaHotelVoo resHotelVoo = new ReservaHotelVoo(dataAtual, qntPessoas, cliente, hotel, dataChegada, numNoites, voo);

                    if (empresa.adicionarReserva(resHotelVoo)) {
                        System.out.println("Reserva de hotel com voo criada com sucesso!");
                    } else {
                        System.out.println("Falha ao criar reserva! Reserva idêntica já existe para esse cliente!");
                    }
                }

                if (tipo == 4) {
                    ReservaVoo resVoo = new ReservaVoo(dataAtual, qntPessoas, cliente, voo);

                    if (empresa.adicionarReserva(resVoo)) {
                        System.out.println("Reserva de voo criada com sucesso!");
                    } else {
                        System.out.println("Falha ao criar reserva! Reserva idêntica já existe para esse cliente!");
                    }
                }

                if (tipo == 3 || tipo == 5) {
                    System.out.println(empresa.listarVoos());
                    boolean resInvalida5 = true;
                    do {
                        try {
                            String codigoVooVolta = obterString("Insira o código do voo de volta pretendido: ");
                            vooVolta = obterVoo(codigoVooVolta);
                            resInvalida5 = false;
                        } catch (IllegalArgumentException | InputMismatchException | FormatoInvalidoException e) {
                            System.out.println(e.getMessage());
                        }
                    } while (resInvalida5);

                    if (tipo == 3) {
                        ReservaHotelVooIdaVolta resHotelVooIdaVolta = new ReservaHotelVooIdaVolta(dataAtual, qntPessoas, cliente, hotel, dataChegada, numNoites, voo, vooVolta);

                        if (empresa.adicionarReserva(resHotelVooIdaVolta)) {
                            System.out.println("Reserva de hotel com voo de ida e volta criada com sucesso!");
                        } else {
                            System.out.println("Falha ao criar reserva! Reserva idêntica já existe para esse cliente!");
                        }
                    }

                    if (tipo == 5) {
                        ReservaVooIdaVolta resVooIdaVolta = new ReservaVooIdaVolta(dataAtual, qntPessoas, cliente, voo, vooVolta);

                        if (empresa.adicionarReserva(resVooIdaVolta)) {
                            System.out.println("Reserva de voo de ida e volta criada com sucesso!");
                        } else {
                            System.out.println("Falha ao criar reserva! Reserva idêntica já existe para esse cliente!");
                        }
                    }
                }
            }
            isValido = false;
        }
    }

    /**
     * Elimina uma reserva existente com base no código fornecido pelo usuário.
     * O método solicita ao usuário que insira o código da reserva que deseja remover.
     * Após a inserção, o código é passado para o método {@code empresa.eliminarReserva} da classe {@code Empresa}
     * para tentar remover a reserva.
     * O método exibe uma mensagem de sucesso caso a reserva seja removida com sucesso,
     * ou uma mensagem de falha caso a reserva não seja encontrada.
     *
     * @param mensagem A mensagem a ser exibida ao usuário antes de inserir o código da reserva.
     */
    public void eliminarReserva(String mensagem) {
        System.out.println(mensagem);
        String codigoTemp = teclado.nextLine().trim().toUpperCase();
        if (empresa.eliminarReserva(codigoTemp)) {
            System.out.printf("Reserva %s removida com sucesso!", codigoTemp);
        } else {
            System.out.println("Falha ao remover! Reserva não encontrada.");
        }
    }

    /**
     * Concretiza uma reserva com base no código fornecido pelo usuário.
     * O método solicita ao usuário que insira o código da reserva que deseja concretizar.
     * Após a inserção, o código é passado para o método {@code empresa.pesquisarReserva}, que retorna a reserva correspondente,
     * e, em seguida, tenta concretizar a reserva com o método {@code empresa.atualizarReservasConcretizadas}.
     * O método exibe uma mensagem de sucesso se a reserva for concretizada com êxito,
     * ou uma mensagem de falha caso a reserva não seja encontrada ou a concretização falhe.
     *
     * @param menssagem A mensagem a ser exibida ao usuário antes de inserir o código da reserva.
     */
    public void concretizarReserva(String menssagem) {
        System.out.println(menssagem);
        String codigoTemp = teclado.nextLine().trim().toUpperCase();

        if (empresa.atualizarReservasConcretizadas(empresa.pesquisarReserva(codigoTemp))) {
            System.out.printf("Reserva %s concretizada com sucesso!", codigoTemp);
        } else {
            System.out.println("Falha ao concretizar! Reserva não encontrada.");
        }
    }

    /**
     * Pesquisa uma reserva pelo código de identificação fornecido pelo usuário.
     * O método solicita ao usuário que insira o código da reserva que deseja pesquisar.
     * Após a inserção, o código é passado para o método {@code empresa.pesquisarReserva} para tentar localizar a reserva.
     * Se a reserva for encontrada, seus detalhes são exibidos; caso contrário, uma mensagem de erro é mostrada informando que a reserva não foi encontrada.
     *
     * @param mensagem A mensagem a ser exibida ao usuário antes de inserir o código da reserva.
     */
    public void pesquisarReservaPorID(String mensagem) {
        System.out.println(mensagem);
        String codigo = teclado.nextLine();
        Reserva reserva = empresa.pesquisarReserva(codigo);
        if (reserva != null) {
            System.out.println(reserva);
        } else {
            System.out.println("Reserva não encontrada.");
        }
    }

    /**
     * Lista todos os hotéis cadastrados na empresa que pertencem a uma categoria específica fornecida pelo usuário.
     * O método solicita ao usuário que insira uma categoria de hotel e, em seguida, exibe todos os hotéis da empresa
     * que pertencem a essa categoria. Caso nenhum hotel seja encontrado para a categoria fornecida, o método exibe
     * uma mensagem informando que não há hotéis disponíveis para a categoria solicitada.
     * <p>
     * O método continua solicitando a categoria até que uma entrada válida seja fornecida.
     */
    public void listarHoteisPorCategoria() {
        StringBuilder strB = new StringBuilder();
        CategoriaHotel categoriaH = null;

        boolean invalido = true;
        do {
            try {
                categoriaH = obterCategoriaHotel();
                for (Hotel hotel : empresa.getListaHoteis()) {
                    if (hotel.getCategoria().equals(categoriaH)) {
                        strB.append(hotel);
                    }
                }
                invalido = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (invalido);

        if (categoriaH == null) {
            System.out.println("Nenhum hotel encontrado para a categoria pretendida.");
        } else {
            System.out.println(strB.toString());
        }
    }

    /**
     * Solicita ao usuário as informações necessárias para criar um novo cliente e adicioná-lo ao sistema.
     * O método valida todas as entradas fornecidas pelo usuário, garantindo que os dados inseridos sejam válidos antes de
     * criar um novo objeto de cliente. As informações solicitadas incluem nome, data de nascimento, gênero, NIF,
     * número de passaporte, e email do cliente, além de uma percentagem de desconto acordada com a empresa.
     * <p>
     * O processo de validação assegura que:
     * <ul>
     *      <li> O nome do cliente não seja nulo ou vazio.
     *      <li> A data de nascimento seja válida e esteja no formato correto.
     *      <li> O gênero do cliente seja um valor válido.
     *      <li> O NIF seja válido de acordo com as regras definidas.
     *      <li> O número do passaporte seja inserido corretamente.
     *      <li> O email do cliente seja inserido corretamente.
     *      <li> A percentagem de desconto seja um valor numérico válido.
     * </ul>
     *
     * <p>
     * Após a inserção e validação dos dados, o método tenta adicionar o cliente ao sistema. Se o cliente for adicionado com sucesso,
     * uma mensagem de confirmação é exibida. Caso contrário, uma mensagem de erro é mostrada, informando que o cliente
     * já existe no sistema.
     *
     * @throws IllegalArgumentException se algum dos dados fornecidos for inválido (por exemplo, nome vazio).
     * @throws DiaInvalidoException,    MesInvalidoException, FormatoInvalidoException se a data de nascimento inserida for inválida.
     * @throws NifInvalidoException     se o NIF inserido for inválido.
     */
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
        if (empresa.adicionarCliente(cliente)) {
            System.out.println("Novo cliente adicionado com sucesso.");
        } else {
            System.out.println("Falha ao adicionar cliente! Cliente já existente.");
        }
    }

    /**
     * Solicita informações ao usuário para criar um novo hotel e adicioná-lo ao sistema.
     * O método valida todas as entradas fornecidas pelo usuário, garantindo que os dados inseridos são válidos antes de
     * criar um novo objeto de hotel. As informações solicitadas incluem o nome do hotel, sua categoria, localidade,
     * disponibilidade de serviço de transfer, e o preço do quarto. Após a inserção e validação dos dados, o hotel é
     * adicionado ao sistema.
     * <p>
     * As validações garantem que:
     * <ul>
     *     <li> O nome do hotel não seja nulo ou vazio.
     *     <li> A categoria do hotel seja válida.
     *     <li> A localidade do hotel não seja vazia.
     *     <li> A escolha sobre a disponibilidade do serviço de transfer seja feita corretamente.
     *     <li> O preço do quarto seja um número válido.
     * <ul/>
     * <p>
     * Após a criação do novo hotel, o método tenta adicioná-lo ao sistema. Se o hotel for adicionado com sucesso,
     * uma mensagem de confirmação é exibida. Caso contrário, uma mensagem de erro é mostrada, informando que o hotel
     * já existe no sistema.
     */
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

        if (empresa.adicionarHotel(novoHotel)) {
            System.out.println("Novo hotel adicionado com sucesso.");
        } else {
            System.out.println("Falha ao adicionar hotel! Hotel já existente.");
        }

    }

    /**
     * Exibe o custo total de diferentes tipos de reservas na empresa, incluindo:
     * <ul> <li> Reservas de hotéis.
     *       <li> Reservas de hotéis com voo.
     *      <li> Reservas de hotéis com voo ida e volta.
     *       <li> Reservas de voo (apenas ida).
     *      <li> Reservas de voo ida e volta.
     *     <li> Custo total de todas as reservas registradas na empresa.</ul>
     * <p>
     * <p>
     * O custo total de cada tipo de reserva é obtido por meio dos métodos da classe {@code Empresa} e é impresso na tela
     * com o valor formatado em euros (€).
     *
     * Cada tipo de reserva é apresentado em uma linha separada, com o valor correspondente.
     */
    private void mostrarCustoTotalReservas() {
        double custoRHotel = empresa.retornarCustoTodasReservasHoteis();
        double custoRHotelVoo = empresa.retornarCustoTodasReservasHoteisVoo();
        double custoRHotelVooIV = empresa.retornarCustoTodasReservasHoteisVooIV();
        double custoRVoo = empresa.retornarCustoTodasReservasVoo();
        double custoRVooIV = empresa.retornarCustoTodasReservasVooIV();
        double custoTotal = empresa.retornarCustoTotalDeTodasReservas();

        System.out.printf("Custo total das reservas de hotel: %.2f€\n", custoRHotel);
        System.out.printf("Custo total das reservas de hotel + voo: %.2f€\n", custoRHotelVoo);
        System.out.printf("Custo total das reservas de hotel + voo ida e volta : %.2f€\n", custoRHotelVooIV);
        System.out.printf("Custo total das reservas de voo (apenas ida): %.2f€\n", custoRVoo);
        System.out.printf("Custo total das reservas de voo ida e volta: %.2f€\n", custoRVooIV);
        System.out.printf("Custo total de todas as reservas cadastras na empresa: %.2f€\n", custoTotal);
    }

    /**
     * Lista todos os clientes da empresa, ordenados conforme a implementação do método
     * {@code retornarClientesOrdenados} da classe {@code Empresa}. Caso haja clientes cadastrados, cada um será impresso
     * no formato de string definido na classe {@code Cliente}.
     *
     * Se não houver nenhum cliente registrado na empresa, é exibida uma mensagem informando que não existem
     * clientes cadastrados.
     */
    public void listarClientesOrdenados() {
        List<Cliente> clientes = empresa.retornarClientesOrdenados();
        if (!clientes.isEmpty()) {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        } else {
            System.out.println("Não há clientes cadastrados na empresa.");
        }
    }

    /**
     * Obtém um cliente da empresa com base no código fornecido. O código do cliente é comparado com os códigos
     * dos clientes registrados na lista de clientes da empresa. Caso o cliente seja encontrado, o objeto {@code Cliente}
     * correspondente é retornado.
     *
     * Se o cliente não for encontrado, uma exceção {@link IllegalArgumentException} será lançada com a mensagem
     * "Cliente não encontrado."
     *
     * @param codigo O código do cliente a ser procurado.
     * @return O cliente correspondente ao código fornecido.
     * @throws IllegalArgumentException Se o cliente não for encontrado na lista de clientes da empresa.
     */
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

    /**
     * Obtém um hotel parceiro da empresa com base no código fornecido. O código do hotel é comparado com os códigos
     * dos hotéis registrados na lista de hotéis da empresa. Caso o hotel seja encontrado, o objeto {@code Hotel}
     * correspondente é retornado.
     *
     * Se o hotel não for encontrado, uma exceção {@link IllegalArgumentException} será lançada com a mensagem
     * "Hotel não encontrado."
     *
     * @param codigo O código do hotel a ser procurado.
     * @return O hotel correspondente ao código fornecido.
     * @throws IllegalArgumentException Se o hotel não for encontrado na lista de hotéis da empresa.
     */
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

    /**
     * Obtém um voo da empresa com base no código fornecido. O código do voo é comparado com os códigos
     * dos voos registrados na lista de voos da empresa. Caso o voo seja encontrado, o objeto {@code Voo}
     * correspondente é retornado.
     *
     * Se o voo não for encontrado, uma exceção {@link IllegalArgumentException} será lançada com a mensagem
     * "Voo não encontrado."
     *
     * @param codigo O código do voo a ser procurado.
     * @return O voo correspondente ao código fornecido.
     * @throws IllegalArgumentException Se o voo não for encontrado na lista de voos da empresa.
     */
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

    /**
     * Solicita ao usuário a entrada de uma data, que deve ser informada no formato "AAAA/MM/DD".
     * A entrada do usuário é validada, e se o formato for inválido, uma exceção {@link FormatoInvalidoException}
     * será lançada com uma mensagem de erro.
     *
     * Caso a data seja inserida corretamente, um objeto {@code Data} é retornado com os valores correspondentes
     * ao ano, mês e dia fornecidos.
     *
     * @param mensagem A mensagem que será exibida ao usuário para solicitar a entrada da data.
     * @return O objeto `Data` com os valores de ano, mês e dia informados.
     * @throws FormatoInvalidoException Se o formato da data for inválido ou se ocorrer algum erro ao processar a entrada.
     */
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

    /**
     * Solicita ao usuário a escolha de uma categoria de hotel entre as opções de 1 a 5, representando
     * de uma a cinco estrelas. A opção do usuário é lida e mapeada para um valor da enumeração {@link CategoriaHotel}.
     * Caso o usuário insira uma opção inválida, uma exceção {@link IllegalArgumentException} será lançada.
     *
     * @return A categoria do hotel escolhida pelo usuário como um valor da enumeração `CategoriaHotel`.
     * @throws IllegalArgumentException Se o usuário inserir uma opção inválida.
     */
    public CategoriaHotel obterCategoriaHotel() {
        System.out.println("Escolha a categoria do hotel:");
        String opcao = obterString("""
                - 1 - Uma Estrela
                - 2 - Duas Estrelas
                - 3 - Três Estrelas
                - 4 - Quatro Estrelas
                - 5 - Cinco Estrelas
                Insira o número da sua opção:""");

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
                throw new IllegalArgumentException("Erro: Categoria inválida.");
        }
    }

    /**
     * Solicita ao usuário a inserção de um gênero. O valor inserido é comparado com os valores da enumeração
     * {@link Genero}. Se o valor for válido, o respectivo valor da enumeração é retornado. Caso contrário, uma
     * exceção `IllegalArgumentException` será lançada.
     *
     * @param mensagem A mensagem que será exibida ao usuário para solicitar a entrada do gênero.
     * @return O valor da enumeração `Genero` correspondente à opção inserida pelo usuário.
     * @throws IllegalArgumentException Se o gênero inserido não for válido.
     */
    public Genero obterGenero(String mensagem) {
        String categ = obterString(mensagem).toUpperCase().trim();
        for (Genero genero : Genero.values()) {
            if (genero.name().equals(categ)) {
                return genero;
            }
        }

        throw new IllegalArgumentException("Erro: O gênero inserido está incorreto.");
    }

    /**
     * Solicita ao usuário a inserção de um número de NIF (número de identificação fiscal). O valor inserido
     * é verificado para garantir que está dentro do intervalo de 100000000 a 999999999. Caso o valor
     * esteja fora desse intervalo, uma exceção {@link NifInvalidoException} será lançada.
     *
     * @param mensagem A mensagem a ser exibida para solicitar a entrada do NIF.
     * @return O número de NIF inserido pelo usuário.
     * @throws NifInvalidoException Se o NIF inserido não for válido.
     */
    public int obterNif(String mensagem) {
        System.out.println(mensagem);
        int nif = teclado.nextInt();
        teclado.nextLine();

        if (nif < 100000000 || nif > 999999999) {
            throw new NifInvalidoException();
        }
        return nif;
    }

    /**
     * Solicita ao usuário que insira uma opção numérica dentro de um intervalo especificado. O valor inserido
     * é validado para garantir que está dentro dos limites fornecidos (min e max). Caso a entrada seja inválida
     * ou fora do intervalo, uma exceção {@link IllegalArgumentException} será lançada.
     *
     * @param mensagem A mensagem a ser exibida ao usuário para solicitar a entrada da opção.
     * @param min O valor mínimo permitido para a opção.
     * @param max O valor máximo permitido para a opção.
     * @return O número da opção inserida, se válido.
     * @throws IllegalArgumentException Se o número inserido estiver fora do intervalo permitido ou se a entrada for inválida.
     */
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

    /**
     * Solicita ao usuário que insira uma string. A entrada do usuário é lida e retornada após remoção de espaços
     * em branco antes e depois da string.
     *
     * @param mensagem A mensagem a ser exibida ao usuário para solicitar a entrada de uma string.
     * @return A string inserida pelo usuário, após a remoção de espaços.
     */
    public String obterString(String mensagem) {
        System.out.println(mensagem);
        return teclado.nextLine().trim();
    }

    /**
     * Pergunta ao usuário se deseja salvar as informações da empresa em um arquivo binário. O usuário pode
     * optar por salvar (1) ou não (2). Se a resposta for 1, o processo de serialização da empresa é iniciado.
     * Caso contrário, o processo é finalizado.
     */
    public void perguntaFinal() {
        int resposta = obterOpcao("Deseja salvar as informações da empresa em um ficheiro binário? (1- SIM / 2- NÃO):", 1, 2);

        if (resposta == 1) {
            serializarEmpresa();
        } else {
            System.out.println("Logout concluído!");
        }
    }


    /**
     * Serializa as informações da empresa para um arquivo binário. O nome do arquivo é solicitado ao usuário
     * até que um nome válido seja inserido. Se a serialização for bem-sucedida, uma mensagem de sucesso é exibida,
     * caso contrário, uma mensagem de erro é mostrada.
     */
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

    /**
     * Exibe o menu principal com as opções disponíveis para o usuário, como gerenciar reservas, listar hotéis,
     * inserir novos clientes e hotéis, visualizar custos, e sair do programa.
     */
    public void mostrarMenu() {
        System.out.println("""
                
                ------------------ MENU ------------------
                1 - Gerenciador de reservas
                2 - Listar hotéis por categoria
                3 - Inserir novo cliente
                4 - Inserir novo hotel
                5 - Visualizar custos das reservas
                6 - Listar hoteis com transfer
                7 - Listar clientes ordenados
                0 - Sair do programa
                ------------------------------------------
                """);
    }

    /**
     * Exibe o menu para o gerenciamento de reservas, oferecendo opções para criar reservas de hotel e voo,
     * eliminar reservas, visualizar reservas e pesquisar por código.
     *
     * @return O menu de opções de reservas como uma string.
     */
    public String menuReservas() {
        return ("""
                ---------------- RESERVAS ----------------
                1 - Criar Reserva de Hotel
                2 - Criar Reserva de Hotel e Voo
                3 - Criar Reserva Hotel e Voo Ida e Volta
                4 - Criar Reserva Voo
                5 - Criar Reserva Voo Ida e Volta
                6 - Eliminar reserva
                7 - Visualizar reservas
                8 - Concretizar reserva
                9 - Pesquisar reserva por código
                0 - Voltar ao menu inicial
                ------------------------------------------
                Insira a opção desejada:""");
    }

    /**
     * Exibe o menu para visualizar reservas, oferecendo opções para ver reservas de hotel, hotel + voo,
     * voo ida e volta, todas as reservas ordenadas por custo ou data, e por cliente específico.
     *
     * @return O menu de visualização de reservas como uma string.
     */
    public String menuVisualizarReservas() {
        return ("""
                ----------- VISUALIZAR RESERVAS -----------
                1 - Visualizar reservas de Hotel
                2 - Visualizar reservas de Hotel + Voo
                3 - Visualizar reservas de Hotel + Voo Ida e Volta
                4 - Visualizar reservas de Voo Ida e Volta
                5 - Visualizar reservas de Voo somente ida
                6 - Visualizar todas as reservas ordenadas por custo
                7 - Visualizar todas as reservas ordenadas por data
                8 - Visualizar todas as reservas de um cliente específico
                0 - Voltar ao menu anterior
                ------------------------------------------
                Insira a opção desejada:""");
    }
}