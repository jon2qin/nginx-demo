# HTTP服务器（代理静态资源）
使用nginx实现一个HTTP服务器，代理静态资源，实现静态文件的访问

## 1. 准备好静态文件
- [page1.html](../assets/static/page1.html)
- [page2.html](../assets/static/page2.html)

## 2. 准备好nginx的配置文件
- 配置文件[http-server.conf](../assets/http-server.conf)

## 3. 使用1、2准备好的配置文件启动nginx
在容器被移除的时候，所有资料都会丢失，这是不安全的，因此需要将关键数据挂载到卷上

```text
docker run -d \
    -p 80:80 \
    -v $PWD/assets/http-server.conf:/etc/nginx/nginx.conf \
    -v $PWD/assets/static:/tmp/static \
    --name http-server-nginx nginx
```

## 4. 在浏览器上访问资源,验证结果
- 验证alias配置是否访问正常: 
  - http://localhost/page1.html

- 验证root配置是否访问正常:
  - http://localhost/static/page2.html

- 动态添加文件(page3.html)到目录/xx/assets/static下，然后查看是否生效
  - http://localhost/static/page3.html
