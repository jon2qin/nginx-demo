# 虚拟主机
可以实现在一台服务器虚拟出多个网站。例如个人网站使用的虚拟主机

---

## 1. 基于IP的虚拟主机配置
- 准备好两个静态页面
  - [page1.html](../assets/static/page1.html)
  - [page2.html](../assets/static/page2.html)

- 准备好配置文件
  - [ip.conf](../assets/ip.conf)

- 使用上面准备好的配置文件启动nginx

```text
docker run -d \
    -p 192.168.1.100:80:80 \
    -p 192.168.1.101:80:80 \
    -v $PWD/assets/ip.conf:/etc/nginx/conf.d/default.conf \
    -v $PWD/assets/static/page1.html:/usr/share/nginx/html/ip1/page1.html \
    -v $PWD/assets/static/page2.html:/usr/share/nginx/html/ip2/page2.html \
    --name virtual-nginx nginx
```

- 访问页面验证结果(IP问题，没有能演示成功)
  - 192.168.1.100:80/page1.html
  - 192.168.1.101:80/page2.html

---

## 2. 基于不同端口的虚拟主机配置
- 准备好两个静态页面
  - [page1.html](../assets/static/page1.html)
  - [page2.html](../assets/static/page2.html)

- 准备好配置文件
  - [port.conf](../assets/port.conf)

- 使用上面准备好的配置文件启动nginx

```text
docker run -d \
    -p 8081:8081 \
    -p 8082:8082 \
    -v $PWD/assets/port.conf:/etc/nginx/conf.d/default.conf \
    -v $PWD/assets/static/page1.html:/usr/share/nginx/html/ip1/page1.html \
    -v $PWD/assets/static/page2.html:/usr/share/nginx/html/ip2/page2.html \
    --name virtual-nginx nginx
```

- 访问页面验证结果
  - http://localhost:8081/page1.html
  - http://localhost:8082/page2.html

---

## 3. 基于不同域名的虚拟主机
- 准备好两个静态页面
  - [page1.html](../assets/static/page1.html)
  - [page2.html](../assets/static/page2.html)

- 需要准备好两个可用域名，如使用花生壳准备两个内网穿透域名,他们分别绑定8081和8082端口
  - http://11fp433it1466.vicp.fun
  - https://11fp433it1466.vicp.fun

- 准备好配置文件
  - [domain.conf](../assets/domain.conf)

- 使用上面准备好的配置文件启动nginx

```text
docker run -d \
    -p 8081:8081 \
    -p 8082:8082 \
    -v $PWD/assets/domain.conf:/etc/nginx/conf.d/default.conf \
    -v $PWD/assets/static/page1.html:/usr/share/nginx/html/domain1/page1.html \
    -v $PWD/assets/static/page2.html:/usr/share/nginx/html/domain2/page2.html \
    --name virtual-nginx nginx
```

- 访问页面验证结果
    - http://11fp433it1466.vicp.fun/page1.html
    - https://11fp433it1466.vicp.fun/page2.html
