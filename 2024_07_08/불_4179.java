package backJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불_4179 {
    static int R;
    static int C;
    static char[][] map;
    static int[] DY = {1, -1, 0, 0};
    static int[] DX = {0, 0, 1, -1};
    static Queue<Pos> q = new LinkedList<>();
    static boolean[][] visited;
    static class Pos {
        int y;
        int x;
        //불인지 지훈이 있지 확인하는 변수
        int type;
        int time;

        Pos(int y, int x, int type, int time) {
            this.y = y;
            this.x = x;
            this.type = type;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        Pos ji = null;

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'J') {
                    if (i == 0 || i == R - 1 || j == 0 || j == C - 1) {
                        //바로 탈출할 수 있는 경우
                        System.out.println(1);
                        return;
                    }
                    //지훈이의 자리를 .으로 바꾼다 -> map에서 J에 대한 것을 고려하지 않기 위해
                    map[i][j] = '.';
                    ji = new Pos(i, j, 0,0);
                }
                if (map[i][j] == 'F') {
                    //불의 위치도 q에 넣어놓는다
                    q.add(new Pos(i, j, 1,0));
                }
            }
        }

        /**
         * 처음 지훈이의 위치를 파라미터로 넣고 bfs()를 시작한다
         * Q에 지훈이의 첫 위치를 넣고, 그 자리를 방문한 것으로 표시한다
         * Q가 비어있을 때까지, 불의 점이와 지훈이의 이동을 실행한다
         * 만약 지훈이가 맵에서 탈출 할 수 있다면, 바로 출력을 하고 함수를 종료한다
         * 4방향으로 이동여부를 체크하고, 이동할 수 있다면 지훈이인지 불인지 확인하고 Q에 넣는다
         * 지훈이일 경우 : visited를 true로 설정한다
         * 불일 경우 : map의 요소를 F로 설정한다.
         * Q가 비어 이동이 끝났는데, 함수가 끝나지 않았으면 이동 불가능하기에 impossible을 출력한다.
         */
        bfs(ji);
    }

    static void bfs(Pos ji) {
        q.add(ji);
        visited[ji.y][ji.x] = true;

        while (!q.isEmpty()) {
            Pos p = q.poll();
            int time = p.time + 1;
            if ((p.y == 0 || p.y == R -1 || p.x == 0 || p.x == C - 1) && p.type == 0) {
                System.out.println(time);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int ny = p.y + DY[d];
                int nx = p.x + DX[d];

                if (ny < 0 || ny >= R || nx < 0 || nx >= C || map[ny][nx] == '#' || map[ny][nx] == 'F') {
                    continue;
                }
                if (p.type == 0 && !visited[ny][nx]) {
                    q.add(new Pos(ny, nx, 0, time));
                    visited[ny][nx] = true;
                } else {
                    q.add(new Pos(ny, nx, 1, time));
                    map[ny][nx] = 'F';
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}
