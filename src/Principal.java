import java.io.*;
import java.util.*;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Sonho> sonhos = new ArrayList<>();
        String arquivoCSV = "sonhos.csv";
        int quantidadeEntidades = contarEntidadesCSV(arquivoCSV);

        while (true) {

            System.out.println("Quantidade de entidades no arquivo CSV: " + quantidadeEntidades);

            System.out.println("Menu:");
            System.out.println("1. Inserir Sonho");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");
            
            int escolha = scanner.nextInt();
            scanner.nextLine(); 

            switch (escolha) {
                case 1:
                    inserirSonho(sonhos, scanner);
                    quantidadeEntidades++;
                    salvarSonhosEmCSV(sonhos, arquivoCSV);
                    break;
                case 2:
                    salvarSonhosEmCSV(sonhos, arquivoCSV);
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }



    private static int contarEntidadesCSV(String arquivoCSV) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("Data:")) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    private static void inserirSonho(List<Sonho> sonhos, Scanner scanner) {
        System.out.print("Digite a data em que você sonhou isso: ");
        String data = scanner.nextLine();
        
        System.out.print("Qual o tema do seu sonho? (ex: repetido, pesadelo, lembranças...). ");
        String tema = scanner.nextLine();

        System.out.print("Descreva exatamente o que você lembra desse sonho: ");
        String descricao = scanner.nextLine();

        System.out.print("Digite a hora e/ou turno em que você sonhou: ");
        String hora = scanner.nextLine();

        System.out.print("Descreva brevemente as emoções sentidas durante seu sonho. ");
        String emocoes = scanner.nextLine();

        Sonho sonho = new Sonho(data, hora, tema, descricao, emocoes);
        sonhos.add(sonho);
        System.out.println("Sonho inserido com sucesso!");
    }

   private static void salvarSonhosEmCSV(List<Sonho> sonhos, String arquivoCSV) {
    //String arquivoCSV = "sonhos.csv";

    try (PrintWriter writer = new PrintWriter(new File(arquivoCSV))) {
        StringBuilder sb = new StringBuilder();
        sb.append("Registre aqui seu sonho.\n");

        for (Sonho sonho : sonhos) {
            sb.append("Data: ").append(sonho.getData()).append("\n");
            sb.append("Tema: ").append(sonho.getTema()).append("\n");
            sb.append("Descrição: ").append(sonho.getDescricao()).append("\n");
            sb.append("Hora: ").append(sonho.getHora()).append("\n");
            sb.append("Emoções: ").append(sonho.getEmocoes()).append("\n");
            sb.append("\n"); 
        }

        writer.write(sb.toString());
        System.out.println("Sua descrição foi salva em " + arquivoCSV + " obrigada por usar o nosso sistema.");
    } catch (FileNotFoundException e) {
        System.out.println("Erro ao salvar o arquivo CSV: " + e.getMessage());
    }
}
}
