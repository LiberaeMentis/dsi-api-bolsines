# GPS Tracker DSI - Backend Mock

Backend REST mock en Java + Spring Boot para simular 3 modelos de GPS Tracker en el dominio de gestion de bolsines.

## Contenido

- [Requisitos](#requisitos)
- [Ejecutar el proyecto](#ejecutar-el-proyecto)
- [Como probarlo](#como-probarlo)
- [Configuracion](#configuracion)
- [Endpoints](#endpoints)
- [Ejemplos de respuesta](#ejemplos-de-respuesta)
- [Manejo de errores](#manejo-de-errores)
- [Donde cambiar los datos mock](#donde-cambiar-los-datos-mock)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Sugerencias de conexion de clientes](#sugerencias-de-conexion-de-clientes)

Por consultas sobre esta API, dirigirse a la Ing. Sol Vega (vegamsol01@gmail.com).

## Requisitos

- Java 17
- Sin base de datos
- Sin autenticacion real (solo API Key mock)

## Ejecutar el proyecto

La app levanta en `http://localhost:8080`.

### Opcion principal 1: IntelliJ IDEA

1. Abrir la carpeta del proyecto.
2. Esperar indexado e importacion Maven.
3. Ejecutar la clase `GpsTrackerMockApplication`.

### Opcion principal 2: VS Code

1. Abrir la carpeta del proyecto.
2. Ir a `Run and Debug`.
3. Ejecutar `Run GPS Tracker Mock (Spring Boot)` (configurado en `.vscode/launch.json`).

### Opcion alternativa: Consola

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Linux/Mac:

```bash
./mvnw spring-boot:run
```

Si tenes Maven global:

```bash
mvn spring-boot:run
```

## Como probarlo

### Opcion 1: Archivo HTTP (IntelliJ / REST Client)

Usar `requests.http` y ejecutar cada request con `Run`.

### Opcion 2: Postman

1. Descargar e instalar Postman desde: `https://www.postman.com/downloads/`
2. Abrir Postman.
3. Ir a `Import` -> `Upload Files`.
4. Seleccionar el archivo: `src/main/resources/DSIBolsines.postman_collection.json`
5. Ejecutar las requests de la coleccion contra `http://localhost:8080`.
6. Usar la API Key correcta segun el modelo (ver seccion `Configuracion`).

### Opcion 3: cURL

```bash
curl http://localhost:8080/health
curl http://localhost:8080/api/mock/bolsines
curl -H "X-API-Key: sk_xtr_9F3a7C21bE6d4A10f2c8e5b7d913" "http://localhost:8080/api/xtr-4500l/getBolsinLocation?numeroBolsin=101&codigoComisionMedicaOrigen=CM-CBA-01"
curl -H "X-API-Key: sk_nav_7b61d9e4c3a2485fae10c77d5b2f" "http://localhost:8080/api/navtrack-qx-7a/retrieveTrackingData?numeroBolsin=101&codigoComisionMedicaDestino=CM-ROS-02"
curl -H "X-API-Key: sk_geo_f2d84a1c9e7345b8a6d1c3f0972e" "http://localhost:8080/api/geopulse-mtr-900/fetchCargoPositions?numeroBolsin=101"
```

## Configuracion

Archivo: `src/main/resources/application.properties`

- `server.port=8080`
- `gps.api.keys.xtr-4500l=sk_xtr_9F3a7C21bE6d4A10f2c8e5b7d913`
- `gps.api.keys.navtrack-qx-7a=sk_nav_7b61d9e4c3a2485fae10c77d5b2f`
- `gps.api.keys.geopulse-mtr-900=sk_geo_f2d84a1c9e7345b8a6d1c3f0972e`

## Endpoints

- `GET /health`
- `GET /api/mock/bolsines`
- `GET /api/mock/bolsines/{numeroBolsin}`
- `GET /api/xtr-4500l/getBolsinLocation?numeroBolsin={nro}&codigoComisionMedicaOrigen={codigo}`
- `GET /api/navtrack-qx-7a/retrieveTrackingData?numeroBolsin={nro}&codigoComisionMedicaDestino={codigo}`
- `GET /api/geopulse-mtr-900/fetchCargoPositions?numeroBolsin={nro}`

Header requerido para modelos GPS:

- XTR-4500L: `X-API-Key: sk_xtr_9F3a7C21bE6d4A10f2c8e5b7d913`
- NavTrack QX-7A: `X-API-Key: sk_nav_7b61d9e4c3a2485fae10c77d5b2f`
- GeoPulse MTR-900: `X-API-Key: sk_geo_f2d84a1c9e7345b8a6d1c3f0972e`

Si se envia una API Key incorrecta o de otro modelo, responde `401`.

## Ejemplos de respuesta

Health:

```json
{
  "status": "ok"
}
```

XTR-4500L:

```json
{
  "items": [
    {
      "numeroBolsin": 101,
      "latitud": -31.4201,
      "longitud": -64.1888,
      "fechaHoraUltimaActualizacion": "2026-03-07T12:30:00"
    }
  ]
}
```

NavTrack QX-7A:

```text
101,-31.4201,-64.1888,2026-03-07T12:30
```

GeoPulse MTR-900:

```json
[
  [101, -31.4201, -64.1888, "2026-03-07T12:30:00"]
]
```

## Manejo de errores

Formato:

```json
{
  "error": "API_KEY_INVALIDA",
  "mensaje": "La API Key provista no es valida.",
  "status": 401,
  "timestamp": "2026-03-07T12:30:00"
}
```

Estados implementados:

- API key invalida -> `401`
- Parametros faltantes/invalidos -> `400`
- Bolsin inexistente -> `404`
- Codigo de comision no coincide -> `404`

## Donde cambiar los datos mock

- Bolsines precargados: `src/main/java/ar/edu/dsi/gpstracker/repository/InMemoryBolsinRepository.java` en el metodo `init()`.
- API keys por modelo: `src/main/resources/application.properties` en `gps.api.keys.*`.
- Puerto: `src/main/resources/application.properties` en `server.port`.

## Estructura del proyecto

```text
src/main/java/ar/edu/dsi/gpstracker
  controller/
  dto/
  exception/
  config/
  model/
  repository/
  service/
```

## Sugerencias de conexion de clientes

Base URL local:

- `http://localhost:8080`

### Cliente Java (Spring Boot)

Ejemplo usando `RestClient`:

```java
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GpsTrackerClient {
    private static final String API_KEY_NAVTRACK = "sk_nav_7b61d9e4c3a2485fae10c77d5b2f";

    private final RestClient restClient = RestClient.builder()
            .baseUrl("http://localhost:8080")
            .build();

    public String obtenerNavTrack() {
        int numeroBolsin = 101;
        String codigoComisionMedicaDestino = "CM-ROS-02";

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/navtrack-qx-7a/retrieveTrackingData")
                        .queryParam("numeroBolsin", numeroBolsin)
                        .queryParam("codigoComisionMedicaDestino", codigoComisionMedicaDestino)
                        .build())
                .header("X-API-Key", API_KEY_NAVTRACK)
                .retrieve()
                .body(String.class);
    }
}
```

### Cliente Python

Ejemplo con `requests`:

```python
import requests

url = "http://localhost:8080/api/xtr-4500l/getBolsinLocation"
params = {
    "numeroBolsin": 101,
    "codigoComisionMedicaOrigen": "CM-CBA-01"
}
headers = {
    "X-API-Key": "sk_xtr_9F3a7C21bE6d4A10f2c8e5b7d913"
}

r = requests.get(url, params=params, headers=headers, timeout=10)
print(r.status_code, r.text)
```

### Cliente Node.js

Ejemplo con `fetch` (Node 18+):

```js
const baseUrl = "http://localhost:8080";
const endpoint = "/api/geopulse-mtr-900/fetchCargoPositions";
const apiKey = "sk_geo_f2d84a1c9e7345b8a6d1c3f0972e";
const numeroBolsin = 101;

const params = new URLSearchParams({
  numeroBolsin: String(numeroBolsin)
});

const url = `${baseUrl}${endpoint}?${params.toString()}`;

const response = await fetch(url, {
  method: "GET",
  headers: {
    "X-API-Key": apiKey
  }
});

const data = await response.json();
console.log(response.status, data);
```

Nota:

- Cada modelo usa una API Key distinta. Si el cliente usa la key de otro modelo, responde `401`.
