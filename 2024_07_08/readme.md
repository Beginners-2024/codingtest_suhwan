## 토마토_7576
### 소요시간
30분

### 간단 풀이 방법
- bfs()을 통해서 저장된 익은 토마토를 순회하면서, 4방향에 익지 않은 토마토에게 영향을 준다
- Q에서 poll 할때 마다, 익는 기간을 ++ 해준다
- Q가 비워지면 토마토가 다 익은 것이기 때문에, 모든 바구니를 순회하면서, 익지 않은 토마토가 있는지 확인하고 결과를 return

### pseudo code
```java
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
```

### 메모리 및 시간
- 120364kb
- 596ms

## 불!_4179
### 소요시간
1시간

### 간단 풀이 방법
- 처음 지훈이의 위치를 파라미터로 넣고 bfs()를 시작한다
- Q에 지훈이의 첫 위치를 넣고, 그 자리를 방문한 것으로 표시한다
- Q가 비어있을 때까지, 불의 점이와 지훈이의 이동을 실행한다
- 만약 지훈이가 맵에서 탈출 할 수 있다면, 바로 출력을 하고 함수를 종료한다
- 4방향으로 이동여부를 체크하고, 이동할 수 있다면 지훈이인지 불인지 확인하고 Q에 넣는다
- 지훈이일 경우 : visited를 true로 설정한다
- 불일 경우 : map의 요소를 F로 설정한다.
- Q가 비어 이동이 끝났는데, 함수가 끝나지 않았으면 이동 불가능하기에 impossible을 출력한다.

### pseudo code
```java
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
```
### 메모리 및 시간
- 76628kb
- 460ms

## 미로탈출_2178
### 소요시간
5분

### 간단 풀이 방법
- 흔히 알려져 있는 bfs()을 사용한다.
- (N, M)에 도달했을 경우 이동한 칸의 min 값을 설정한다.

### pseudo code
```java
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
```

### 메모리 및 시간
- 16704kb
- 196ms