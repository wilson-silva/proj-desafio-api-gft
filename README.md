# Projeto Desafio API Starter #3 / Sistema Programa Starter

## Dependências utilizadas no projeto

* Spring Boot DevTools
* Lombok
* Spring Web
* Spring Data Jpa
* Validation
* Flyway Migration
* Spring Security
* java-jwt
* mysql-connector-java
* springfox-swagger2(2.9.2)
* springfox-swagger-ui(2.9.2)
* spring-boot-starter-mail


Obs: Versão do Java 17


## Banco Populado

Banco já está populado com flyway, só rodar o projeto

-> categoria(tecnologia, nome)

* ('Java', 'Turma1-2022')
* ('C#', 'Turma2-2022')
* ('Python', 'Turma3-2022')
* ('JavaScript', 'Turma4-2022')
* ('Ruby', 'Turma5-2022')
* ('Go', 'Turma6-2022')

-> starter(nome, cpf, quatro_letras, email) 

* ('Wilson Silva', '956.438.968-26','wngv','wilson@hotmail')
* ('Ana Maria', '553.573.078-57','anma','anamaria@hotmail')
* ('Francislaine Cruz', '776.248.308-096','facr','francislaine@hotmail')

-> usuario(usuario, senha, perfil_id)

* ('admin','Gft@1234',1)
* ('usuario1','Gft@1111',2)
* ('usuario2','Gft@2222',2)

-> perfil(nome) 

* 1 - ('ADMIN')
* 2 - ('USER')


## Permissões de acesso

'USER' só acessam a lista de categoria e starter.
'ADMIN' acessa tudo.
Obs: Só o 'ADMIN'tem acesso ao mapeamento/usuarios.


ADMIN:
````
{
    "usuario":"admin",
    "senha":"Gft@1234"
}
````

USER:
```
{
    "usuario":"usuario1",
    "senha":"Gft@1111"
}
```

```
{
    "usuario":"usuario2",
    "senha":"Gft@2222"
}
```



# Exceeds

## CPF

CPF validado a partir da anotação do Validation @CPF

Site utilizado para gerar cpf
https://www.calculadorafacil.com.br/computacao/gerador-de-cpf


## Email

Ao se autenticar no app e disparado um email configurado no application.properties 
que é o meu particular, para teste voce pode trocar pelo seu email
e tambem trocar na classe AutenticacaoController.
Obs: Usei o Gmail, é preciso acessar suas configuraçõesdo gmail -> Segurança ->
como fazer login no google -> senhas de app (Gerar senha la criptografada para colocar no
password no appication.properties. 



## Swagger endpoints / Upload de imagem

Acesso: localhost:8080/swagger-ui.html

Obs: para autenticar endpoints apos gerar o token deve colocar "Bearer" no campo
"Header para token JWT" antes do token:

Bearer xxxxx...


Para testar o Post Criar Starter colar este json e fazer upload da imagem
```
{
    "nome": "teste",
    "cpf": "102.929.878-54",
    "quatroLetras": "tttt",
    "email": "teste@hotmail"
}
```
OBS: Para upload deixei algumas imagens na pasta resouces/imagem para utilizar, após o upload
deixei configurado para salvar na pasta resource/static.imagens do proprio projeto ok.


## Postman

Caso teste pelo o Postaman estarei enviando a expostação do arquivo do Postman de nome
"desafio-api.postman_collection.json".








