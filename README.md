 # Operation-Qausar-Meli

Esta aplicación se contiene una clase la cual incorpora 2 métodos los cuales son:

- getLocation 
  - entrada: distancia al emisor tal cual se recibe en cada satélite
  - salida: las coordenadas del emisor del mensaje
- getMessage 
  - entrada: el mensaje tal cual es recibido en cada satélite
  - salida: el mensaje tal cual lo genera el emisor del mensaje

las mismas se crean en una clase QuasarUtils, será necesario para los endpoint posteriores (level2, level3 ) 

## Detalle sobre ApiRest

**Artefacto**: **operation-quasar-meli**

**Repositorio:** [operation-quasar-meli - main](https://github.com/gabriel95-x/operation-quasar-meli)  



## Pasos para la instalación y la ejecución 

**Es importante tener instalado java 11 y Maven**

- **clonar el repositorio en alguna ubicación de su máquina ej C:/example/operation-quasar**
- **abrir una consola CMD** 
- **luego en la consola posicionarse en el directorio donde clono el proyecto con el siguiente comando**
```shell
   $ cd C:/example/operation-quasar 
```
- **hasta este punto esta listo para la instalación aplicando el siguiente comando**
```shell 
   $ mvn clean install
```
- **una vez terminado el proceso de instalación con el siguiente comando realiza la propia ejecución**
```shell
   $  mvn spring-boot:run
```

**una vez ejecutada la app puedes acceder a la documetacion respectiva de la api en el siguiente enlace**

**Swagger (documentación de la api):** [API DOC](http://localhost:3332/swagger-ui.html#/)







