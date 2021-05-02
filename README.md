# Reto Pokémon (PARTE 1, recuperación de tipos de un pokémon)

## Pre-requisitos

* Tener Java 11 instalado
* Comprobar los permisos del fichero gradlew (este fichero se encuentra en la raíz del proyecto, y es lo que se emplea para lanzar la aplicación).
  Es muy probable que la terminal muestre que no tenemos permisos para ejecutarlo, por lo que habrá que cambiarlos mediante `chmod u+x gradlew`
* Tener algún programa como Postman (la parte de la API no es necesario probarla con Postman, pero este tipo de programas hace que dicha labor sea más sencilla por la interfaz visual de este tipo de aplicaciones como Postman)

## Puesta en marcha

1) Para asegurar que partimos de un proyecto fresco, ejecutar `./gradlew clean`
2) Para construir la aplicación, ejecutar `./gradlew build`
3) Para lanzar la aplicación, ejecutar `java -jar build/mdas-api-g5-1.0-SNAPSHOT-runner.jar`

## Parar la aplicación

* La aplicación se podrá parar por completo pulsando la combinación de teclas CTRL+C
* La consola, que normalmente se usará para escribir nombres de pokémon, se puede usar para parar la aplicación escribiendo "exit" y pulsando Enter

## Probar la aplicación

La aplicación se puede usar de dos maneras:
1) Desde la consola
2) Desde programas tipo Postman

### Desde la consola

1) En la misma terminal desde la que se lanzó el proyecto, escribir el nombre de un Pokémon y pulsar Enter  
    1.1) En caso de que el Pokémon exista, se mostrarán los tipos del Pokémon  
    1.2) En caso de que el Pokémon no exista, se mostrará un mensaje (en la propia consola) indicando que no hay ningún Pokémon con el nombre introducido
2) Para salir de la consola, escribir "exit" y pulsar Enter

### Desde programas tipo Postman

1) Suponiendo que se usa un programa tipo Postman  
   1.1) Será necesario crear una nueva petición de tipo GET  
   1.2) La URL a la que hacer peticiones será la siguiente: http://localhost:8081/pokemon/types?name=charizard  
   1.3) En lugar de charizard, se puede escribir en nombre de cualquier otro Pokémon  
2) Suponiendo que se usan comandos de terminal como "curl"  
   2.1) Ejecutar curl http://localhost:8081/pokemon/types?name=charizard  
   2.2) En lugar de charizard, se puede escribir en nombre de cualquier otro Pokémon

# Reto Pokémon (PARTE 2, gestión de pokémon favoritos de un usuario)

## Pre-requisitos

* Tener Java 11 instalado
* Comprobar los permisos del fichero gradlew (este fichero se encuentra en la raíz del proyecto, y es lo que se emplea para lanzar la aplicación).
  Es muy probable que la terminal muestre que no tenemos permisos para ejecutarlo, por lo que habrá que cambiarlos mediante `chmod u+x gradlew`
* Tener algún programa como Postman (la parte de la API no es necesario probarla con Postman, pero este tipo de programas hace que dicha labor sea más sencilla por la interfaz visual de este tipo de aplicaciones como Postman)

## Puesta en marcha

1) Para asegurar que partimos de un proyecto fresco, ejecutar `./gradlew clean`
2) Para construir la aplicación, ejecutar `./gradlew build`
3) Para lanzar la aplicación, ejecutar `java -jar build/mdas-api-g5-1.0-SNAPSHOT-runner.jar`

## Parar la aplicación

Se recomienda parar la aplicación pulsando la combinación de teclas CTRL+C en la consola donde se lanzó el fichero JAR.

## Probar la aplicación (desde programas tipo Postman)

1) Será necesario crear una nueva petición de tipo GET  
2) Será necesario hacer 1 petición a esta URL para crear un usuario: http://localhost:8081/user/addUser/?name=alberto&userId=1  
    2.1 En lugar de alberto se puede elegir cualquier otro nombre para el usuario  
    2.2 En lugar de 1 se puede elegir cualquier otro identificador para el usuario
3) La URL para añadir pokemon favoritos es la siguiente: http://localhost:8081/user/addFavouritePokemon/?id=6  
    3.1 En lugar de 6 se puede elegir cualquier otro identificador que insertar  
    3.2 Advertencia: el header param que representa al id del usuario puede que se añada de forma distinta según la
        herramienta elegida para hacer las peticiones
   
## Ejecutar los test unitarios

A pesar de que los test unitarios se ejecutan en el momento de ejecutar el comando `./gradlew build`, también se pueden  
ejecutar mediante el comando `./gradlew cleanTest test`.

# Reto Pokémon (PARTE 3, recuperación de Pokémon)

## Pre-requisitos

* Tener Java 11 instalado
* Comprobar los permisos del fichero gradlew (este fichero se encuentra en la raíz del proyecto, y es lo que se emplea para lanzar la aplicación).
  Es muy probable que la terminal muestre que no tenemos permisos para ejecutarlo, por lo que habrá que cambiarlos mediante `chmod u+x gradlew`
* Tener algún programa como Postman (la parte de la API no es necesario probarla con Postman, pero este tipo de programas hace que dicha labor sea más sencilla por la interfaz visual de este tipo de aplicaciones como Postman)

## Puesta en marcha

1) Para asegurar que partimos de un proyecto fresco, ejecutar `./gradlew clean`
2) Para construir la aplicación, ejecutar `./gradlew build`
3) Para lanzar la aplicación, ejecutar `java -jar build/mdas-api-g5-1.0-SNAPSHOT-runner.jar`

## Parar la aplicación

Se recomienda parar la aplicación pulsando la combinación de teclas CTRL+C en la consola donde se lanzó el fichero JAR.

## Probar la aplicación (desde programas tipo Postman)

1) Será necesario crear una nueva petición de tipo GET
2) Será necesario hacer 1 petición a esta URL para obtener la información de un Pokémon: http://localhost:8081/pokemon/get/123  
   2.1 En lugar de 123 se puede elegir cualquier otro id en función del Pokémon que se desee recuperar  

## Ejecutar los test

A pesar de que los test unitarios se ejecutan en el momento de ejecutar el comando `./gradlew build`, también se pueden 
ejecutar mediante el comando `./gradlew cleanTest test`.