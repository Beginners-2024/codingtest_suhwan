package backJun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커 {
    static int N;
    static int M;
    static int K;
    static int answer;
    static int[][] lapTop;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        lapTop = new int[N][M];
        answer = 0;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[n][m];
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(bf.readLine());
                for (int k = 0; k < m; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            findLocation(sticker, n, m);
        }

        System.out.println(answer);
    }

    private static void findLocation(int[][] sticker, int n, int m) {
        for (int d = 0; d < 4; d++) {
            if (d != 0) {
                sticker = rotateSticker(sticker, n, m);
            }
            n = sticker.length;
            m = sticker[0].length;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (i + n > N || j + m > M) {
                        break ;
                    }
                    if (putSticker(i, j, n, m, sticker)) {
                        return ;
                    }
                }
            }
        }
    }

    private static int[][] rotateSticker(int[][] sticker, int n, int m) {
        int[][] newOne = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newOne[j][n - i - 1] = sticker[i][j];
            }
        }
        return newOne;
    }

    private static boolean putSticker(int i, int j, int n, int m, int[][] sticker) {
        for (int y = i; y < i + n; y++) {
            for (int x = j; x < j + m; x++) {
                if (lapTop[y][x] == 1 && sticker[y - i][x - j] == 1) {
                    return false;
                }
            }
        }

        for (int y = i; y < i + n; y++) {
            for (int x = j; x < j + m; x++) {
                if (sticker[y - i][x - j] == 1) {
                    lapTop[y][x] = 1;
                    answer++;
                }
            }
        }
        return true;
    }
}
