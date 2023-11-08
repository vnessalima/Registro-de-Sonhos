import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) throws IOException {
        MenuPrincipal menu = new MenuPrincipal();
        menu.executar();
    }
}

class MenuPrincipal {
    private Scanner scanner = new Scanner(System.in);
    private String arquivoCSV = "sonhos.csv";
    
    public void executar() throws IOException {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Registrar novo sonho");
            System.out.println("2. Mostrar a quantidade de entidades no arquivo CSV");
            //System.out.println("3. Converter dados para JSON e XML");
            System.out.println("3. Compactar arquivo CSV em ZIP");
            System.out.println("4. Mostrar hash SHA256 do arquivo CSV");
            System.out.println("5. Sair");
    
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();
    
            switch (escolha) {
                case 1:
                    InserirSonho.inserirSonho(arquivoCSV);
                    break;
                case 2:
                    ContarEntidadesCSV.contarEntidadesCSV(arquivoCSV);
                    break;
                /*case 3:
                    ConverterDadosParaJSONeXML.converterDadosParaJSONeXML(arquivoCSV);
                    break;
                */
                case 3:
                    CompactacaoArquivoCSV.compactarArquivoCSV(arquivoCSV);
                    break;
                case 4:
                    try {
                        String hash = CalcularEExibirHashSHA256.calcularHashSHA256(arquivoCSV);
                        System.out.println("Hash SHA-256 do arquivo CSV: " + hash);
                    } catch (IOException e) {
                        System.out.println("Erro ao calcular o hash: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}