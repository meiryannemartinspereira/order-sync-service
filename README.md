# 📦 Order Service

Este projeto implementa um **serviço de pedidos (Order Service)** utilizando **Arquitetura Hexagonal (Ports and Adapters)**, com foco em **baixo acoplamento**, **alta testabilidade** e **facilidade de evolução tecnológica**.

A aplicação é organizada de forma que o **domínio e as regras de negócio não dependam de frameworks, bancos de dados ou mensageria**.

---

## 🧠 Visão Geral da Arquitetura

A Arquitetura Hexagonal separa o sistema em três camadas principais:

* **Domain** → Regras de negócio puras
* **Application** → Casos de uso
* **Adapters (In / Out)** → Integrações externas

```
adapter
├─ in
│   └─ web
│       └─ OrderController
│
├─ out
│   ├─ persistence
│   │   └─ mongo
│   │       └─ MongoOrderRepository
│   │
│   ├─ messaging
│   │   └─ kafka
│   │       └─ KafkaOrderEventPublisher
│   │
│   └─ cache
│       └─ redis
│           └─ RedisOrderCache
│
application
└─ OrderProcessingService
│
domain
├─ model
│   └─ Order
│
└─ port
    ├─ OrderRepository
    ├─ OrderEventPublisher
    └─ OrderCache
```

---

## 🧩 Camadas e Responsabilidades

### 📌 Domain

Contém o **coração do sistema**.

#### `model`

* **Order**

  * Entidade de domínio
  * Representa o pedido e suas regras

#### `port`

Define **contratos** (interfaces) que o mundo externo deve implementar:

* `OrderRepository` → Persistência de pedidos
* `OrderEventPublisher` → Publicação de eventos
* `OrderCache` → Cache de pedidos

> ⚠️ O domínio **não conhece** MongoDB, Kafka, Redis ou Spring.

---

### ⚙️ Application

#### `OrderProcessingService`

Responsável por **orquestrar o caso de uso**, por exemplo:

* Criar pedido
* Validar regras
* Salvar pedido
* Publicar evento
* Atualizar cache

Ele depende **apenas das portas do domínio**, nunca das implementações concretas.

---

### 🔌 Adapters

São as implementações concretas das portas.

#### ➡️ Inbound Adapter (Entrada)

##### `adapter/in/web/OrderController`

* Camada HTTP (REST)
* Recebe requisições externas
* Converte DTOs
* Chama o `OrderProcessingService`

---

#### ⬅️ Outbound Adapters (Saída)

##### 🗄️ Persistência (MongoDB)

* `MongoOrderRepository`
* Implementa `OrderRepository`
* Responsável por salvar e buscar pedidos

##### 📩 Mensageria (Kafka)

* `KafkaOrderEventPublisher`
* Implementa `OrderEventPublisher`
* Publica eventos de pedido (ex: `OrderCreated`)

##### ⚡ Cache (Redis)

* `RedisOrderCache`
* Implementa `OrderCache`
* Melhora performance de leitura

---

## 🔁 Fluxo de Execução (Exemplo)

1. Cliente faz requisição HTTP
2. `OrderController` recebe o request
3. Controller chama `OrderProcessingService`
4. Service aplica regras de negócio
5. Service usa as **ports**:

   * `OrderRepository`
   * `OrderEventPublisher`
   * `OrderCache`
6. Adapters executam ações externas

```
Controller → Application → Domain → Ports → Adapters
```

---

## ✅ Benefícios dessa Arquitetura

* 🧪 Testes simples (mock de ports)
* 🔄 Troca fácil de tecnologias
* 🧠 Domínio isolado e limpo
* 📈 Escalável e sustentável
* 🚀 Ideal para microsserviços

---

## 🧪 Testes

* Testes unitários focam em:

  * `Order`
  * `OrderProcessingService`

* Adapters podem ser testados separadamente

---

## 🛠️ Tecnologias (exemplo)

* Kotlin / Java
* Spring Boot
* MongoDB
* Kafka
* Redis

> As tecnologias são **detalhes de implementação**, não o centro do sistema.

---

## 📌 Próximos Passos

* Adicionar testes unitários e de integração
* Implementar observabilidade (logs, métricas, tracing)
* Criar versionamento de eventos
* Documentar contratos (OpenAPI / AsyncAPI)

---

💙 Arquitetura pensada para crescer sem dor.

