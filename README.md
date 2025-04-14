# Login Service - Autenticação via JWT

Este projeto é um **microserviço de autenticação** que utiliza Spring WebFlux e JWT para validar credenciais de **médicos** e **enfermeiros**.

O fluxo de autenticação faz uso de chamadas HTTP para um microserviço externo responsável por fornecer os dados dos profissionais de saúde.

## Repositório de Dados
Os dados dos profissionais (médicos e enfermeiros) são obtidos via HTTP do microserviço que pode ser encontrado no repositório:

🔗 [https://github.com/Halleey/hospital_staff](https://github.com/Halleey/hospital_staff)

---

## Endpoints

| Rota                | Metodo | Descrição                                |
|---------------------|--------|---------------------------------------------|
| `/login/doctor`     | POST   | Login de Médico, gera um JWT personalizado. |
| `/login/nurse`      | POST   | Login de Enfermeiro, gera um JWT personalizado. |

---

## Exemplo de Request

### Login de Médico
```json
POST /login/doctor
{
  "username": "dr.john",
  "password": "senhaSegura"
}
```

### Login de Enfermeiro
```json
POST /login/nurse
{
  "username": "nurse.jane",
  "password": "senhaSegura"
}
```

---

## Geração do Token JWT
Após autenticar com sucesso, o microserviço retorna um token JWT com as seguintes claims:

### Para Médicos
- name
- crm
- expertise
- role

### Para Enfermeiros
- name
- registrationNumber
- shift
- role

Este token pode ser usado para autenticação e autorização em outras partes do ecossistema.

---

## Como usar
1. Configure o `application.yml` ou `.properties` com suas variáveis:
```properties
jwt.secret=SUA_SECRET_KEY
jwt.expiration=3600000
```
2. Suba o microserviço do `hospital_staff`.
3. Execute este microserviço e faça requests conforme as rotas expostas.

---

## Dependências principais
- Spring WebFlux
- jjwt (Java JWT)
- Reactor Core

---

## Observações
- Este projeto segue arquitetura de **microserviços**, desacoplando a lógica de autenticação do gerenciamento de usuários.
- Facilmente extensível para novas roles (ex: admin, recepcionista).

---

💡 **Contribuições**
Pull Requests são bem-vindos! Caso queira propor melhorias ou integração com novos perfis de profissionais, basta abrir uma issue.

---

Feito com 💙 para facilitar a segurança no ecossistema hospitalar!

