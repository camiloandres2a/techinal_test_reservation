# Reservacion en un restaurante

### Como correr la aplicacion ?

* Tener descargado docker ( 27.4.0 v )
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
