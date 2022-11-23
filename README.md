# Sistema de cadastramento de clientes em um banco virtual

## Contexto: Solicitação inicial de abertura de conta para pessoa física.

### Etapa 1: Informações básicas do Cliente
* nome -> obrigatório
* sobrenome -> obrigatório
* e-mail -> obrigatório
* data de nascimento -> obrigatório e no formato xxx@xxxx.xxx
* cpf -> obrigatório -> obrigatório, no passado e maior de 18 anos

#### Resultado esperado:

  |Status|Descrição                   |Retorno                    |
  |------|----------------------------|---------------------------|
  | 201  |CREATED                     |header location preenchido |
  | 400  |FALHA DE QUALQUER VALIDAÇÃO |JSON com as informações    |

### Etapa 2: dados do Endereço do possível cliente
* cep -> obrigatório e no formato adequado
* rua -> obrigatório
* bairro -> obrigatório
* complemento -> obrigatório 
* cidade -> obrigatório 
* estado -> obrigatório

#### Resultado esperado:

  |Status|Descrição                   |Retorno                    |
  |------|----------------------------|---------------------------|
  | 201  |CREATED                     |header location preenchido |
  | 400  |FALHA DE QUALQUER VALIDAÇÃO |JSON com as informações    |

### Etapa 3: envio de foto da parte frontal do cpf
* arquivo -> obrigatório

#### Resultado esperado:

  |Status|Descrição                   |Retorno                    |
  |------|----------------------------|---------------------------|
  | 201  |CREATED                     |header location preenchido |
  | 400  |FALHA DE QUALQUER VALIDAÇÃO |JSON com as informações    |
  | 404  |PROPOSTA INEXISTENTE        |                           |
  | 422  |PASSOS ANTERIORES FALTANTES |                           |
  

### Etapa 4: Mostrar os dados cadastrados para que seja feito o aceite
* Validar os passos anteriores

#### Resultado esperado:

* Aceite realizado -> envio de email para o cliente com a informação de que a conta será criada.
* Aceite não realizado -> armazena a proposta e envia e-mail para convencimento.

|Status|Descrição                   |Retorno                    |
|------|----------------------------|---------------------------|
| 201  |CREATED                     |header location preenchido |
| 422  |PASSOS ANTERIORES FALTANTES |                           |


### Etapa 5: criação da conta
* agência
* conta
* código do banco
* a partir da conta, precisa-se chegar na proposta que a originou
* saldo da conta zerado

#### Resultado esperado:

|Status|Descrição                   |Retorno                    |
|------|----------------------------|---------------------------|
| 201  |CREATED                     |header location preenchido |


## Tarefas Realizadas:

- [x] Etapa 1
- [x] Etapa 2
- [x] Etapa 3
- [ ] Etapa 4
    - [x] Etapa 4.1 - Aceite realizado, envio do e-mail
    - [ ] Etapa 4.2 - Aceite não realizado, armazenamento da proposta e envio de email para convencimento.
- [ ] Etapa 5

**header location não preenchidos (~~microsserviços~~)**






