# OpenStore 🛍️

> Plataforma de práctica desarrollada para las **Olimpiadas de Java** de la **Universidad Americana (UAM)**. Aplicación web empresarial construida con [OpenXava](https://openxava.org/) y empaquetada como archivo **WAR** mediante Maven.

![Estado](https://img.shields.io/badge/estado-en%20desarrollo-yellow) ![OpenXava](https://img.shields.io/badge/OpenXava-7.4-blue) ![Java](https://img.shields.io/badge/Java-8%20|%2011%20|%2017%20|%2021-important) ![Packaging](https://img.shields.io/badge/Tipo-WAR-orange) ![UAM](https://img.shields.io/badge/UAM-Olimpiadas%20Java-9cf) ![Licencia](https://img.shields.io/badge/Licencia-MIT-green)

---
## 🧭 Tabla de Contenidos
1. [Resumen](#-resumen)
2. [Tecnologías](#-tecnologías)
3. [Estructura del Proyecto](#-estructura-del-proyecto)
4. [Requisitos Previos](#-requisitos-previos)
5. [Configuración Rápida (Quick Start)](#-configuración-rápida-quick-start)
6. [Construcción y Empaquetado](#-construcción-y-empaquetado)
7. [Ejecución / Despliegue](#-ejecución--despliegue)
8. [Configuración de Base de Datos](#-configuración-de-base-de-datos)
9. [Usuarios y Seguridad](#-usuarios-y-seguridad)
10. [Internacionalización (i18n)](#-internacionalización-i18n)
11. [Tests](#-tests)
12. [Personalización de la Aplicación](#-personalización-de-la-aplicación)
13. [Convenciones de Código](#-convenciones-de-código)
14. [Solución de Problemas (Troubleshooting)](#-solución-de-problemas-troubleshooting)
15. [Roadmap / Próximos Pasos](#-roadmap--próximos-pasos)
16. [Licencia](#-licencia)
17. [Contacto](#-contacto)

---
## 🎯 Resumen
OpenStore sirve como base para crear aplicaciones de gestión (ERP/CRM u otros sistemas internos) de forma ágil usando **OpenXava**, que genera automáticamente módulos CRUD productivos a partir de clases Java. Este proyecto se creó como ejercicio formativo y demostración técnica para competencias académicas en la UAM.

---
## 🛠 Tecnologías
- **Java** 8 (compatible también con 11, 17, 21)
- **Maven** para empaquetado y dependencias
- **OpenXava** 7.4 (framework RAD para aplicaciones empresariales)
- **JPA** / Hibernate vía `persistence.xml`
- **Servlet/JSP** en contenedores (Tomcat, Payara, WildFly)
- **PostgreSQL** (driver activo) y otros listos para habilitar

---
## 📁 Estructura del Proyecto
```
openStore/
├─ pom.xml
├─ src/
│  ├─ main/
│  │  ├─ java/              # Entidades y lógica
│  │  ├─ resources/
│  │  │  ├─ i18n/           # Etiquetas y mensajes
│  │  │  ├─ META-INF/       # persistence.xml
│  │  │  ├─ naviox*.properties  # Usuarios y navegación
│  │  │  └─ xava/           # Configuración OpenXava
│  │  └─ webapp/            # web.xml, estilos, etc.
│  └─ test/                 # (Config tests) recursos
└─ target/                  # WAR y artefactos build
```

---
## ✅ Requisitos Previos
Asegúrate de tener:
- JDK 8 / 11 / 17 / 21 (LTS recomendado) → `java -version`
- Maven 3.8+ → `mvn -v`
- Contenedor Java (Tomcat 9+, etc.)
- Base de datos si probarás persistencia real

> Encoding actual: `ISO-8859-1`. Considera migrar a UTF-8 si usas multilenguaje y caracteres especiales extensivos.

---
## 🚀 Configuración Rápida (Quick Start)
```powershell
# Compilar y empaquetar
mvn clean package

# WAR generado: target/openStore.war
# Desplegar en Tomcat (ejemplo)
# copy target\openStore.war %CATALINA_HOME%\webapps\
# Acceder
# http://localhost:8080/openStore
```
IDE: Importa como proyecto Maven y configura artefacto WAR en tu servidor embebido.

---
## 🏗 Construcción y Empaquetado
```powershell
mvn clean package              # Build estándar
mvn -DskipTests clean install   # Sin tests
mvn -DskipTests=false test      # Forzar ejecución de tests
```
Resultado principal: `target/openStore.war`

Exclusiones (maven-war-plugin) reducen tamaño excluyendo libs pesadas. Ajusta `packagingExcludes` si necesitas alguna en runtime.

---
## 🌐 Ejecución / Despliegue
1. Genera el WAR.
2. Copia a `webapps/` del contenedor.
3. Arranca servidor.
4. Visita: `http://localhost:8080/openStore`

IDE: Configura run config y publica artefacto antes de iniciar.

---
## 🗄 Configuración de Base de Datos
Pasos:
1. Descomenta dependencia de tu motor en `pom.xml`.
2. Ajusta credenciales en `persistence.xml`.
3. Verifica propiedad `hibernate.hbm2ddl.auto` (create/update/validate).

Ejemplo PostgreSQL:
```xml
<property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/>
<property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/openstore"/>
<property name="javax.persistence.jdbc.user" value="openstore"/>
<property name="javax.persistence.jdbc.password" value="secret"/>
```

---
## 🔐 Usuarios y Seguridad
`naviox-users.properties` gestiona usuarios:
```
usuario=contraseña,ROL1,ROL2
```
Reinicia el contenedor para aplicar cambios. Futuro: migrar a almacenamiento en BD o proveedor externo (LDAP, OAuth).

---
## 🌍 Internacionalización (i18n)
Archivos `openStore-labels_es.properties` y `openStore-messages_es.properties` definen texto en español.
Para otro idioma crea: `openStore-labels_en.properties` & `openStore-messages_en.properties`.

---
## 🧪 Tests
Surefire actualmente con `<skipTests>true`.
Activar:
1. Cambiar a `<skipTests>false</skipTests>`.
2. Añadir clases de test en `src/test/java`.
```powershell
mvn test
```

---
## 🎨 Personalización de la Aplicación
- `aplicacion.xml`: Módulos visibles.
- `controladores.xml`: Acciones y botones.
- `editores.xml`: Sustituir editores por defecto.
- `xava/style/`: Logos, colores, CSS corporativo.

---
## 📏 Convenciones de Código
- Entidades con `@Entity`, relaciones claras (`@OneToMany`, etc.).
- Definir longitudes con `@Column(length=...)`.
- Nombrado orientado al dominio (CamelCase).
- Lógica de negocio en servicios/domain, no en controladores UI.

---
## 🛠 Solución de Problemas (Troubleshooting)
| Problema | Causa | Solución |
|----------|-------|----------|
| WAR no se despliega | Versión Java incompatible | Usar JDK soportado (8/11/17/21) |
| Error driver JDBC | Dependencia comentada | Descomentar y recompilar |
| Cambios usuarios no se reflejan | Caché contenedor | Reiniciar servidor |
| Caracteres extraños | Encoding ISO-8859-1 | Migrar a UTF-8 en POM y ficheros |
| Fallo DDL | `hbm2ddl` agresivo | Usar `update` o `validate` |

---
## 🗺 Roadmap / Próximos Pasos
- [ ] Entidades ejemplo (Producto, Cliente, Pedido)
- [ ] Pruebas JUnit para lógica
- [ ] Autenticación basada en BD
- [ ] i18n adicional (en, pt)
- [ ] Endpoints REST
- [ ] CI/CD (GitHub Actions / Jenkins)
- [ ] Métricas (Actuator / Prometheus)

---
## 📄 Licencia
Distribuido bajo la **Licencia MIT**. Consulta el archivo `LICENSE` para el texto completo.
Resumen rápido:
- Uso, copia, modificación y distribución permitidos.
- Incluir aviso de copyright y licencia.
- Software proporcionado "tal cual", sin garantías.

---
## 📬 Contacto
> 📇 **Tarjeta de Contacto**
> 
> Nombre: _Diedereich Alemán_  
> Correo: _diedereicha@uam.edu.ni_  
> LinkedIn: _https://www.linkedin.com/in/daamaleman/_  
> GitHub: _https://github.com/daamaleman_  
> Portafolio: _https://didudi.lat/_  
> Dirección: _Managua - Nicaragua_  

¿Interesado en colaborar? Abre un Issue o envía un PR.

---
✨ ¡Sigue construyendo y mejorando OpenStore!
