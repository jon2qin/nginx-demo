# Nginx-Demo
This project demonstrates common Nginx concepts with code to facilitate review and understanding.

---
# 基础知识

---

## 正向代理和反向代理
- 正向代理就是一个人发送一个请求直接就到达了目标的服务器
- 反方代理就是请求统一被Nginx接收，nginx反向代理服务器接收到之后，按照一定的规则分发给了后端的业务处理服务器进行处理

## Nginx怎么处理请求的？
nginx接收一个请求后，首先由listen和server_name指令匹配server模块，再匹配server模块里的location，location就是实际地址

```config
server {                                    # 第一个Server区块开始，表示一个独立的虚拟主机站点
    listen       80；                        # 提供服务的端口，默认80
    server_name  localhost；                 # 提供服务的域名主机名
    location / {                            # 第一个location区块开始
        root   html；                        # 站点的根目录，相当于Nginx的安装目录
        index  index.html index.htm；        # 默认的首页文件，多个用空格分开
    }                                       # 第一个location区块结果
}
```
## location语法

```table
匹配符             匹配规则                优先级
=               精确匹配                    1
^~              以某个字符串开头             2
~               区分大小写的正则匹配          3
~*              不区分大小写的正则匹配        4
!~              区分大小写不匹配的正则        5
!~*             不区分大小写不匹配的正则       6
/               通用匹配，任何请求都会匹配到    7
```

## nginx常见状态码500、502、503、504

- 500： Internal Server Error 内部服务错误，比如脚本错误，编程语言语法错误。
- 502： Bad Gateway错误，网关错误。比如服务器当前连接太多，响应太慢，页面素材太多、带宽慢。
- 503： Service Temporarily Unavailable，服务不可用，web服务器不能处理HTTP请求，可能是临时超载或者是服务器进行停机维护。
- 504： Gateway timeout 网关超时，程序执行时间过长导致响应超时，例如程序需要执行20秒，而nginx最大响应等待时间为10秒，这样就会出现超时
---

# Demo

---

## 1. HTTP服务器（代理静态资源）
操作参考[http-server.md](docs/http-server.md)

## 2. 虚拟主机
操作参考[virtual-host.md](docs/virtual-host.md)

## 3. 反向代理，负载均衡
- 轮询
- 加权轮询
- IP哈希
- 最少连接
- 加权最少连接
- 扩展算法
  - URL哈希
  - 一致性哈希
  - fair算法

参考文档[proxy.md](docs/proxy.md)

## 4. gzip压缩后
参考文档[gzip-comp.md](docs/gzip-comp.md)

## 5. 访问限制
- 限制频率（正常/突发）
- 限制并发数
- 限制文件大小
- 限制IP
- 限制浏览器
- 限制爬虫
参考文档[limit-req.md](docs/limit-req.md)

## 6. 解决跨域
## 7. 静态文件缓存
## 8. 集群
- Keepalived + nginx
- 依赖K8S自动重启


