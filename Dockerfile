# 构建阶段 - 使用 Maven 3.8.4 + JDK 8 编译项目
FROM maven:3.8.4-openjdk-8 AS build
WORKDIR /app

# 先复制 pom.xml，利用 Docker 缓存依赖层（避免源码变动时重复下载依赖）
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 再复制源代码并打包（跳过测试以加快构建）
COPY src ./src
RUN mvn package -DskipTests

# 运行阶段 - 使用 Eclipse Temurin JDK 8 的 Alpine 精简镜像（官方推荐替代 openjdk）
FROM eclipse-temurin:8-jre-alpine
WORKDIR /app

# 从构建阶段复制打包好的 jar 文件
COPY --from=build /app/target/*.jar app.jar

# 暴露 Railway 默认监听的端口
EXPOSE 8080

# 设置 JVM 最大堆内存为 400MB（为免费套餐 0.5GB 预留足够余量）
# 同时开启容器化优化参数，避免 JVM 误判内存限制
ENTRYPOINT ["java", "-Xmx400m", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=80.0", "-jar", "app.jar"]