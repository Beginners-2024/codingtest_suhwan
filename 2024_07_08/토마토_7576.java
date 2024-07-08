package backJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토_7576 {
    static int N;
    static int M;
    static int[][] basket;
    static int[] DY = {1, -1, 0, 0};
    static int[] DX = {0, 0, 1, -1};
    static class Tomato {
        int y;
        int x;
        int day;

        Tomato(int y, int x, int day) {
            this.y = y;
            this.x = x;
            // 하루하루 지나면서 몇일째 익은 토마토인지 기록
            this.day = day;
        }
    }
    static Queue<Tomato> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        basket = new int[M][N];

        q = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                basket[i][j] = Integer.parseInt(st.nextToken());
                if (basket[i][j] == 1) {
                    // 익은 토마토를 Q에 저장
                    q.add(new Tomato(i, j, 0));
                }
            }
        }
        /**
         * bfs()을 통해서 저장된 익은 토마토를 순회하면서, 4방향에 익지 않은 토마토에게 영향을 준다
         * Q가 비워지면 토마토가 다 익은 것이기 때문에, 모든 바구니를 순회하면서, 익지 않은 토마토가 있는지 확인하고 결과를 return
         */
        bw.write(new StringBuilder().append(bfs()).toString());
        bw.close();
        br.close();
    }

    private static int bfs() {
        int day = 0;
        while (!q.isEmpty()) {
            Tomato t = q.poll();

            day = t.day;
            for (int i = 0; i < 4; i++) {
                int dy = t.y + DY[i];
                int dx = t.x + DX[i];

                if (dy < 0 || dy >= M || dx < 0 || dx >= N || basket[dy][dx] == -1 || basket[dy][dx] == 1) {
                    continue;
                }
                basket[dy][dx] = 1;
                q.add(new Tomato(dy, dx, day + 1));
            }
        }
        if (!checkBasket()) {
            return -1;
        }
        return day;
    }

    private static boolean checkBasket() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (basket[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}