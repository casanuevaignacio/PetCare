# 🐾 PetCare

Aplicación Android que conecta **dueños de mascotas** con **cuidadores**, permitiendo registrar mascotas, publicar servicios de cuidado y explorar mascotas disponibles para adopción a través de una API externa.

## 📱 Descripción

PetCare es una app con dos roles de usuario: **Dueño** y **Cuidador**. Los dueños pueden registrar a sus mascotas y buscar cuidadores disponibles. Los cuidadores pueden publicar sus servicios con precio y descripción. Además, el dashboard muestra en tiempo real mascotas disponibles para adopción obtenidas desde la API de [Huachitos.cl](https://huachitos.cl).

## 🏗️ Arquitectura

La app sigue una arquitectura en capas:

- **UI** → Fragments con ViewBinding y Navigation Component
- **ViewModel** → Manejo del estado y lógica de presentación
- **Repository** → Fuente única de verdad
- **Data** → Room (base de datos local) + Retrofit (API remota)

## 🛠️ Tecnologías y librerías

| Categoría | Librería |
|---|---|
| Lenguaje | Kotlin |
| UI | XML Views, ViewBinding, Material 3, Jetpack Compose (parcial) |
| Navegación | Navigation Component 2.7.7 |
| Base de datos local | Room 2.6.1 |
| Consumo de API | Retrofit 2.9.0 + Gson |
| Carga de imágenes | Glide 4.16.0 |
| Asincronía | Coroutines + ViewModel KTX |
| Build | Gradle KTS, compileSdk 35 |

## ✨ Funcionalidades

- **Login por rol**: ingreso como Dueño o Cuidador (datos persistidos en SharedPreferences)
- **Perfil de mascota**: registro de nombre, raza, edad, cuidados especiales y enfermedades
- **Servicios de cuidado**: los cuidadores crean servicios con título, descripción y precio
- **Lista de servicios**: los dueños exploran cuidadores disponibles con su contacto
- **Huachitos**: visualización de mascotas en adopción desde la API externa [huachitos.cl](https://huachitos.cl/api/animales)
- **Dashboard estilo Netflix**: scroll horizontal con mascotas (API) y servicios (Room)
- **Sistema de calificaciones**: los dueños pueden calificar a los cuidadores
- **Vista pública del cuidador**: perfil con reseñas y comentarios

## 📁 Estructura del proyecto

```
petcare/
├── adapter/          # Adapters para RecyclerViews (mascotas, servicios, comentarios)
├── data/
│   ├── local/        # Room: AppDatabase, DAOs (Pet, User, Service, Rating, Request)
│   ├── remote/       # Retrofit: HuachitosService, RetrofitClient
│   └── repository/   # PetRepository
├── model/            # Entidades: Pet, User, Service, Rating, Request, HuachitoPet
├── ui/
│   ├── caretaker/    # AvailablePetsFragment, CaretakerPublicViewFragment
│   ├── dashboard/    # DashboardFragment
│   ├── huachitos/    # HuachitosFragment
│   ├── login/        # LoginFragment
│   ├── pet/          # PetProfileFragment
│   ├── rating/       # RatingFragment
│   └── service/      # CreateServiceFragment, ServiceListFragment
└── viewmodel/        # PetViewModel
```

## 🚀 Cómo ejecutar el proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/casanuevaignacio/PetCare.git
   ```
2. Abre el proyecto en **Android Studio Hedgehog** o superior.
3. Sincroniza las dependencias con Gradle.
4. Ejecuta la app en un emulador o dispositivo físico.

> La sección de Huachitos y el Dashboard requieren conexión a internet para consumir la API de huachitos.cl.

## 📋 Requisitos

- Android Studio Hedgehog o superior
- Android SDK 24+ (minSdk 24, targetSdk 35)
- Kotlin 1.9.22
- Conexión a internet para la sección de adopciones

## 👨‍💻 Autor

**Ignacio Casanueva** — Ingeniero Comercial apasionado por el desarrollo de software y productos digitales.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Ignacio_Casanueva-blue?logo=linkedin)](https://www.linkedin.com/in/ignacio-casanueva-13624a8b/)
[![GitHub](https://img.shields.io/badge/GitHub-casanuevaignacio-black?logo=github)](https://github.com/casanuevaignacio)
