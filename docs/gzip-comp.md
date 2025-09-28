# gzip压缩

---

## 1. 开启配置
 - 配置文件[gzip-comp.conf](../assets/gzip-comp.conf)

## 2. 启动项目
```text
docker run -d \
-p 80:80 \
-v $PWD/assets/gzip-comp.conf:/etc/nginx/nginx.conf \
--name gzip-comp-nginx nginx
```

## 3. 检查结果

执行一下命令，查看到“Content-Encoding: gzip”
```text
curl -I -H "Accept-Encoding: gzip, deflate" http://localhost/index.html
```
