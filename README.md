# BusRoutePlanner

BusRoutePlanner es una aplicación web desarrollada con Spring Boot para gestionar la planificación de rutas de autobús urbano. La aplicación permite administrar rutas, autobuses y conductores, y crear planificaciones que asignan un conductor y un bus a una ruta en una fecha y turno concretos. El acceso está protegido por roles: los administradores gestionan también los usuarios del sistema, mientras que los operadores se encargan del día a día operativo.
Funcionalidades
Gestión de rutas

Listado de todas las rutas disponibles.
Creación de nuevas rutas con código, origen, destino, distancia, precio por kilómetro, precio del viaje y número máximo de buses simultáneos.
Edición y eliminación de rutas existentes.
Validación de campos del formulario.
Control de errores con páginas de error específicas por entidad.

Gestión de autobuses

Listado de todos los autobuses registrados.
Creación de autobuses con matrícula, capacidad y asignación a una ruta.
Edición y eliminación de autobuses.
Validación de campos y control de errores.

Gestión de conductores

Listado de todos los conductores.
Filtrado de conductores activos.
Creación de conductores con número de licencia, nombre y estado (activo/inactivo).
Edición y eliminación de conductores.
Validación de campos y control de errores.

Planificación de rutas

Listado de todas las planificaciones creadas.
Creación de una planificación asignando una ruta, un autobús, un conductor, una fecha y un turno (mañana o tarde).
El día de la semana se calcula automáticamente a partir de la fecha seleccionada.
Edición y eliminación de planificaciones.
Filtrado de planificaciones por día de la semana.
Ranking de rutas más utilizadas según el número de planificaciones registradas.
Validación de campos: la fecha no puede ser anterior al día de hoy.
Control de duplicados: un conductor no puede tener dos turnos asignados el mismo día.

Gestión de usuarios (solo ADMIN)

Listado de todos los usuarios del sistema.
Creación de nuevos usuarios con rol ADMIN u OPERADOR.
Edición y eliminación de usuarios.

Tecnologías y dependencias
El proyecto está construido con Java 21 y Spring Boot 4.0.6. Las dependencias principales declaradas en pom.xml son:

spring-boot-starter-webmvc: desarrollo web MVC con controladores y rutas.
spring-boot-starter-thymeleaf: motor de plantillas HTML del lado del servidor.
spring-boot-starter-data-jpa: persistencia con JPA/Hibernate.
spring-boot-starter-security: autenticación, gestión de roles y protección de rutas.
spring-boot-starter-validation: validación de formularios y entidades.
spring-boot-h2console: consola web para consultar la base de datos H2.
h2: base de datos en fichero usada en desarrollo.
lombok: reducción de código repetitivo en modelos, servicios y controladores.
thymeleaf-extras-springsecurity6: integración de Spring Security con las plantillas Thymeleaf.

En el frontend se usan:

Thymeleaf para renderizar las vistas desde el servidor.
Bootstrap 5 para estilos y componentes responsivos.
Bootstrap Icons para los iconos de la interfaz.
CSS propio en src/main/resources/static/css.

Herramientas utilizadas

Java 21
Maven
Spring Boot
Spring Tools for Eclipse / Eclipse como entorno de desarrollo
Git para control de versiones
H2 Console para inspeccionar la base de datos durante el desarrollo
Navegador web para probar la aplicación

Acceso a la aplicación
Una vez arrancada, la aplicación está disponible en:
http://localhost:9000
La consola de H2 está disponible en:
http://localhost:9000/h2-console
Datos de conexión de H2:
CampoValorJDBC URLjdbc:h2:file:./db/busroutedbUsuariosaContraseña(vacía)
Usuarios de prueba
La aplicación crea los usuarios iniciales automáticamente al arrancar mediante DataInitializer.
UsuarioContraseñaRoladminadminADMINoperadoroperadorOPERADOR
Base de datos
La base de datos usada es H2 en fichero (./db/busroutedb). El esquema se recrea en cada arranque (create-drop) y los datos iniciales se cargan desde src/main/resources/import.sql, que incluye rutas, autobuses, conductores y planificaciones de ejemplo. Los datos persisten en el fichero mientras la aplicación esté detenida, pero se pierden al reiniciar porque el esquema se regenera.
Seguridad
Las rutas de recursos estáticos (/css/**, /js/**, /img/**) y la consola H2 son públicas. El formulario de login (/login) también es accesible sin autenticación.
Las rutas bajo /admin/** requieren el rol ADMIN. Las rutas bajo /operador/** requieren el rol OPERADOR. El resto de rutas (listados de rutas, buses, conductores, planificaciones) requieren cualquier usuario autenticado independientemente del rol.
Tras el login, cada usuario es redirigido automáticamente a su panel correspondiente según su rol mediante RoleBasedSuccessHandler.