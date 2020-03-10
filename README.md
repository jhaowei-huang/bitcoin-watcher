# 比特幣即時價格查看(bitcoin-watcher)

![img](https://i.imgur.com/W5cuIBC.png)

### 安裝與使用

- 透過 Docker Image 
  - `docker pull jhaoweihuang/bitcoin-watcher`
  - `docker run jhaoweihuang/bitcoin-watcher`
  - 開啟瀏覽器，前往 http://localhost:8080/

- 透過 git clone 專案並使用 Maven 執行
  - `git clone https://github.com/jhaowei-huang/bitcoin-watcher.git` 
  - `cd bitcoin-watcher`
  - `mvn spring-boot:run`
  
>
> 程式預設使用 8080 port，若 8080 port 已被占用請更換 port
>
> 若你是透過 Docker Image 更換 port 
> 
> - `docker run -p [PORT]:8080 jhaoweihuang/bitcoin-watcher`
> 
> 若你是透過 git clone 專案並使用 Maven 執行
>
> - 開啟 `/src/main/resources/application.properties`
>
> - 加入 `server.port = [PORT]`，儲存關閉
>
> - `mvn spring-boot:run`

### 介面說明

![img](https://i.imgur.com/j6cIs9J.png)

![#f03c15](https://placehold.it/15/f03c15/000000?text=+) A. 資料來源: 目前可以切換來源1, 2, 3

![#c5f015](https://placehold.it/15/c5f015/000000?text=+) B. 即時模式/歷史模式: 
  - 即時模式: 每當接收到新的價格變化後，以streaming方式呈現
  - 歷史模式: 當天00:00至現在時間歷史價格
  
![#f03c15](https://placehold.it/15/f03c15/000000?text=+) 紅框: WebSocket接收新的價格後，刷新價錢表格

![#1589F0](https://placehold.it/15/1589F0/000000?text=+) 藍框: 簡易的公開的聊天室，每次最多輸入60個字元

![#c5f015](https://placehold.it/15/c5f015/000000?text=+) 綠框: 即時模式/歷史模式的圖表

## API 說明

__GET /api/price?id=?__ ： 查看當前價格 

|參數|必要|可選值|
|----|----|----|
|id| true | 1, 2, 3|

__GET /api/historical/price__ ： 查看歷史價格

|參數|必要|可選值|
|----|----|----|
|id| true | 1, 2, 3|
|start| true | UNIX timestamp |
|end| false | UNIX timestamp, 預設是現在時間|
