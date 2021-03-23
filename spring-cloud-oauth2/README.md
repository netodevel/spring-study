# Spring cloud with oauth2


### Este repositório contém:

* um serviço de configuração único, [aqui](https://github.com/NetoDevel/config-server)
* Service Registry com Eureka
* Zuul Api Gateway (Não está funcionando com oauth2)
* um serviço de autenticação com oauth2
* um serviço de usuários


Para executar o projeto é necessário iniciar nesta ordem: serviço de configuração, eureka, autenticação, users.
Este projeto salva tokens em memoria, e usuários no postgres.

Apenas criar o database chamado `oauth` (o hibernate irar criar as tabelas)
Para acessar os endpoitns de usuário é necessário solicitar um token em:


### POST (serviço de autenticação com oauth2)
      http://localhost:9094/oauth/token?grant_type=password&username=admin&password=123
  
 #### HEADER
      Authorization Basic bW9iaWxlOjEyMw==
    
   
 #### Response
```json
{
  "access_token": "92d7286e-ebc6-4561-ac35-a7cd175eb384",
  "token_type": "bearer",
  "refresh_token": "f0932ab3-2102-4103-9d3a-962aa3cc1066",
  "expires_in": 43199,
  "scope": "bar read write"
}
```

Esté usuário já é previamente cadastrado quando starto a aplicação.


### Post (Criar usuário)
    localhost:8083/users
    
  #### HEADER
        Authorization Bearer 92d7286e-ebc6-4561-ac35-a7cd175eb384
        Content-Type application/json
        
  ### BODY
  ```json
 {
      "name": "João da Silva",
      "email": "josevieira.dev@gmail.com",
      "password": "hunter2",
      "phones": [
          {
              "number": "987654321",
              "ddd": "21"
          }
      ]
  }
  ```
  ### Response
  
  ```json
{
    "name": "João da Silva",
    "email": "josevieira.dev@gmail.com",
    "password": "hunter2",
    "phones": [
        {
            "id": 4,
            "ddd": "21",
            "number": "987654321",
         }
    ],
    "createdAt": 1529156697839,
    "updatedAt": null
}
  ```
  ### GET (perfil usuário)
    localhost:8083/3
    
  #### HEADER
        Authorization Bearer 92d7286e-ebc6-4561-ac35-a7cd175eb384
    
  #### Response
```json
{
    "name": "João da Silva",
    "email": "josevieira.dev@gmail.com",
    "password": "hunter2",
    "phones": [
          {
            "ddd": "21",
            "number": "987654321"
          }
    ],
    "createdAt": 1529156697839,
    "updatedAt": null
}
```
      
#### Observações
  
* As rotas estão diretas no microserviços porque não consegui ainda fazer o zuul funcionar com oauth2.



