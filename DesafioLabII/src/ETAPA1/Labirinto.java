package ETAPA1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Labirinto {
    private char[][] labirinto;

    //construtor
    public Labirinto() {}

    //método para criar o labirinto a partir de um arquivo txt
    public void criaLabirinto(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String linha;
            int linhas = 0;
            int colunas = 0;

            //le o arquivo pra saber quantas linhas e colunas ele tem
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

    //método auxiliar para imprimir o labirinto
    public void imprimirLabirinto() {
        for(char[] linha : labirinto) {
            for (char c: linha) {
                System.out.println(c);
            }
            System.out.println();
        }
    }

    public boolean percorreLabirinto() {
        //faz comecar da posicao 0, 0 (linha, coluna)
        return percorreLabirinto(0, 0);
    }

    private boolean percorreLabirinto (int linha, int coluna) {
        //verificações de limites das linhas e colunas
        if (linha < 0 || coluna < 0 || linha >= labirinto.length || coluna >= labirinto[0].length) {
            return false;
        }

        char atual = labirinto[linha][coluna];

        //se for parede ou já visitado, retorna falso
        if (atual == 'X' || atual == '*') {
            return false;
        }

        //se for D marca como saida/fim
        if (atual == 'D') {
            return true;
        }

        //marca como visitado
        labirinto[linha][coluna] = '*';

        //anda para todas as direções
        if (percorreLabirinto(linha - 1, coluna) || // cima
                percorreLabirinto(linha + 1, coluna) || // baixo
                percorreLabirinto(linha, coluna - 1) || // esquerda
                percorreLabirinto(linha, coluna + 1)) { // direita
            return true;
        }
        return false;
    }
}
