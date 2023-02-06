# 4 en raya con reactive y websockets

Hemos desarrollado una aplicación basada en Spring Reactive, Websockets y Angular con la cual se puede jugar multijugador, chatear y todos los datos de la partida 
son guardados en MongoDB.

## Comenzando 🚀

Antes de poder lanzar la aplicación vamos a necesitar tener una serie de programas instalados para la correcta ejecución. Ponemos una pequeña lista de las dependencias
de esta.


### Pre-requisitos 📋

_Software necesario para la ejecución correcta de la aplicación_

#### Backend

- Java 17
- MongoDB

#### Frontend

- Angular
  - SweetAlert2
  - Bootstrap
  - SocksJS
  - Stomp

### Instalación 🔧

_Ponemos a continuación los comandos necesarios para poder iniciar la aplicación_

- Angular
  - Para comenzar instalaremos SweetAlert2 que es una extensión para Angular
```
npm install --save sweetalert2
```
- SocketJS
  - Para instalar SocketJS
 ```
 npm i @types/socksjs-client--save-dev
 npm install sockjs-client
 ```
- Stomp
  - Para instalar STOMP (que usaremos para websockets)
 ```
 npm install @stomp/stompjs@5.2.0 --save
 ```
 - Hacemos un pequeño update para tener todo actualizado
```
npm update
```
Para que funcione correctamente, hay que cambiar el application-properties del backend para que conecte adecuadamente con el mongoDB 
(en nuestro caso dentro de un contenedor de Docker)

## Autores ✒️

* **Andrés Cioppi Mettler**
* **Victor Rubio Ibáñez**
* **Jose del Río Hermo**
* **Jose Alberto Corzo Mate**
