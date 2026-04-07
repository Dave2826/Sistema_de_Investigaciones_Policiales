# SIP — Sistema de Investigaciones Policiales

## Información académica

- Asignatura: Programación Orientada a Objetos  
- Institución: Tecnológico de Software  
- Cuatrimestre: Segundo  
- Tipo de proyecto: Proyecto final  

---

## Descripción general

SIP (Sistema de Investigaciones Policiales) es una aplicación desarrollada en Java cuyo objetivo es gestionar de manera estructurada la información relacionada con investigaciones criminales.

El sistema permite organizar y controlar los elementos principales de un caso, incluyendo personas involucradas, evidencias, entrevistas y reportes, manteniendo una estructura clara, consistente y fácil de consultar.

---

## Problema que resuelve

En el manejo de investigaciones, la información suele estar dispersa, desorganizada o duplicada, lo que dificulta su análisis y seguimiento.

SIP resuelve estos problemas mediante:

- Centralización de la información en un solo sistema  
- Organización estructurada de los datos  
- Control de duplicidad de registros  
- Validación de información desde su creación  
- Acceso rápido y ordenado a los datos  

---

## Propósito del sistema

El sistema tiene como finalidad proporcionar una herramienta que permita:

- Registrar y administrar casos de investigación  
- Asociar correctamente personas a cada caso  
- Gestionar evidencias con control estructurado  
- Documentar entrevistas realizadas durante el proceso  
- Generar reportes claros para análisis y seguimiento  

---

## Funcionamiento del sistema

El sistema sigue un flujo lógico que representa el proceso de una investigación:

1. Creación de un caso  
2. Registro de personas involucradas  
3. Asociación de evidencias al caso  
4. Registro de entrevistas  
5. Generación de reportes  
6. Almacenamiento y recuperación de información  

Cada operación incluye validaciones que garantizan la integridad y consistencia de los datos.

---

## Gestión de información

### Personas

El sistema maneja distintos tipos de personas dentro de una investigación:

- Detective: encargado del caso  
- Sospechoso: posible responsable  
- Testigo: aporta información  
- Víctima: afectado por el caso  

Estas entidades comparten una estructura común mediante herencia, manteniendo atributos específicos según su rol.

---

### Evidencias

Las evidencias representan elementos clave dentro de una investigación y se clasifican en:

- Evidencia física  
- Evidencia digital  
- Evidencia forense  

Cada tipo contiene información especializada, manteniendo una base común y permitiendo comportamiento polimórfico.

---

### Entrevistas

Permiten registrar interacciones entre el detective y otras personas involucradas, almacenando información relevante para el caso.

---

### Reportes

Los reportes permiten documentar y resumir la información de un caso de manera clara y organizada.

---

## Características del sistema

- Organización estructurada de la información  
- Identificación única de elementos  
- Validaciones en la creación de objetos  
- Manejo controlado de errores  
- Persistencia de datos  
- Diseño modular y escalable  

---

## Equipo de desarrollo

- Edwuard Chay  
- David Morales  
- Giovana Díaz  
- Cristopher Euan  
- Joshua Cruz  

---

## Sección técnica

### Arquitectura

El sistema está dividido en capas para mejorar su organización:

- **Modelo (models):** representación de entidades  
- **Servicio (service):** lógica de negocio  
- **Persistencia (persistence):** almacenamiento de datos  
- **Excepciones (exceptions):** manejo de errores  

Esta separación permite alta cohesión y bajo acoplamiento.

---

### Modelado del sistema

- Persona (clase abstracta)
  - Detective  
  - Sospechoso  
  - Testigo  
  - Víctima  

- Evidencia (clase abstracta)
  - EvidenciaFisica  
  - EvidenciaDigital  
  - EvidenciaForense  

- Clases principales:
  - Caso  
  - Entrevista  
  - Reporte  

---

### Persistencia de datos

El sistema implementa persistencia utilizando archivos CSV independientes por tipo de entidad:

- personas.csv  
- casos.csv  
- evidencias.csv  
- entrevistas.csv  
- reportes.csv  

Cada clase implementa:

- `toCSV()`: convierte el objeto a texto  
- `fromCSV(String linea)`: reconstruye el objeto  

Se implementa deserialización polimórfica en entidades como `Persona` y `Evidencia`.

Además, el sistema incluye:

- Validación de datos al deserializar  
- Manejo de datos corruptos  
- Uso de `try-with-resources`  
- Verificación de existencia de archivos  
- Reconstrucción completa del sistema al iniciar  

---

### Manejo de excepciones

El sistema utiliza excepciones personalizadas:

- `CasoCerradoException`  
- `ElementoDuplicadoException`  
- `CSVInvalidoException`  

Se evita el uso de `catch(Exception)` genérico, manejando errores de forma controlada.

---

### Flujo del sistema

1. Carga de datos desde archivos CSV  
2. Reconstrucción de objetos  
3. Ejecución de operaciones  
4. Persistencia automática después de cada cambio  

---

### Conceptos de Programación Orientada a Objetos aplicados

- Encapsulamiento  
- Herencia  
- Polimorfismo  
- Clases abstractas  
- Interfaces  
- Manejo de excepciones  
- Uso de colecciones (`List`)  
- Iteradores (`Iterator`)  
- Separación por capas  

---

### Estructura del proyecto
SIP/
│
├── exceptions/
├── interfaces/
├── models/
├── persistence/
├── service/
├── Main.java
└── README.md

---

## Conclusión

SIP implementa un sistema completo de gestión de investigaciones policiales, integrando persistencia de datos, validación robusta y una arquitectura en capas bien definida.

El proyecto demuestra la aplicación práctica de los principios de Programación Orientada a Objetos, permitiendo la manipulación estructurada de información compleja y garantizando consistencia, integridad y escalabilidad.

El sistema queda preparado para futuras mejoras, como interfaces gráficas o integración con bases de datos.
