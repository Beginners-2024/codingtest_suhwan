package backJoon;

import java.io.*;
import java.util.*;

public class 내리막_길_1520 {
    private static int M, N, H;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        H = 0;

        map = new int[M][N];

        for (int i = 0; i < M; ++i) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        visited = new boolean[M][N];
        visited[0][0] = true;
        getCount(0, 0);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(new StringBuilder().append(H).toString());
        bw.close();
        br.close();
    }

    private static void getCount(int y, int x) {
        if (y == M - 1 && x == N - 1) {
            H++;
            return;
        }

        for (int i = 0; i < 4; ++i) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= M || nx < 0 || nx >= N) {
                continue;
            }
            if (map[y][x] > map[ny][nx] && !visited[ny][nx]) {
                visited[ny][nx] = true;
                getCount(ny, nx);
                visited[ny][nx] = false;
            }
        }
    }
}
