## 동전_0 11047
### 소요시간
10분

### 간단 풀이 방식
- 동전 지갑을 내림 차순으로 정렬한다
- 동전으로 바꾸어야하는 가격 보다 작은 동전을 사용하여, 최대로 바꾼다. -> 이 과정을 반복하며, 동전 수를 count 한다.
- 즉, 그리디하게 풀면 된다!

### pseudo code
```
for (Integer coin : wallet) {
            if (K == 0) {
                break;
            }
            if (coin > K) {
                continue;
            }
            while (K >= coin) {
                K -= coin;
                answer++;
            }
        }
```

### 메모리 및 시간
- 14340kb
- 252ms (좀... 느린거 같기도...)

## 그림_1926
### 소요시간
20분

### 간단 풀이 방식
- board을 순회하면서, 값이 '1'이고 방문하지 않은 곳이라면, 그림의 첫 시작으로 생각하고 BFS를 통해 그림의 크기를 구한다.
- 그림은 동서남북 4방향으로 이어져 있어야 하기에, 그림 시작 점의 4곳을 확인한 후, board 안에 있고 방문하지 않은 지점이라면 Queue에 넣는다. -> 이 방식을 Queue가 비어있을 떄 까지 진행
- Queue가 비어, 그림의 크기를 구했다면 그림의 최댓값을 갱신한다.
- 다음 그림을 찾아 board를 순회한다.

### pseudo code
board 순회
```
for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
        if (board[i][j] == 1 && !visited[i][j]) {
            // 방문하지 않았고, 좌표 값아 '1'인 경우 그림이 시작하는 (i, j) 값으로 그림의 크기를 구함
            bfs(new Pos(i, j));
        }
    }
}
```
그림 크기 확인
```
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
```

### 메모리 및 시간
- 45700kb
- 440ms

## 회의실_배정 1931
### 소요시간
30분

### 간단 풀이 방식
- 주어진 회의 시간을 끝나는 시간 기준으로 오름 차순으로 정렬한다. 끝나는 시간이 같다면, 시작시간이 빠른 것으로 선택한다.
- 회의시간표 순회하면서, 진적 회의가 끝나는 시간보다 시작 시간이 클 경우 회의 가능한 것으로 판단하고 회의 끝나는 시간을 갱신하고 가능한 회의 시간으로 체크한다.
- 

### pseudo code
```
for (int i = 0; i < N; i++) {
    if (endTime <= board[i][0]) {
        endTime = board[i][1];
        answer++;
    }
}
```

### 메모리 및 시간
- 43308kb
- 524ms (많이 느린듯...)