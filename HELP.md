#  Projeto Spring Boot com JWT, JPA e H2

Bem-vindo ao projeto! Este é um sistema construído com Spring Boot, utilizando JWT para autenticação, JPA para persistência de dados com um banco de dados H2 em memória, e Spring Security para controle de acesso.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **JWT (JSON Web Token)**: Para autenticação segura.
- **JPA (Java Persistence API)**: Para gerenciar dados de forma eficiente.
- **H2**: Banco de dados em memória leve e fácil de usar.
- **Spring Security**: Para configuração de segurança e controle de acesso.

## Estrutura do Projeto

### Endpoints

Os endpoints estão configurados para diferentes níveis de acesso. Aqui estão alguns exemplos:

- `POST /login`: Permite que qualquer usuário faça login (sem autenticação necessária).
- `POST /users`: Permite a criação de novos usuários (acesso livre).
- `GET /users`: Acesso permitido apenas para usuários com as roles `USERS` ou `MANAGERS`.
- `GET /managers`: Acesso permitido apenas para usuários com a role `MANAGERS`.

### Configuração de Segurança

O controle de acesso é definido na classe de configuração de segurança com o seguinte trecho:

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
            .requestMatchers("/h2-console/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/users").permitAll()
            .requestMatchers(HttpMethod.GET, "/users").hasAnyRole("USERS", "MANAGERS")
            .requestMatchers("/managers").hasAnyRole("MANAGERS")
            .anyRequest().authenticated()
        .and()
        .csrf().disable()
        .headers().frameOptions().sameOrigin();
}
```

## Acessando o H2 Console

Para verificar e manipular o banco de dados H2, acesse o console web através da seguinte URL:

```
http://localhost:8081/h2-console
```

### Credenciais

- **Usuário**: `sa`
- **Senha**: `sa`

## Testando com Postman

Sinta-se à vontade para usar o Postman para testar os endpoints. Aqui estão algumas dicas:

1. **Login**: Faça uma requisição POST para `/login` com as credenciais do usuario.
    `{"username":"jr","password":"1234"}`
2. **Criar Usuário**: Envie uma requisição POST para `/users` com os dados do novo usuário.
   `{"name":"Junior","username":"jr","password":"1234","roles":["USERS","MANAGERS"]}`
   `{"name":"Fanger","username":"fau","password":"1234","roles":["USERS"]}`
3. **Autorização do Usuários**: Realize uma requisição GET para `/users` usando o token JWT no cabeçalho de autorização.
4. **Autorização de Managers**: Teste o acesso ao endpoint `/managers` da mesma forma.

## Contribuições

Contribuições são bem-vindas! Se você encontrar um bug ou tiver sugestões de melhorias, sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença

Este projeto é de código aberto. Sinta-se à vontade para usar e modificar conforme necessário!

 🚀