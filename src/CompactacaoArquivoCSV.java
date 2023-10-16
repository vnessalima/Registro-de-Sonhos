//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompactacaoArquivoCSV {

    public static void compactarArquivoCSV(String Sonhos) {
        // Nome do arquivo ZIP de saída
        String ArquivoZIP = Sonhos + ".zip";

        try (
            // Cria um fluxo de saída para o arquivo ZIP
            FileOutputStream fos = new FileOutputStream(ArquivoZIP);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
        ) {
            // Cria uma entrada no arquivo ZIP
            ZipEntry zipEntry = new ZipEntry(Sonhos);

            // Adiciona a entrada ao arquivo ZIP
            zipOut.putNextEntry(zipEntry);

            // Lê o conteúdo do arquivo CSV e escreve no arquivo ZIP
            try (FileInputStream fis = new FileInputStream(Sonhos)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    zipOut.write(buffer, 0, bytesRead);
                }
            }

            // Fecha a entrada no arquivo ZIP
            zipOut.closeEntry();

            System.out.println("Arquivo " + Sonhos + " foi compactado com sucesso para " + ArquivoZIP);

            // Calcular e exibir o hash SHA-256 do arquivo CSV
            calcularEExibirHashSHA256(Sonhos);

            // Converter para JSON
            //List<Sonho> sonhos = lerDadosDoCSV(Sonhos);
            //converterParaJSON(sonhos);

            // Converter para XML
            //converterParaXML(sonhos);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void calcularEExibirHashSHA256(String arquivoCSV) {
        try (FileInputStream fis = new FileInputStream(arquivoCSV)) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }

            byte[] hashBytes = digest.digest();
            StringBuilder hexHash = new StringBuilder();

            for (byte hashByte : hashBytes) {
                hexHash.append(String.format("%02x", hashByte));
            }

            System.out.println("Hash SHA-256 do arquivo " + arquivoCSV + ": " + hexHash.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Sonho> lerDadosDoCSV(String arquivoCSV) {
    List<Sonho> sonhos = new ArrayList<>();
    
    try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
        String line;
        String[] headers = null;
        while ((line = reader.readLine()) != null) {
            if (headers == null) {
                // O primeiro linha é o cabeçalho, então armazenamos as colunas.
                headers = line.split(",");
            } else {
                String[] values = line.split(",");
                if (values.length == headers.length) {
                    // Criar um objeto Sonho com os valores da linha.
                    String data = values[0].trim();
                    String hora = values[1].trim();
                    String tema = values[2].trim();
                    String descricao = values[3].trim();
                    String emocoes = values[4].trim();

                    Sonho sonho = new Sonho(data, hora, tema, descricao, emocoes);
                    sonhos.add(sonho);
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return sonhos;
}


    /*public static void converterParaJSON(List<Sonho> sonhos) {
        unzip apache-maven-3.9.4-bin.zip
        try {
            objectMapper.writeValue(new FileWriter("sonhos.json"), sonhos);
            System.out.println("Dados convertidos para JSON com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void converterParaXML(List<Sonho> sonhos) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(new File("sonhos.xml"), sonhos);
            System.out.println("Dados convertidos para XML com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }/* */

    public static void main(String[] args) {
        String Sonhos = "sonhos.csv";
        compactarArquivoCSV(Sonhos);
    }
}
