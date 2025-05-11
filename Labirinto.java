import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Labirinto {
    private char[][] labirinto;

    // Construtor padrão
    public Labirinto() {}

    // Método para criar o labirinto a partir de um arquivo
    public void criaLabirinto(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String linha;
            int linhas = 0;
            int colunas = 0;

            // Primeiro, lemos o arquivo para saber quantas linhas e colunas ele tem
            while ((linha = br.readLine()) != null) {
                colunas = Math.max(colunas, linha.length()); // garante a maior largura
                linhas++;
            }

            br.close();
            labirinto = new char[linhas][colunas];

            br = new BufferedReader(new FileReader(filename));
            int i = 0;

            while ((linha = br.readLine()) != null) {
                for (int j = 0; j < linha.length(); j++) {
                    labirinto[i][j] = linha.charAt(j);
                }
                i++;
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: "+ e.getMessage());
        }
    }

    // Método auxiliar para imprimir o labirinto (para debug)
    public void imprimirLabirinto() {
        for(char[] linha : labirinto) {
            for (char c: linha) {
                System.out.println(c);
            }
            System.out.println();
        }
    }

    public boolean percorreLabirinto() {
        // Começa a partir da posição inicial (ex: 0, 0). Ajuste conforme necessário.
        return percorreLabirinto(0, 0);
    }

    private boolean percorreLabirinto (int linha, int coluna) {
        // Verificações de limites
        if (linha < 0 || coluna < 0 || linha >= labirinto.length || coluna >= labirinto[0].length) {
            return false;
        }

        char atual = labirinto[linha][coluna];

        // Se for parede ou já visitado, retorna falso
        if (atual == 'X' || atual == '*') {
            return false;
        }

        // Se for a saída
        if (atual == 'D') {
            return true;
        }

        // Marca como visitado
        labirinto[linha][coluna] = '*';

        // Tenta andar para todas as direções (cima, baixo, esquerda, direita)
        if (percorreLabirinto(linha - 1, coluna) || // cima
                percorreLabirinto(linha + 1, coluna) || // baixo
                percorreLabirinto(linha, coluna - 1) || // esquerda
                percorreLabirinto(linha, coluna + 1)) { // direita
            return true;
        }
        return false;
    }
}
