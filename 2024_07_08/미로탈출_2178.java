package backJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미로탈출_2178 {
    static int N;
    static int M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static class Pos {
        int y;
        int x;
        int distance;

        Pos(int y, int x, int distance) {
            this.y = y;
            this.x = x;
            this.distance = distance;
        }
    }
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(line[j]);
            }
        }

        visited = new boolean[N][M];
        /**
         * 흔히 알려져 있는 bfs()을 사용한다.
         * (N, M)에 도달했을 경우 이동한 칸의 min 값을 설정한다.
         */
        bfs(0, 0);
        System.out.println(answer);
    }

    private static void bfs(int y, int x) {
        Queue<Pos> q = new LinkedList<>();

        q.add(new Pos(y, x, 0));
        visited[y][x] = true;

        while(!q.isEmpty()) {
            Pos p = q.poll();
            int distance = p.distance + 1;

            if (p.y == N - 1 && p.x == M - 1) {
                answer = Math.min(answer, distance);
            }

            for (int d = 0; d < 4; d++) {
                int ny = p.y + dy[d];
                int nx = p.x + dx[d];

                if ((ny < 0 || ny >= N || nx < 0 || nx >= M) || board[ny][nx] == 0 || visited[ny][nx]) {
                    continue;
                }
                visited[ny][nx] = true;
                q.add(new Pos(ny, nx, distance));
            }
        }
    }
}
