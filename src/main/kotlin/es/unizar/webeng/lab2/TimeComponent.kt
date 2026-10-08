package es.unizar.webeng.lab2

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

// DTO
data class TimeDTO(
    val time: LocalDateTime,
)

// Interfaz TimeProvider
interface TimeProvider {
    fun now(): LocalDateTime
}

// Implementación de TimeProvider
@Service
class TimeService : TimeProvider {
    override fun now(): LocalDateTime = LocalDateTime.now()
}

// Función de extensión
fun LocalDateTime.toDTO(): TimeDTO = TimeDTO(time = this)

// Controlador REST
// Al llamarse /time se envuelve el valor devuelto por service.now()
// en un DTO para enviarlo dentro de un objeto JSON
@RestController
class TimeController(
    private val service: TimeProvider,
) {
    @GetMapping("/time")
    fun time(): TimeDTO = service.now().toDTO()
}
