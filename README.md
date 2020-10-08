# Cafeteria-FullStack
 Full Stack VCT questionnaire Java Spring Boot/ Angular 10 / Mysql / WebSocket + RestApi

## Contenido
	
## Prerrequisitos
Asegúrese de tener esto instalado antes de continuar
- Java 8
- Maven 3.3.9+
- Spring Boot 2.1.5.RELEASE
- Node 12.18.4 or above,  
- npm 6 or above,   
- Angular-cli 10.1.4
- Mysql or MariaDB

## Instalacion
1 -Crear Base de Datos en Mysql con nombre "cafeteria"
2 -Ejecutar sql/Query.sql
3 -
* Para instalar y ejecutar este proyecto, simplemente escriba y ejecute en Cafeteria-Frontend
```bash
npm install
```

* Instalar repositorios Maven de Cafeteria-Backend en Eclipse o STS

4 - Ejecutar Spring Boot Cafeteria-Backend
5 - Ejecutar Cafeteria-Frontend
```bash
ng serve
```


## Funcionamiento del Ejercicio 1:

* En la secccion de Usuarios se pueden ver los distintos Usuarios con los 4 roles pedidos (Operador, Supervisor, Gerente, Cliente), se pueden agregar, editar y eliminar los mismos, 
* Se les puede cambiar el estado de online (que este conectado) y disponible (que no este ocupado),
* Al momento de ir a la seccion Soporte pide un usuario (si es un cliente puede ingresar con uno ya existente o sino crea uno nuevo sin ir a otra seccion), o ingresar como soporte con la contraseña asignada
* Si inicia sesion como soporte automaticamente se va a cambiar el estado del usuario a Online y Disponible, 
* Cuando comienza el chat con un cliente este cambia su estado de disponible = false y cuando presiona Desconectar cambiar el estado de Online=false 
* pero cuando presiona Desconectar un Cliente se va a cambiar solo el estado Disponible = true del soporte que estaba atendiendolo...


## Funcionamiento del Ejercicio 2:

* Tanto en la seccion Bebidas como Adicionales se pueden cargar, editar, y eliminar la carga de datos con sus respectivos precios.
* En la seccion de Pedidos se pueden crear y ver ordenes de 1 bebida con los adicionales que se quieran agregar, calculando el total al final del formulario
