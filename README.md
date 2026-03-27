# SIP — Sistema de Investigaciones Policiales

## Información académica

- Asignatura: Programación Orientada a Objetos  
- Carrera: Tecnologías de la Información / Desarrollo de Software  
- Institución: Tecnológico  
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

Estas entidades comparten una estructura común, pero conservan atributos específicos según su rol.

---

### Evidencias

Las evidencias representan elementos clave dentro de una investigación y se clasifican en:

- Evidencia física  
- Evidencia digital  
- Evidencia forense  

Cada tipo de evidencia contiene información especializada, pero mantiene una base común.

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

- Modelo (models): representación de entidades  
- Servicio (service): lógica de negocio  
- Persistencia (persistence): almacenamiento de datos  
- Excepciones (exceptions): manejo de errores  

---

### Modelado del sistema

- Persona (clase abstracta)
  - Detective  
  - Sospechoso  
  - Testigo  
  - Victima  

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

El sistema utiliza archivos CSV para almacenar la información.

- toCSV(): convierte objetos a texto  
- fromCSV(): reconstruye objetos desde texto  

Este mecanismo permite guardar y recuperar datos de forma consistente.

---

### Conceptos de Programación Orientada a Objetos aplicados

- Encapsulamiento: protección de atributos mediante modificadores de acceso  
- Herencia: reutilización de código en clases derivadas  
- Polimorfismo: manejo de objetos a través de referencias generales  
- Clases abstractas: definición de estructuras base  
- Interfaces: definición de comportamientos comunes  
- Manejo de excepciones: control de errores mediante clases personalizadas  
- Uso de colecciones: almacenamiento dinámico con List  
- Iteradores: eliminación segura de elementos  
- Separación por capas: organización del sistema  

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

SIP permite gestionar investigaciones de forma ordenada, evitando problemas como la duplicación de información o la falta de control sobre los datos.

El sistema demuestra una implementación sólida de los principios de Programación Orientada a Objetos y establece una base adecuada para futuras mejoras y expansión.