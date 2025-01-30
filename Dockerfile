# Use OpenJDK como base
FROM openjdk:17

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos do projeto para o container
COPY . /app

# Cria o diretório de saída para os arquivos compilados
RUN mkdir -p out

# Compila todos os arquivos Java manualmente
RUN javac -d out $(ls *.java */*.java */*/*.java 2>/dev/null || echo "")

# Define o ponto de entrada para executar o programa
CMD ["java", "-cp", "out", "org.example.Main"]
