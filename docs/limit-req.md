# 访问限制

---

## 限制频率（正常/突发）
1. 先准备可访问的服务
   - 在本地分别启动多个服务实例，模拟集群
   - 确保服务正常访问
       - http://localhost:8081/api/hello
       - http://localhost:8082/api/hello
       - http://localhost:8083/api/hello   此服务为堵塞10秒的服务
2. 配置参数[limit-req.conf](../assets/limit-req.conf)
3. 按照参数启动
    ```text
    docker run -d \
       -p 80:80 \
       -v $PWD/assets/limit-req.conf:/etc/nginx/nginx.conf \
       --name limit-req-nginx nginx
    ```
4. 检查结果
   - http://localhost/api/hello 多次点击直接返回错误页面
   - http://localhost/api/test  多次点击后进入突发的容量里，如果还超过，那么进入错误页面

---

## 限制并发数
1. 跟“限制频率”一致
2. 参数配置[limit-conn.conf](../assets/limit-conn.conf)
3. 启动参数
    ```text
    docker run -d \
       -p 80:80 \
       -v $PWD/assets/limit-conn.conf:/etc/nginx/nginx.conf \
       --name limit-conn-nginx nginx
    ```
4. 检查结果
  - 同时发起 10 个请求（替换为你的URL）,观察到第三个并发请求会返回503
  ```text
    for i in {1..10}; do
    curl -s -o /dev/null -w "%{http_code}\n" http://localhost/api/hello &
    done
   ```

---

## 限制文件大小
## 限制IP
## 限制浏览器
## 限制爬虫