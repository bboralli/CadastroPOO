package cadastropoo;

import java.io.Serializable;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 */
public class CadastroPooParte2 {
    
    private static PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
    private static PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("==================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("==================================");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    incluirPessoa();
                    break;
                case 2:
                    alterarPessoa();
                    break;
                case 3:
                    excluirPessoa();
                    break;
                case 4:
                    buscarPeloId();
                    break;
                case 5:
                    exibirTodos();
                    break;
                case 6:
                    persistirDados();
                    break;
                case 7:
                    recuperarDados();
                    break;
                case 0:
                    System.out.println("Finalizando programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void excluirPessoa() {
        System.out.println("Excluir Pessoa: [F] - Física | [J] - Jurídica");
        String tipo = scanner.nextLine().toUpperCase();
        System.out.print("Digite o id da pessoa: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (tipo.equals("F")) {
            repoFisica.excluir(id);
            System.out.println("Pessoa Física excluída.");
        } else if (tipo.equals("J")) {
            repoJuridica.excluir(id);
            System.out.println("Pessoa Jurídica excluída.");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void buscarPeloId() {
        System.out.println("Buscar Pessoa pelo ID: [F] - Física | [J] - Jurídica");
        String tipo = scanner.nextLine().toUpperCase();
        System.out.print("Digite o id da pessoa: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (tipo.equals("F")) {
            PessoaFisica pf = repoFisica.obter(id);
            if (pf != null) {
                pf.exibir();
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
        } else if (tipo.equals("J")) {
            PessoaJuridica pj = repoJuridica.obter(id);
            if (pj != null) {
                pj.exibir();
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void exibirTodos() {
        System.out.println("Exibir Todos: [F] - Física | [J] - Jurídica");
        String tipo = scanner.nextLine().toUpperCase();

        if (tipo.equals("F")) {
            System.out.println("Pessoas Físicas:");
            for (PessoaFisica pf : repoFisica.obterTodos()) {
                pf.exibir();
            }
        } else if (tipo.equals("J")) {
            System.out.println("Pessoas Jurídicas:");
            for (PessoaJuridica pj : repoJuridica.obterTodos()) {
                pj.exibir();
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void persistirDados() {
        System.out.print("Digite o prefixo para os arquivos de persistência: ");
        String prefixo = scanner.nextLine();
        String arquivoFisica = prefixo + ".fisica.bin";
        String arquivoJuridica = prefixo + ".juridica.bin";

        try {
            repoFisica.persistir(arquivoFisica);
            repoJuridica.persistir(arquivoJuridica);
            System.out.println("Dados persistidos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao persistir os dados: " + e.getMessage());
        }
    }

    private static void recuperarDados() {
        System.out.print("Digite o prefixo para os arquivos de recuperação: ");
        String prefixo = scanner.nextLine();
        String arquivoFisica = prefixo + ".fisica.bin";
        String arquivoJuridica = prefixo + ".juridica.bin";

        try {
            repoFisica.recuperar(arquivoFisica);
            repoJuridica.recuperar(arquivoJuridica);
            System.out.println("Dados recuperados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao recuperar os dados: " + e.getMessage());
        }
    }

    private static void incluirPessoa() {
        System.out.println("Incluir Pessoa: [F] - Física | [J] - Jurídica");
        String tipo = scanner.nextLine().toUpperCase();

        while (!tipo.equals("F") && !tipo.equals("J")) {
            System.out.println("Tipo inválido. Por favor, digite F para Pessoa Física ou J para Pessoa Jurídica.");
            System.out.println("Incluir Pessoa: [F] - Física | [J] - Jurídica");
            tipo = scanner.nextLine().toUpperCase();

        }
    
        System.out.print("Digite o id da pessoa: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
    
        System.out.print("Digite o nome da pessoa: ");
        String nome = scanner.nextLine();
    
        if (tipo.equals("F")) {
            System.out.print("Digite o CPF da pessoa: ");
            String cpf = scanner.nextLine();
    
            System.out.print("Digite a idade da pessoa: ");
            int idade = scanner.nextInt();
            scanner.nextLine(); 
    
            PessoaFisica pf = new PessoaFisica(id, nome, cpf, idade);
            repoFisica.inserir(pf);
            System.out.println("Pessoa Física incluída com sucesso.");
        } else { 
            System.out.print("Digite o CNPJ da pessoa: ");
            String cnpj = scanner.nextLine();
    
            PessoaJuridica pj = new PessoaJuridica(id, nome, cnpj);
            repoJuridica.inserir(pj);
            System.out.println("Pessoa Jurídica incluída com sucesso.");
        }
    }   

    private static void alterarPessoa() {
        System.out.println("Alterar Pessoa: [F] - Física | [J] - Jurídica");
        String tipo = scanner.nextLine().toUpperCase();

        System.out.print("Digite o id da pessoa para alteração: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (tipo.equals("F")) {
            PessoaFisica pf = repoFisica.obter(id);
            if (pf != null) {
                System.out.print("Digite o novo nome da pessoa Física (ou deixe em branco para não alterar): ");
                String nome = scanner.nextLine();
                if (!nome.isEmpty())
                    pf.setNome(nome);

                System.out.print("Digite o novo CPF da pessoa Física (ou deixe em branco para não alterar): ");
                String cpf = scanner.nextLine();
                if (!cpf.isEmpty())
                    pf.setCpf(cpf);

                System.out.print("Digite a nova idade da pessoa Física (ou 0 para não alterar): ");
                int idade = scanner.nextInt();
                if (idade != 0)
                    pf.setIdade(idade);

                repoFisica.alterar(pf);
                System.out.println("Pessoa Física alterada com sucesso.");
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
        } else if (tipo.equals("J")) {
            PessoaJuridica pj = repoJuridica.obter(id);
            if (pj != null) {
                System.out.print("Digite o novo nome da pessoa Jurídica (ou deixe em branco para não alterar): ");
                String nome = scanner.nextLine();
                if (!nome.isEmpty())
                    pj.setNome(nome);

                System.out.print("Digite o novo CNPJ da pessoa Jurídica (ou deixe em branco para não alterar): ");
                String cnpj = scanner.nextLine();
                if (!cnpj.isEmpty())
                    pj.setCnpj(cnpj);

                repoJuridica.alterar(pj);
                System.out.println("Pessoa Jurídica alterada com sucesso.");
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }
    
}
