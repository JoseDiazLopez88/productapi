# 📦 API de Gestión de Productos y Categorías - Spring Boot

## 📑 Descripción
API RESTful para gestión de productos y categorías con arquitectura limpia, seguridad JWT.

## 🛠️ Tecnologías
- **Spring Boot 3** - Framework principal
- **Spring Security + JWT** - Autenticación
- **Spring Data JPA** - Persistencia
- **Lombok** - Reducción de código
- **MapStruct** - Mapeo de DTOs

## 🚀 Endpoints Principales

### 🔸 Categorías
| Método | Endpoint                | Descripción          |
|--------|-------------------------|----------------------|
| GET    | /api/categories         | Listar categorías    |
| POST   | /api/categories         | Crear categoría      |

### 🔸 Productos
| Método | Endpoint                | Descripción          |
|--------|-------------------------|----------------------|
| GET    | /api/products           | Listar (paginado)    |
| POST   | /api/products           | Crear producto       |

## 🏗️ Estructura
src/

├── config/ # Configuraciones

├── controllers/ # Controladores REST

├── dtos/ # Objetos de transferencia

├── models/ # Entidades JPA

├── repositories/ # Repositorios

└── services/ # Lógica de negocio


## 📝 Licencia
MIT - Libre uso y modificación




