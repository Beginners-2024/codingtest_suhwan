package backJoon;

import java.util.*;
import java.io.*;

public class 그림_1926 {
    static int N;
    static int M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    //그림의 첫 시작 '1'을 사용하기 위한 class, 개인적으로 int[] 배열 보다는 사용하기 편해서 주로 이용
    static class Pos {
        int y;
        int x;

        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static int pictureCount = 0;
    static int widestPicture = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1 && !visited[i][j]) {
                    // 방문하지 않았고, 좌표 값아 '1'인 경우 그림이 시작하는 (i, j) 값으로 그림의 크기를 구함
                    bfs(new Pos(i, j));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(pictureCount).append('\n').append(widestPicture);
        bw.write(sb.toString());

        bw.close();
        bf.close();
    }

    private static void bfs(Pos p) {
        Queue<Pos> q = new LinkedList<>();
        q.add(p);

        int size = 0;

        //처음 그림 시작 값을 방문한 것으로 처리해야, dfs 돌때 다시 방문하지 않음
        visited[p.y][p.x] = true;
        while (!q.isEmpty()) {
            Pos pos = q.poll();
            //그림의 부분이기에 크기를 하나씩 늘려감
            size += 1;
            for (int d = 0; d < 4; d++) {
                int py = pos.y + dy[d];
                int px = pos.x + dx[d];

                if (isInRange(py, px) && board[py][px] == 1 && !visited[py][px]) {
                    visited[py][px] = true;
                    q.add(new Pos(py, px));
                }
            }
        }
        //그림의 부분을 다 찾았기에, 그림 개수++, 최대 그림 크기를 갱신
        pictureCount++;
        widestPicture = Math.max(widestPicture, size);
    }

    //board의 범위안에 들어오는지를 함수로 뺴서 쓰는게 더 편함
    private static boolean isInRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < M);
    }
}