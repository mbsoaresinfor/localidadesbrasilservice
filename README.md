Este projeto tem o intuito de apresentar o desenlvolvimento de um servidor que busca os
dados dos municipios do Brasil e envia por email para os clientes.

# Documentação da api
http://localhost:8080/swagger-ui.html

# Executar o Servidor
classe: br.com.mbs.localidadesbrasilservice.StartService

# Requisitos
java version 8+

# Configuracoes
O projeto pode ser executado no docker (arquivo já está pronto).
Configurar os dados de envio de email no arquivo (applicattion.properties)
Tambem definir a quantidade de cliente cadastrado (campo: cliente.total).
E tambem, um email, para onde vai ser enviado os emails (campo: cliente.email)

# Tecnologias
Spring Boot, Spring batch, Banco de Dados H2,jpa, Javax.Mail, ItextPdf, hystrix,Docker

# Arquitetura
A api possui dois endPoint, que realiza o envio dos municiopios do Brasil para o email do clientes.
A busca dos municipios é feito no inicio do serviço e deixado armazenado no h2 (memória), visto
que esses dados não mudam toda hora, por isso realizar no inicio e deixar em cache.
Existe o tratamento do circuit breaker implementando, no acesso junto a apit do IBGE. Caso não consiga
acesso, ele busca os dados em memoria. Se nao tiver dados, mostra erro para o usuario.
O primeiro endpoint
http://localhost:8080/enviaemail/
Busca todos os clientes cadastrados e envia os municipios para cada email cadastrado do cliente.
Esse envio é feito de forma em lote, usando o springo batch.
Esse processo é assincrono, pois pode demorar muito.

O segundo endpoint
http://localhost:8080/enviaemail/{id_cliente}
Busca as informacoes do cliente e envia os municipios para o email do mesmo.
Processo sincrono.



