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
```bash
$ nano kibana-6.2.4-linux-x86_64/config/kibana.yml
```
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
```bash
$ nano kibana-6.2.4-linux-x86_64/config/kibana.yml
```
```yml
server.host: 0.0.0.0
```
Para mudar a porta usar a propriedade no kibana-6.2.4-linux-x86_64/config/kibana.yml
```yml
server.port: 5601
```
# Instalação LogStash
* Baixe o Logstash
```bash
$ wget https://artifacts.elastic.co/downloads/logstash/logstash-6.2.4.tar.gz
```
* Descompacte o logstash
```bash
$ tar -xzf logstash-6.2.4.tar.gz
```
* Execute o logstash(Demora um pouco para aparecer algo no console)
```bash
$ logstash-6.2.4/in/logstash -e 'input { stdin { } } output { stdout {} }'
```
* Após isso digite alguma coisa para ver a saida 
```
hello word
```
* Uma opção a esse longo comando é criar um arquivo qualquercoisa.conf na raiz do logstash como esse 
```conf
input {
    beats {
        port => "5044"
    }
}
# The filter part of this file is commented out to indicate that it is
# optional.
# filter {
#
# }
output {
    elasticsearch { hosts => ["10.13.29.71:9200"] }
}
```
* E executar o logstash assim 
```bash
bin/logstash -f qualquercoisa.conf --config.reload.automatic
```
# FileBeat

* Para habilitar o log, vá em filebeat.yml e altere a propriedade enable para true
```yml
filebeat.prospectors:

# Each - is a prospector. Most options can be set at the prospector level, so
# you can use different prospectors for various configurations.
# Below are the prospector specific configurations.

- type: log

  # Change to true to enable this prospector configuration.
  enabled: true
```
* Depois diga o arquivo de log que deseja que o filebeat escanee e mande para o elasticsearch na propriedade path
```yml
filebeat.prospectors:

# Each - is a prospector. Most options can be set at the prospector level, so
# you can use different prospectors for various configurations.
# Below are the prospector specific configurations.

- type: log

  # Change to true to enable this prospector configuration.
  enabled: true

  # Paths that should be crawled and fetched. Glob based paths.
  paths:
    - /var/log/auth.log
    #- c:\programdata\elasticsearch\logs\*
```
* Se for preciso altere o endereço/link do kibana na propriedade host do filebeat.yml
```yml
#============================== Kibana =====================================

# Starting with Beats version 6.0.0, the dashboards are loaded via the Kibana API.
# This requires a Kibana endpoint configuration.
setup.kibana:

  # Kibana Host
  # Scheme and port can be left out and will be set to the default (http and 5601)
  # In case you specify and additional path, the scheme is required: http://localhost:5601/path
  # IPv6 addresses should always be defined as: https://[2001:db8::1]:5601
  host: "192.168.100.242:5601"
```
* Se for preciso altere a propriedade hosts do output.elasticsearch do arquivo filebeat.yml
```yml
#-------------------------- Elasticsearch output ------------------------------
output.elasticsearch:
  # Array of hosts to connect to.
  hosts: ["192.168.100.242:9200"]
  ```
  * Depois de realizar as configurações, execute o filebeat
  ```bash
  sudo ./filebeat -e -c filebeat.yml -d "publish"
  ```
# Importante
* Se ao executar o filebeat e houver algum como esse 
```bash
Exiting: error loading config file: config file ("filebeat.yml") must be owned by the beat user (uid=0) or root
```
* Execute um desses dois comando 
```bash
chown root {beatname}.yml
```
* ou 
```bash 
chown 501 {beatname}.yml
```
# Instalação do XPack para elasticsearch

# Instalação do XPack para kibana

* baixe o xpack: https://www.elastic.co/guide/en/kibana/6.2/installing-xpack-kb.html

* Extraia o arquivo
```bash 
$ unzip -x x-pack-6.2.4.zip 
```
* Mova o arquivo x-pack dentro da pasta kibana que apareceu depois da descompactação do x-pack para a pasta plugins do kibana
```bash
$ mv kibana/x-pack/ ./kibana-6.2.4-linux-x86_64/plugins/
```
* Execute este comando para verificar se o x-pack foi instalado
```bash
$ .bin/kibana-plugin list
```
* A saida deve ser parecida com essa 
```bash 
x-pack@6.2.4
```
* Abra o arquivo kibana.yml dentro de config
```bash
$ nano config/kibana.yml 
```
* Adicione/Descomente as seguintes linhas e defina um usuario e senha
```yml 
elasticsearch.username: "kibana"
elasticsearch.password: "kibanapassword"
```
* Troque o usuario da pasta optimize para o seu 
```bash
$ chown -R seuuser:seuuser /path/to/kibana/optimize
```
* Execute o kibana e espere ele se otimizar e organizar as coisas(Demora um pouco)
```bash
$ ./bin/kibana
```
