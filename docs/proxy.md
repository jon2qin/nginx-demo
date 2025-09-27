# 反向代理，负载均衡

---

## 轮询

#### 1. 先准备可访问的服务
  - 在本地分别启动多个服务实例，模拟集群
  - 为了方便观察轮询的结果，需将同一个接口路径/api/test/hello设置不同的返回值或阻塞不同的时间
  - 确保服务正常访问
    - http://192.168.10.154:8081/api/test/hello
    - http://192.168.10.154:8082/api/test/hello
    - http://192.168.10.154:8083/api/test/hello

#### 2. 准备好配置文件
  - [round.conf](../assets/round.conf)

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

## 加权轮询

#### 1. 先准备可访问的服务
与轮询方式一致

#### 2. 准备好配置文件
- [proxy-weighted.conf](../assets/round-weighted.conf)

#### 3. 使用配置文件启动nginx
与轮询方式一致

#### 4. 验证结果（多次访问检查是否按照权重轮流访问）
http://localhost/api/test/hello

---

## IP哈希(由于条件有限，没有多台IP设备访问，未能实际验证)

#### 1. 先准备可访问的服务
与轮询方式一致

#### 2. 准备好配置文件
- [hash.conf](../assets/hash.conf)

#### 3. 使用配置文件启动nginx
与轮询方式一致

#### 4. 验证结果（多次访问检查是否按照权重轮流访问）
http://localhost/api/test/hello

---

## 最少连接

#### 1. 先准备可访问的服务
与轮询方式一致, 只是让每个访问在服务端堵塞一段时间来增加连接的时长，以便持有服务

#### 2. 准备好配置文件
- [least.conf](../assets/least.conf)

#### 3. 使用配置文件启动nginx
与轮询方式一致

#### 4. 验证结果（多次访问检查是否符合预期）
http://localhost/api/test/hello

---

## 加权最少连接

#### 1. 先准备可访问的服务
与轮询方式一致, 只是让每个访问在服务端堵塞一段时间来增加连接的时长，以便持有服务

#### 2. 准备好配置文件
- [least-weighted.conf](../assets/least-weighted.conf)

#### 3. 使用配置文件启动nginx
与轮询方式一致

#### 4. 验证结果（多次访问检查是否符合预期）
http://localhost/api/test/hello

---

## 扩展算法
  - URL哈希
  - 一致性哈希
  - fair算法 

扩展算法不过度探索，简单步骤如下

    1. 安装fair算法模块
    2. 重新编译nginx，把模块加到nginx
    3. 启动模块，参考配置文件[fair.conf](../assets/fair.conf)
    4. 其他步骤跟轮询类似
  
