## Generar Java a patir de avro y consultar la compatibilidad con versiones anteriores

# Para generar los ficheros 

* Incluir los ficheros avsc que se quieren generar en la carpeta src/main/event
* Añadirlos al pom.xml en la seccion  <imports>
* Ejecutar el comando de generacion de maven
* El fichero pipe_definitions.avsc es necesario y nunca borrar porque tiene las definiciones propias del framework
   
  
```
mvn generate-sources
```
# Consultar compatibilidad

Se debe tener en cuenta el tipo de compatibilidad que tiene el esquema. 

* Para consultarla:
```
curl -X GET -H "Content-Type: application/vnd.schemaregistry.v1+json" http://localhost:8082/config
```

* En caso de querer cambiarla:
```
curl -X PUT -H "Content-Type: application/vnd.schemaregistry.v1+json" --data '{"compatibility": "FORWARD"}' http://localhost:8082/config
```

Una vez generados los ficheros java instancias la clase que contiene el nuevo esquema, modificas el subject del topic a comprobar
y arrancas la aplicacion Avro2SchemaRegistry. Internamente hace POST al schema registry a la url que se muestra a continuacion y te devuelve si es compatible

```
http://localhost:8082/compatibility/subjects/<subject>/versions/latest
```

# Docu de Schema Registry

https://docs.confluent.io/current/schema-registry/develop/api.html

# Para generar ejemplos con datos aleatorios

En la clase ExampleFromSchema instancias el objeto cuyo esuqema quieres generar el ejemplo y lanzas la aplicacion.
