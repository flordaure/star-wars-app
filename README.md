# STAR-WARS-APP

STAR-WARS-APP es una aplicación Java basada en Spring Boot 2.7.18 que consume la API pública de Star Wars (https://swapi.tech/documentation). La aplicación proporciona varios servicios RESTful para acceder a información sobre películas, personajes, naves y vehículos del universo de Star Wars. Los datos son obtenidos a través de la API de Star Wars y expuestos de manera eficiente con el uso de un servicio de caché.

## Tecnologías utilizadas

- **Java**: 8
- **Spring Boot**: 2.7.18
- **Maven**: Para gestión de dependencias y construcción del proyecto
- **Swagger**: Para la documentación de la API
  **Spring Security**: Para la autenticación y autorización
- **Spring Cache**: Para el almacenamiento en caché de las respuestas

## Funcionalidad

La aplicación expone varios endpoints que permiten consultar información acerca de:

- **Películas** (`/films`, `/filmsById`, `/filmsByTitle`, `/films-pages`)
- **Personajes** (`/characters`, `/charactersByName`, `/characterById`, `/characters-pages`)
- **Naves** (`/starships`, `/starshipsById`, `/starshipsByName`, `/starships-pages`)
- **Vehículos** (`/vehicles`, `/vehiclesById`, `/vehiclesByName`, `/vehicles-pages`)
- **Caché** (`/cleanCache/{nameCache}`)

### Endpoints 
#### Películas

- **GET** `/films`: Obtiene una lista completa de las películas de Star Wars.
- **GET** `/filmsById`: Obtiene una película específica por su ID.
- **GET** `/filmsByTitle`: Obtiene las películas que contienen el título enviado como parámetro.
- **GET** `/films-pages`: Obtiene una lista de películas paginadas, utilizando el parámetro de página.

#### Personajes

- **GET** `/characters`: Obtiene todos los personajes de Star Wars.
- **GET** `/charactersByName`: Obtiene personajes cuyo nombre contiene el parámetro proporcionado.
- **GET** `/characterById`: Obtiene un personaje específico por su ID.
- **GET** `/characters-pages`: Obtiene personajes de páginas específicas, utilizando el parámetro de páginas (acepta una lista de páginas).

#### Naves

- **GET** `/starships`: Obtiene todas las naves del universo Star Wars.
- **GET** `/starshipsByName`: Obtiene las naves cuyo nombre contiene el parámetro proporcionado.
- **GET** `/starshipById`: Obtiene una nave específica por su ID.
- **GET** `/starships-pages`: Obtiene naves de páginas específicas, utilizando el parámetro de páginas.

#### Vehículos

- **GET** `/vehicles`: Obtiene todos los vehículos de Star Wars.
- **GET** `/vehiclesByName`: Obtiene los vehículos cuyo nombre contiene el parámetro proporcionado.
- **GET** `/vehicleById`: Obtiene un vehículo específico por su ID.
- **GET** `/vehicles-pages`: Obtiene vehículos de páginas específicas, utilizando el parámetro de páginas.

#### Cache

- **POST** `/cleanCache/{nameCache}`: Limpia el caché especificado por su nombre. Los nombres de caché válidos son:
   - `films`
   - `people`
   - `starships`
   - `vehicles`

El caché se limpia automáticamente a intervalos regulares y también se limpia una vez al día mediante una tarea programada (scheduled task). Además, el caché puede ser limpiado manualmente a través de este endpoint.

## Seguridad

La aplicación utiliza **Spring Security** para proteger los endpoints de la API. Los usuarios deben autenticarse utilizando las siguientes credenciales:

- **Usuario**: `admin`
- **Contraseña**: `adminPassword`

Estas credenciales son necesarias para acceder a los endpoints expuestos por la aplicación.


## Requisitos

- Java 8
- Maven 3.x

## Instalación

1. Clona este repositorio en tu máquina local:

   ```
   git clone https://github.com/flordaure/star-wars-app.git
   cd STAR-WARS-APP
## Dependencias

2. Ejecuta el siguiente comando para compilar e instalar las dependencias:

   ```
   mvn clean install

3. Ejecuta la aplicación Spring Boot:
  
   ```
   mvn spring-boot:run
   
4. La aplicación estará disponible en http://localhost:8081.
## Uso de la API

La API está documentada con Swagger. Puedes acceder a la documentación interactiva a través de la siguiente URL:

http://localhost:8081/swagger-ui.html
