
# Java-POO-Excersice: La Batalla de las Almas

## Descripción del Proyecto

Este repositorio contiene un ejercicio de simulación implementado en Java, centrado en los principios de la **Programación Orientada a Objetos (POO)**.

El proyecto simula una **Batalla de las Almas**, donde entidades **Físicas** (Humanos) son sometidas a un **Conflicto Moral** continuo orquestado por entidades **Incorpóreas** (Ángeles y Demonios). La lógica del combate y las fórmulas matemáticas determinan la evolución de la **Bondad**, **Maldad** y **Fe** del Humano en cada turno. El objetivo final es determinar la salvación del Humano.

### 🔑 Conceptos POO Aplicados

* **Herencia:** Uso de clases base (`Fisico`, `Incorporeo`) para crear jerarquías.
* **Polimorfismo:** Implementación de habilidades (`enganiar`, `proteger`, etc.) con diferentes lógicas según la subclase (`Angel` vs. `Demonio`).
* **Interfaces:** Uso de la interfaz `Espiritual` para definir comportamientos comunes (`rezar`).
* **Encapsulamiento:** Gestión de atributos como `fe`, `bondad` y `maldad` mediante métodos *getters* y *setters*.

-----

## 🚀 Cómo Empezar

### 📋 Prerrequisitos

Necesitarás tener instalado:

1.  **JDK (Java Development Kit)** versión 11 o superior.
2.  Un **IDE** de Java (IntelliJ IDEA, Eclipse o VS Code) o acceso a la terminal para la compilación.

### ⚙️ Instalación

1.  Clona el repositorio en tu máquina local:
    ```bash
    git clone https://github.com/SozaJostin-Sc/Java-POO-Excersice.git
    ```
2.  Navega al directorio del proyecto:
    ```bash
    cd Java-POO-Excersice
    ```
3.  Abre el proyecto en tu IDE preferido.

-----

## ▶️ Uso y Ejecución

La simulación se inicia desde la clase principal `Batalla.java`, ubicada en el paquete `main`.

El método `main` está configurado para ejecutarse sin argumentos de línea de comandos, utilizando constantes internas para definir el tamaño de la simulación.

### 📝 Parámetros de Simulación (Constantes)

Puedes ajustar el comportamiento de la simulación modificando las constantes estáticas en la clase `main.Batalla`:

| Constante | Descripción | Valor Predeterminado |
| :--- | :--- | :--- |
| `NUM_HUMANOS` | Cantidad de Humanos que participan en la simulación. | `5` |
| `NUM_ANGELES` | Cantidad de entidades Angel disponibles. | `3` |
| `NUM_DEMONIOS` | Cantidad de entidades Demonio disponibles. | `3` |
| `NUM_TURNOS` | Duración total de la simulación. | `10` |

### 🛠️ Ejecución (Desde la Terminal)

1.  Compila las clases (asegúrate de incluir todos los paquetes: `entidades.fisicas`, `entidades.incorporeas`, etc.).
2.  Ejecuta la clase principal `Batalla.java`:
    ```bash
    java main.Batalla
    ```

La consola mostrará la inicialización, la progresión turno por turno de los conflictos morales y, finalmente, el resumen de cuántos Humanos fueron salvados (según el criterio de la clase `DiosCristiano`).

-----

## 🗺️ Estructura del Proyecto

El proyecto se organiza en los siguientes paquetes principales:

| Paquete | Descripción | Clases Clave |
| :--- | :--- | :--- |
| `main` | Contiene el punto de entrada de la aplicación y el motor de la simulación. | `Batalla` |
| `entidades.fisicas` | Entidades que representan seres con cuerpo físico. | `Humano` |
| `entidades.incorporeas` | Entidades espirituales que interactúan en el conflicto. | `Angel`, `Demonio`, `DiosCristiano` |
| `interfaces` | Define contratos de comportamiento (ej. `Espiritual`, `Incorporeo`). | N/A (asumido) |

-----

## 💡 Contribuciones

Si tienes sugerencias para mejorar las fórmulas de combate, las estructuras de las clases o refactorizar el código, ¡son bienvenidas\!

1.  Haz un *fork* del repositorio.
2.  Crea una rama (`git checkout -b feature/nueva-logica`).
3.  Haz *commit* de tus cambios (`git commit -m 'feat: agrega nueva fórmula de seducción'`).
4.  Haz *push* a la rama (`git push origin feature/nueva-logica`).
5.  Abre un *Pull Request*.

-----

## 📧 Contacto

* **Jostin Soza** - ([@SozaJostin-Sc](https://www.google.com/search?q=https://github.com/SozaJostin-Sc))

