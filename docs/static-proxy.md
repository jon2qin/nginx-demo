# HTTP服务器（代理静态资源）
使用nginx实现一个HTTP服务器，代理静态资源，实现静态文件的访问

## 1. 准备好静态文件
- [hello world网页](../assets/static/hello%20world.html)
- [nginx图标](../assets/static/nginx.png)

## 2. 准备好nginx的配置文件
- 主配置文件[nginx.conf](../assets/nginx01.conf)
- 子配置文件[default.conf](../assets/default01.conf)

## 3. 使用1、2准备好的配置文件启动nginx
在容器被移除的时候，所有资料都会丢失，这是不安全的，因此需要将关键数据挂载到卷上
- 配置文件挂载: 
  - 主配置文件 /xx/assets/nginx01.conf:/etc/nginx/nginx.conf
  - 子配置文件: /xx/assets/default01.conf:/etc/nginx/conf.d/default.conf
- 静态资源挂载
  - /xx/assets/static:/tmp/static
- 日志文件目录挂载
  - /xx/log:/var/log/nginx

## 4. 在浏览器上访问资源,验证结果
- 验证root配置是否访问正常: 
  - localhost:8090/static/hello world.html
  - localhost:8090/static/nginx.png
- 验证alias配置是否访问正常: 
  - localhost:8090/static/image
- 添加静态文件(aa.png)到目录/xx/assets/static下，然后查看是否生效
  - localhost:8090/static/aa.png
