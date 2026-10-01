package es.unizar.webeng.lab2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.resttestclient.TestRestTemplate
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

/**
 * Pruebas de integración para verificar el correcto renderizado de la página
 * de error personalizada [error.html] cuando se consulta una ruta inexistente.
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class ErrorPageTest {

    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var client: TestRestTemplate

    @Test
    fun unknownPathRendersErrorHtml() {
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.TEXT_HTML)

        val response = client.exchange(
            "http://127.0.0.1:$port/missing",
            HttpMethod.GET,
            HttpEntity<Void>(headers),
            String::class.java,
        )

        // 1. Verifica que el estado HTTP sea 404 Not Found
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)

        val body = response.body ?: ""

        // 2. Verifica el marcador de tu HTML (reemplaza "your marker")
        assertTrue(body.contains("Custom Error Page Marker"))

        // 3. Step further: Comprueba que el status (404) y el path (/missing) están en el HTML
        assertTrue(body.contains("404"))
        assertTrue(body.contains("/missing"))
    }
}