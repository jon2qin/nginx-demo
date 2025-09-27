# 第一阶段：构建阶段（使用 Maven 镜像编译项目）
FROM maven:3.8.5-eclipse-temurin-17 AS builder

# 设置构建阶段工作目录
WORKDIR /app

# 复制pom.xml到容器
COPY pom.xml .

# 提前下载所有依赖到本地缓存（仅pom.xml变更时执行）
RUN mvn dependency:go-offline

# 复制源代码（源代码变动不影响上面的依赖层）
COPY src ./src

# 打包项目（跳过测试）
RUN mvn clean package -DskipTests


# 第二阶段：运行阶段（仅包含 JRE，体积更小）
FROM eclipse-temurin:17-jre-alpine

# 设置运行阶段工作目录
WORKDIR /app

# 从构建阶段复制打包好的 JAR 到当前阶段
COPY --from=builder /app/target/app.jar /app.jar

# 暴露端口
EXPOSE 8080

# 启动命令
ENTRYPOINT ["java", "-jar", "/app.jar"]