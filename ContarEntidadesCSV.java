import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ContarEntidadesCSV {
    public static int contarEntidadesCSV(String arquivoCSV) throws IOException {
        int count = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
            String line;
            int sonhoInfoCount = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Data:")) {
                    sonhoInfoCount = 1;
                } else if (sonhoInfoCount == 1 && line.startsWith("Tema:")
                        && reader.readLine().startsWith("Descrição:")
                        && reader.readLine().startsWith("Hora:")
                        && reader.readLine().startsWith("Emoções:")) {
                    sonhoInfoCount = 0;
                    count++;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao contar as entidades: " + e.getMessage());
        }

        System.out.println("Quantidade de entidades no arquivo CSV: " + count);
        return count;
    }
}