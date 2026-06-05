🚀 BusRoutePlanner
BusRoutePlanner es una aplicación web MVC desarrollada con Spring Boot que permite gestionar de forma integral las operaciones de una empresa de transporte urbano. Cubre el ciclo completo: desde la definición de rutas y la asignación de autobuses, hasta la creación de turnos diarios con control de conductores.
La aplicación está pensada para dos perfiles de usuario: administradores, que controlan también los accesos al sistema, y operadores, que se encargan de la planificación del día a día.

✅ Funcionalidades
🗺️ Gestión de rutas

Listado, creación, edición y eliminación de rutas.
Campos: código, origen, destino, distancia, precio por kilómetro, precio del viaje y número máximo de buses simultáneos.
Validación de formularios y páginas de error específicas.

🚌 Gestión de autobuses

Listado, creación, edición y eliminación de autobuses.
Cada autobús tiene matrícula, capacidad y está asignado a una ruta.

👤 Gestión de conductores

Listado completo y filtrado por conductores activos.
Creación con número de licencia, nombre y estado (activo / inactivo).
Edición y eliminación con validaciones.

📅 Planificación de rutas

Creación de planificaciones asignando ruta, autobús, conductor, fecha y turno (MAÑANA / TARDE).
El día de la semana se calcula automáticamente a partir de la fecha.
Filtrado de planificaciones por día de la semana.
Ranking de rutas más utilizadas según el número de planificaciones registradas.
Validación: la fecha no puede ser anterior al día de hoy.
Control de duplicados: un conductor no puede tener dos turnos asignados en el mismo día.

🔐 Gestión de usuarios (solo ADMIN)

Listado, creación, edición y eliminación de usuarios del sistema.
Asignación de rol ADMIN u OPERADOR en la creación.


🛠️ Tecnologías
CategoríaTecnologíaLenguajeJava 21Framework backendSpring Boot 4.0.6PersistenciaSpring Data JPA / HibernateSeguridadSpring SecurityValidaciónJakarta Bean ValidationPlantillasThymeleaf + thymeleaf-extras-springsecurity6Base de datosH2 (fichero embebido)FrontendBootstrap 5, Bootstrap Icons, CSS propioBuildApache MavenUtilidadesLombokIDESpring Tools for Eclipse

🏗️ Arquitectura
El proyecto sigue una arquitectura MVC en capas:
Controladores (controllers/)
    │
    ▼
Servicios (service/)          ◄── BaseServiceImplem<T, ID, R>
    │
    ▼
Repositorios (repositories/)  ◄── Spring Data JPA
    │
    ▼
Entidades JPA (entity/)

Entidades principales: Route, Bus, Driver, PlanificacionRuta, Usuario (con subclases Admin y Operador).
Herencia de servicio: todos los servicios extienden BaseServiceImplem, que provee las operaciones CRUD genéricas.
Seguridad: configurada mediante SecurityConfig con reglas por rol; RoleBasedSuccessHandler redirige a cada usuario a su panel tras el login.
Excepciones personalizadas: DriverDuplicadoException, RutaSolapadaException, CapacidadExcedidaException, gestionadas globalmente por ExceptionControllerAdvice.


⚡ Primeros pasos
Requisitos previos

Java 21 instalado y en el PATH.
Maven 3.8+ (o usar el wrapper incluido ./mvnw).
Navegador web moderno.

Clonar el repositorio
bashgit clone https://github.com/tu-usuario/BusRoutePlanner.git
cd BusRoutePlanner/BusRoutePlannerMarioGil
Ejecutar la aplicación
Con Maven Wrapper (recomendado, no requiere Maven instalado):
bash./mvnw spring-boot:run
Con Maven instalado:
bashmvn spring-boot:run
Acceder a la aplicación
Una vez arrancada, abre tu navegador en:
http://localhost:9000

La aplicación carga automáticamente datos de ejemplo desde import.sql al arrancar.


👥 Usuarios de prueba
La aplicación crea los siguientes usuarios automáticamente al arrancar (via DataInitializer):
UsuarioContraseñaRoladminadminADMINoperadoroperadorOPERADOR

🗄️ Base de datos
La base de datos utilizada es H2 en modo fichero, almacenada en ./db/busroutedb.
CampoValorConsola webhttp://localhost:9000/h2-consoleJDBC URLjdbc:h2:file:./db/busroutedbUsuariosaContraseña(vacía)

Nota: el esquema se recrea en cada arranque (create-drop). Los datos de ejemplo se cargan desde src/main/resources/import.sql e incluyen rutas, autobuses, conductores y planificaciones de muestra.


🔒 Seguridad y roles
RutaAcceso/css/**, /js/**, /img/**Público/h2-console/**Público/loginPúblico/admin/**Solo ADMIN/operador/**Solo OPERADORResto de rutasCualquier usuario autenticado
Tras el login, cada usuario es redirigido automáticamente a su panel correspondiente según su rol.

📁 Estructura del proyecto
BusRoutePlannerMarioGil/
├── src/
│   └── main/
│       ├── java/com/salesianostriana/dam/busrouteplannermariogil/
│       │   ├── controllers/        # Controladores MVC
│       │   ├── entity/             # Entidades JPA y enums (Rol, Horario)
│       │   ├── exceptions/         # Excepciones de negocio y ControllerAdvice
│       │   ├── repositories/       # Repositorios Spring Data
│       │   ├── security/           # Configuración de seguridad y autenticación
│       │   ├── service/            # Servicios de negocio
│       │   └── servicebase/        # Servicio CRUD genérico
│       └── resources/
│           ├── static/css/         # Estilos propios
│           ├── templates/          # Vistas Thymeleaf
│           ├── application.properties
│           └── import.sql          # Datos iniciales de ejemplo
├── pom.xml
└── HELP.md