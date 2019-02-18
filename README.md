# Limite de Crédito - Backend
00. Para a execução do projeto creditlimit (backend) é necessário ter o "docker" instalador na máquina
01. Após ter feito download do repositório é necessário entrar no diretório do projeto;
cd ~/Download/creditlimit
02. Construir a imagem do  creditlimit (backend)
docker build -t creditlimit .
03. Para conferir se o mesmo foi criado executar o comando abaixo:
$ docker image ls
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
creditlimit         latest              0857b86c8791        46 seconds ago      143MB
openjdk             8-jdk-alpine        792ff45a2a17        11 days ago         105MB
04. É possivel notar que foi feito o download da imagem do openjdk e também criado o imagem do creditLimit (backend);
05. Após os passos anteriores terem rodado com sucesso, é necessário rodar imagem do creditLimit (backend);
docker run -p 8080:8080 --net bridge --rm creditlimit