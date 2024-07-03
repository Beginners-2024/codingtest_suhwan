package backJun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 감시 {
    static int N;
    static int M;
    static int[][] board;
    static int[][] copyBoard;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static List<Pos> ccTvs;
    static int[] outPut;
    static int answer = Integer.MAX_VALUE;
    static class Pos {
        int y;
        int x;
        int type; //cctv 번호

        Pos(int y, int x, int type) {
            this.y = y;
            this.x = x;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        ccTvs = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value > 0 && value < 6) {
                    ccTvs.add(new Pos(i, j, value));
                }
                board[i][j] = value;
            }
        }

        outPut = new int[ccTvs.size()];
        dfs(0);

        System.out.println(answer);
    }

    private static void dfs(int depth) {
        if (depth == ccTvs.size()) {
            copyBoard = new int[N][M];
            for (int i = 0; i < N; i++) {
                System.arraycopy(board[i], 0, copyBoard[i], 0, M); // 각 방향에서 사용할 map
            }

            for (int i = 0; i < ccTvs.size(); i++) {
                direction(ccTvs.get(i), outPut[i]);
            }

            getEmptySpace(); //bfs를 통해 '0'의 갯수 확인
            return;
        }
        for (int i = 0; i < 4; i++) {
                outPut[depth] = i;
                dfs(depth + 1); // depth 늘려가며 cctv 방향 설정
        }
    }

    private static void direction(Pos cctv, int d) {
        int type = cctv.type;

        if (type == 1) {
            if (d == 0) {
                observe(cctv, 0);
            } else if (d == 1) {
                observe(cctv, 1);
            } else if (d == 2) {
                observe(cctv, 2);
            } else if (d == 3) {
                observe(cctv, 3);
            }
        } else if (type == 2) {
            if (d == 0 || d == 2) {
                observe(cctv, 0);
                observe(cctv, 2);
            } else {
                observe(cctv, 1);
                observe(cctv, 3);
            }
        } else if (type == 3) {
            if (d == 0) {
                observe(cctv, 0);
                observe(cctv, 1);
            } else if (d == 1) {
                observe(cctv, 1);
                observe(cctv, 2);
            } else if (d == 2) {
                observe(cctv, 2);
                observe(cctv, 3);
            } else {
                observe(cctv, 0);
                observe(cctv, 3);
            }
        } else if (type == 4){
            if (d == 0) {
                observe(cctv, 0);
                observe(cctv, 1);
                observe(cctv, 3);
            } else if (d == 1) {
                observe(cctv, 0);
                observe(cctv, 1);
                observe(cctv, 2);
            } else if (d == 2) {
                observe(cctv, 1);
                observe(cctv, 2);
                observe(cctv, 3);
            } else {
                observe(cctv, 0);
                observe(cctv, 2);
                observe(cctv, 3);
            }
        } else {
            observe(cctv, 0);
            observe(cctv, 1);
            observe(cctv, 2);
            observe(cctv, 3);
        }
    }

    private static void observe(Pos cctv, int d) {
        Queue<Pos> q = new LinkedList<>();

        q.add(cctv);
        while(!q.isEmpty()) {
            Pos p = q.poll();
            int ny = p.y + dy[d];
            int nx = p.x + dx[d];

            if (!isInRange(ny, nx)) {
                 break;
            }
            if (copyBoard[ny][nx] == 0) {
                copyBoard[ny][nx] = -1;
            }
            q.add(new Pos(ny, nx, cctv.type));
        }
    }

    private static void getEmptySpace() {
        int space = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyBoard[i][j] == 0) {
                    space++;
                }
            }
        }
        answer = Math.min(answer, space);
    }

    private static boolean isInRange(int y, int x) {
        return (y >= 0 && y < N) && (x >= 0 && x < M) && copyBoard[y][x] != 6;
    }
    
}