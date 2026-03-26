
# SIP — Sistema de Investigaciones Policiales

## Descripción

SIP es un sistema desarrollado en Java con Programación Orientada a Objetos para administrar investigaciones policiales. Su objetivo es llevar un control ordenado de los casos, las personas involucradas, las evidencias, las entrevistas y los reportes generados durante el proceso de investigación.

## Integrantes del equipo

- Edwuard Chay
- David Morales
- Giovana Díaz
- Cristopher Euan
- Joshua Cruz

## Objetivo del proyecto
Este proyecto fue elaborado como parte de la materia de Programación Orientada a Objetos, con el propósito de aplicar conceptos fundamentales como:

- encapsulamiento
- herencia
- polimorfismo
- clases abstractas
- interfaces
- composición y asociación
- constructores con validación
- uso de `toString()`
- **manejo de excepciones personalizadas** (`try/catch`, `throws`)
- **colecciones y genéricos** (`List<T>`, `Iterator`, iteración con `for-each`)
- **separación de responsabilidades** (Capa de Servicio)

## Estructura del sistema
El sistema está compuesto por las siguientes capas, clases e interfaces:

### Capa de Servicio
- **CasoServicio**: contiene toda la lógica de negocio para agregar, buscar, filtrar y eliminar personas o evidencias, aplicando validaciones y lanzando excepciones.

### Capa de Modelo (Entidades)
- **Caso**: administra toda la información general de una investigación.
- **Persona** (Clase abstracta): clase base para representar a las personas involucradas.
  - **Detective**, **Sospechoso**, **Testigo**, **Victima**
- **Evidencia** (Clase abstracta): clase base para representar las pruebas o indicios.
  - **EvidenciaFisica**, **EvidenciaDigital**, **EvidenciaForense**
- **Entrevista**: registra entrevistas realizadas durante la investigación.
- **Reporte**: almacena reportes o resúmenes del caso.

### Capa de Excepciones
- **CasoCerradoException**: lanzada cuando se intenta modificar un caso que ya ha sido cerrado.
- **ElementoDuplicadoException**: lanzada cuando se intenta registrar una persona o evidencia que ya existe en el caso.

### Interfaz
- **Documentable**: define los métodos `generarResumen()` y `mostrarDetalles()`.

## Funcionalidades principales
- Registrar casos policiales
- Agregar personas involucradas y registrar evidencias con **validaciones de duplicidad**
- **Búsqueda** de personas y evidencias por identificador único devolviendo objetos
- **Filtrado** de personas por rol y evidencias por estado devolviendo sublistas
- **Eliminación** segura de evidencias mediante el uso de `Iterator`
- Mostrar resúmenes y detalles de la información haciendo uso de **polimorfismo**
- Manejo correcto del flujo de errores mediante intercepción de **excepciones personalizadas**

## Tecnologías utilizadas
- Java
- Programación Orientada a Objetos
- UML

## Organización del proyecto
```text
SIP/
│
├── docs/
│   └── UML.png
│
├── exceptions/
│   ├── CasoCerradoException.java
│   └── ElementoDuplicadoException.java
│
├── interfaces/
│   └── Documentable.java
│
├── models/
│   ├── Caso.java
│   ├── Detective.java
│   ├── Entrevista.java
│   ├── Evidencia.java
│   ├── EvidenciaDigital.java
│   ├── EvidenciaFisica.java
│   ├── EvidenciaForense.java
│   ├── Persona.java
│   ├── Reporte.java
│   ├── Sospechoso.java
│   ├── Testigo.java
│   └── Victima.java
│
├── service/
│   └── CasoServicio.java
│
├── Main.java
└── README.md
```
=======
# Sistema_de_Investigaciones_Policiales
Sistema POO de investigación policial

