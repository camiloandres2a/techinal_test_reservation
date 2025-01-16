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
[ADJUNTO COLLECTION DE ENDPOINTS EN POSTMAN](https://drive.google.com/file/d/1lwx3m19aM591Qwg5LQ_kXR73nx6hwTHT/view?usp=drive_link)

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

* Por el momento solo existe en base de datos un restaurante (ID: 744844fe-9e5e-41aa-9578-f3f7602a6511) y una sede de ese restaurante (ID: f18b1cc5-d076-4421-a3d0-84f824fe5a2f)

### Cómo manejarías un aumento significativo en las consultas de horarios y creación de reservaciones?

Primero, evaluaría las consultas SQL utilizando una herramienta especializada para identificar posibles mejoras. Si es viable, optimizaría las consultas añadiendo índices, mejorando la estructura de los JOIN, y realizando ajustes que aumenten la eficiencia. En caso de que las consultas sigan creciendo significativamente, implementaría una estrategia de caché con Redis.

Desde la aplicación, verificaría en la caché si existen cambios recientes en la base de datos que no estén reflejados. En caso de ser necesario, solo traería los registros nuevos o actualizados desde la base de datos para mantener la información sincronizada. Este proceso lo realizaría preferiblemente durante horas de menor actividad, como en la noche, para minimizar el impacto en el rendimiento de las operaciones de reservación.

Para la creación de reservas, podría manejarse de forma asincrónica utilizando un broker de mensajería como Kafka. En este esquema, un publisher generaría un evento de creación que se enviaría a un topic en Kafka. A partir de ahí, un consumer procesaría los eventos desde el topic y gestionaría la inserción de datos en la base de datos.

Este enfoque tiene la ventaja de desacoplar la aplicación del tiempo de respuesta de la base de datos, lo que resulta especialmente útil en escenarios de alta concurrencia, como cuando hay 1000 reservaciones simultáneas. En lugar de esperar directamente la respuesta de la base de datos, las reservas se mantienen temporalmente en Kafka, donde se procesan de forma controlada y ordenada a través del consumer antes de almacenarse en la base de datos.

### Qué patrones, tecnologías o arquitecturas utilizarías para garantizar disponibilidad y rendimiento ? 

* Patrones: Builder, Chain of responsability
* Tecnologias: Docker, Java, Flyway, Kafka, Redis en Cache
* Arquitectura: Arquitectura hexagonal o puertos y adaptadores