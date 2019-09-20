# DitoServices

Desenvolvimento de serviço para armazenamento de eventos realizados por usuários e criação de timeline frente a eventos ocorridos.

## Eventos

Os eventos são cadastrados através de uma WebAPI desenvolvida com SpringBoot através das semânticas do cabeçalhos de HTTP (HTTP Headers):

> GET - /api/event - Listar todos os eventos
> GET - /api/event/{id} - Retorna o evento de acordo com o {id}
> GET - /api/event/autocomplete/{texto} - Identificação de possíveis eventos que iniciam com o texto indicado
>> O evento somente retorna algo quando é informado algo com texto > 2 caracteres
> POST - /api/event - Cadastro de evento
> PUT - /api/event/{id} - Edição de evento previamente cadastrado identificado pelo {id}
> DELETE - /api/event/{id} - Remoção de evento previamente cadastrado identificado pelo {id}

## Timeline

Coleta informações de JSON através de um Endpoint inserido no código

> GET - /api/timeline - Listar todos os eventos ordenados pelo timestamp em ordem descrescente
