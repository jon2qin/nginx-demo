# 反向代理，负载均衡

## 轮询

#### 1. 先准备两个可访问的服务

  - 在本地分别启动服务A和B
  - 为了方便观察轮询的结果，需将两个服务的同一个接口路径/api/test/hello设置不同的返回值
  - 确保两个服务正常访问
    - http://192.168.10.154:8081/api/test/hello
    - http://192.168.10.154:8082/api/test/hello

#### 2. 准备好配置文件
  - [proxy.conf](../assets/proxy.conf)

#### 3. 使用配置文件启动nginx
```text
docker run -d \                     
  -p 80:80 \
  -v /xxx/assets/proxy.conf:/etc/nginx/nginx.conf \
  --name my-nginx nginx
```
#### 4. 验证结果（多次访问检查是否轮流访问）
http://localhost/api/test/hello

---

- 加权轮询
- IP哈希
- 最少连接
- 加权最少连接
- 扩展算法
  - URL哈希
  - 一致性哈希
  - fair算法

