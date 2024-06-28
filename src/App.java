import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String arquivoA = "C:\\Users\\dados\\Desktop\\madara\\madara\\pessoa.csv";
        String arquivoB = "C:\\Users\\dados\\Desktop\\madara\\madara\\endereco.csv";
        String arquivoC = "C:\\Users\\dados\\Desktop\\madara\\madara\\pessoacomendereco.csv";

        File fileA = new File(arquivoA);
        File fileB = new File(arquivoB);
        File fileC = new File(arquivoC);

        // Carrega os dados de ambos os arquivos em listas
        List<String[]> pessoas = lerArquivo(fileA);
        List<String[]> enderecos = lerArquivo(fileB);

        // Combina os dados e grava no arquivo de saída
        combinarEGravar(pessoas, enderecos, fileC);
    }

    public static List<String[]> lerArquivo(File arquivo) throws IOException {
        List<String[]> linhas = new ArrayList<>();
        Scanner scan = new Scanner(arquivo);

        while (scan.hasNextLine()) {
            String linha = scan.nextLine();
            String[] partes = linha.split(";");
            linhas.add(partes);
        }
        scan.close();
        return linhas;
    }

    public static void combinarEGravar(List<String[]> pessoas, List<String[]> enderecos, File arquivoSaida) throws IOException {
        FileWriter writer = new FileWriter(arquivoSaida);

        for (String[] pessoa : pessoas) {
            String idPessoa = pessoa[0];
            String nome = pessoa[1];

            for (String[] endereco : enderecos) {
                if (endereco[2].equals(idPessoa)) {
                    writer.write(idPessoa + ";" + nome + ";" + endereco[0] + ";" + endereco[1] + ";" + idPessoa + "\n");
                }
            }
        }
        writer.close();
    }
}