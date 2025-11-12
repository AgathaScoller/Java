import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CalculadoraCientifica {

    private static final String HISTORICO_ARQUIVO = "historico-operacoes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean fim = false;

        while (!fim) {
            System.out.println("\n--- CALCULADORA CIENTÍFICA JAVA ---");
            System.out.println("1. Soma");
            System.out.println("2. Subtração");
            System.out.println("3. Multiplicação");
            System.out.println("4. Divisão");
            System.out.println("5. Raiz Quadrada");
            System.out.println("6. Potência");
            System.out.println("7. Logaritmo Natural");
            System.out.println("8. Seno");
            System.out.println("9. Cosseno");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine().trim();
            String tipoOperacao;
            double entrada1, entrada2, resultado;

            switch (opcao) {
                case "1":
                    tipoOperacao = "Soma";
                    double[] entradas1 = duasEntradas(scanner);
                    entrada1 = entradas1[0];
                    entrada2 = entradas1[1];
                    resultado = entrada1 + entrada2;
                    System.out.println("Resultado: " + resultado);
                    registrarOperacao(tipoOperacao, entrada1, entrada2, resultado);
                    break;

                case "2":
                    tipoOperacao = "Subtração";
                    double[] entradas2 = duasEntradas(scanner);
                    entrada1 = entradas2[0];
                    entrada2 = entradas2[1];
                    resultado = entrada1 - entrada2;
                    System.out.println("Resultado: " + resultado);
                    registrarOperacao(tipoOperacao, entrada1, entrada2, resultado);
                    break;

                case "3":
                    tipoOperacao = "Multiplicação";
                    double[] entradas3 = duasEntradas(scanner);
                    entrada1 = entradas3[0];
                    entrada2 = entradas3[1];
                    resultado = entrada1 * entrada2;
                    System.out.println("Resultado: " + resultado);
                    registrarOperacao(tipoOperacao, entrada1, entrada2, resultado);
                    break;

                case "4":
                    tipoOperacao = "Divisão";
                    double[] entradas4 = duasEntradas(scanner);
                    entrada1 = entradas4[0];
                    entrada2 = entradas4[1];
                    if (entrada2 == 0) {
                        System.out.println("Erro: Divisão por zero.");
                    } else {
                        resultado = entrada1 / entrada2;
                        System.out.println("Resultado: " + resultado);
                        registrarOperacao(tipoOperacao, entrada1, entrada2, resultado);
                    }
                    break;

                case "5":
                    tipoOperacao = "Raiz Quadrada";
                    entrada1 = entradaUnica(scanner, "Digite o número: ");
                    if (entrada1 < 0) {
                        System.out.println("Erro: Número negativo.");
                    } else {
                        resultado = Math.sqrt(entrada1);
                        System.out.println("Resultado: " + resultado);
                        registrarOperacao(tipoOperacao, entrada1, 0.0, resultado);
                    }
                    break;

                case "6":
                    tipoOperacao = "Potência";
                    double[] entradas6 = duasEntradas(scanner);
                    entrada1 = entradas6[0];
                    entrada2 = entradas6[1];
                    if (entrada1 > 0) {
                        resultado = Math.exp(Math.log(entrada1) * entrada2);
                    } else {
                        resultado = Math.pow(entrada1, entrada2);
                    }
                    System.out.println("Resultado: " + resultado);
                    registrarOperacao(tipoOperacao, entrada1, entrada2, resultado);
                    break;

                case "7":
                    tipoOperacao = "Logaritmo Natural";
                    entrada1 = entradaUnica(scanner, "Digite o número: ");
                    if (entrada1 <= 0) {
                        System.out.println("Erro: Valor <= 0.");
                    } else {
                        resultado = Math.log(entrada1);
                        System.out.println("Resultado: " + resultado);
                        registrarOperacao(tipoOperacao, entrada1, 0.0, resultado);
                    }
                    break;

                case "8":
                    tipoOperacao = "Seno";
                    entrada1 = entradaUnica(scanner, "Digite o ângulo (radianos): ");
                    resultado = Math.sin(entrada1);
                    System.out.println("Resultado: " + resultado);
                    registrarOperacao(tipoOperacao, entrada1, 0.0, resultado);
                    break;

                case "9":
                    tipoOperacao = "Cosseno";
                    entrada1 = entradaUnica(scanner, "Digite o ângulo (radianos): ");
                    resultado = Math.cos(entrada1);
                    System.out.println("Resultado: " + resultado);
                    registrarOperacao(tipoOperacao, entrada1, 0.0, resultado);
                    break;

                case "0":
                    fim = true;
                    System.out.println("Calculadora encerrada.");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
        scanner.close();
    }

    private static double[] duasEntradas(Scanner scanner) {
        double[] entradas = new double[2];
        while (true) {
            try {
                System.out.print("Digite o primeiro número: ");
                entradas[0] = Double.parseDouble(scanner.nextLine());
                System.out.print("Digite o segundo número: ");
                entradas[1] = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
            }
        }
        return entradas;
    }

    private static double entradaUnica(Scanner scanner, String mensagem) {
        double valor = 0;
        while (true) {
            try {
                System.out.print(mensagem);
                valor = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
            }
        }
        return valor;
    }

    private static void registrarOperacao(String tipoOperacao, double entrada1, double entrada2, double resultado) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORICO_ARQUIVO, true))) {
            writer.write(String.format("%-30s %15.6f %15.6f %15.6f%n", tipoOperacao, entrada1, entrada2, resultado));
        } catch (IOException e) {
            System.out.println("Erro ao gravar no arquivo de histórico: " + e.getMessage());
        }
    }
}
