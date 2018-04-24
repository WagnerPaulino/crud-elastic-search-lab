# crud-elastic-search-lab
projeto exemplo para crud usando elasticSearch
# Instalação do ElasticSearch
* Instale o CURL 
```bash
#para debian e derivados
$ sudo apt install curl
```
```bash
#para fedora e derivados
$ sudo dnf install curl
```
* Baixe o elasticSearch tar gz para uma pasta desejada
```bash
$ curl -L -O https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-6.2.4.tar.gz
```

* Extraia os arquivos
```bash
$ tar -xvf elasticsearch-6.2.4.tar.gz
```

* Caso queira configurar o host e a porta em que o elasticSearch vai iniciar, var no diretorio elasticsearch-6.2.4/config e abra o arquivo elasticsearch.yml
```bash
$ nano elasticsearch-6.2.4/config/elasticsearch.yml
```
* Descomente/Adicione as linhas semelhantes a essas:
```yml
network.host: 192.168.100.242
http.port: 9200
```
Para alterar a porta que a aplicação devera utilizar para se comunicar com o elasticsearch(O padrão é 9300)
```yml
http.publish_port: 9300
```
* Para nomear o cluster
```yml
cluster.name: myClusterName
```

* Executar o elasticsearch
```bash
$ cd elasticsearch-6.2.4/bin
$ ./elasticsearch
```
* Caso haja algum erro como este:
```console
elasticsearch_1 | ERROR: bootstrap checks failed
elasticsearch_1 | max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
```
* Execute este comando como sudo(root/superUsuario)
```bash
$ sudo sysctl -w vm.max_map_count=262144
```
