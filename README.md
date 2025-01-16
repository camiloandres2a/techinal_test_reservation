# Reservacion en un restaurante

### Como correr la aplicacion ?

* Tener descargado docker
* Tener descargado MySQL

### Como levantar docker ?
Al mismo tiempo que se levanta la base de datos, se levanta la aplicación automaticamente y puedes empezar a usar los endpoints desde postman

* Estar en la raiz donde se encuentra el docker-compose.yml
* Codigos a seguir:
  1.  docker-compose up -d
  2. docker-compose logs app

### Como mirar la base de datos creada ?
* Comandos:
  * docker exec -it demo-db-1 bash
  * mysql -u root -p
  * password = (rootpassword)
  * use myreservationreversi;

### Base de Datos

* [Diagrama de Entidad-Relacion](https://lucid.app/lucidchart/bbe20ef9-2a28-4597-9d03-6c64aaaa6010/edit?viewport_loc=1145%2C54%2C2249%2C1056%2C0_0&invitationId=inv_d15327f6-1118-45c7-9483-ade9bd8b8331)

El modelo funciona de la siguiente manera: un restaurante puede tener múltiples sedes (Branch), y cada sede gestiona su propio horario semanal (Schedule). Dado que esta aplicación es un MVP, los horarios deben ser precisos, como 11:00 o 14:00. Además, cada sede puede definir la capacidad máxima de comensales que puede atender por hora.

Si se supera este límite, la aplicación sugerirá al cliente un horario alternativo disponible. Por otro lado, los clientes (Client) pueden realizar múltiples reservas (Reservation), gestionando así sus visitas de forma eficiente.

Este diseño sencillo y funcional permite a los restaurantes optimizar sus operaciones y mejorar la experiencia de los clientes.     


### ENDPOINT
[ADJUNTO COLLECTION DE ENDPOINTS EN POSTMAN](..%2F..%2F..%2FDocuments%2FReservation%20-%20Riservi.postman_collection.json)

Tengo implementados cinco endpoints, los cuales son los siguientes:

* Crear una reserva
* Obtener reservas por día y por sede
* Obtener reserva por ID de reserva
* Eliminar una reserva por ID de reserva
* Actualizar una reserva


* Recomendaciones:
  * Crear una reserva


* Los días de la semana deben ser proporcionados en inglés y en mayúsculas (por ejemplo, "WEDNESDAY").

* El horario debe seguir el formato "HH:MM:SS" (por ejemplo, "09:00:00").
  
* El número de celular debe constar de 10 dígitos.
  
* Por el momento, existe de manera predeterminada un restaurante y una sede asociada a dicho restaurante.

