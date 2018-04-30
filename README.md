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
# Importante
* Caso haja algum erro como este:
```console
elasticsearch_1 | ERROR: bootstrap checks failed
elasticsearch_1 | max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
```
* Execute este comando como sudo(root/superUsuario)
```bash
$ sudo sysctl -w vm.max_map_count=262144
```
* Caso haja algum erro como este: 
```Console
ERROR: [1] bootstrap checks failed
[1]: max file descriptors [4096] for elasticsearch process is too low, increase to at least [65536]
```
* Execute estes comandos
```console
$ sudo su
# ulimit -n 65536
# su elasticsearch
```
<!--
* Para habilitar a escrita e leitura, descomente/adicione estás propriedades no elasticsearch.yml
```yml
cluster.routing.allocation.disk.threshold_enabled: false
cluster.blocks.read_only_allow_delete: false
```
-->
# Instalação do Kibana
* Baixar o kibana para uma pasta desejada
```bash
$ wget https://artifacts.elastic.co/downloads/kibana/kibana-6.2.4-linux-x86_64.tar.gz
```
* Descompactar
```bash
$ tar -xzf kibana-6.2.4-linux-x86_64.tar.gz
```
* colocar a propriedade elasticsearch.url no arquivo kibana-6.2.4-linux-x86_64/config/kibana.yml
```yml
elasticsearch.url: http|https://ip|host:port
```
* Iniciar o kibana
```bash
./kibana-6.2.4-linux-x86_64/bin/kibana
```
<!-- elasticsearch.url:
    Default: "http://localhost:9200" The URL of the Elasticsearch instance to use for all your queries. -->
# Importante
Para mudar o host ip usar a propriedade no kibana-6.2.4-linux-x86_64/config/kibana.yml
```yml
server.host: 0.0.0.0
```
Para mudar a porta usar a propriedade no kibana-6.2.4-linux-x86_64/config/kibana.yml
```yml
server.port: 5601
```
