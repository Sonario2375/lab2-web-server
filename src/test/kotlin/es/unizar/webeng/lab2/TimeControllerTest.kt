package es.unizar.webeng.lab2

import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
class TimeControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var timeProvider: TimeProvider

    @Test
    fun timeIsJson() {
        Mockito.`when`(timeProvider.now()).thenReturn(LocalDateTime.now())

        mockMvc
            .perform(get("/time").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.time").exists())
    }

    // Test para "A step further" (Inyecta un proveedor fijo y verifica el timestamp exacto)
    @Test
    fun timeReturnsFixedTimestamp() {
        val fixedTime = LocalDateTime.of(2026, 10, 6, 15, 30, 0)
        Mockito.`when`(timeProvider.now()).thenReturn(fixedTime) // Hago que mock devuelva esa fecha específica

        mockMvc
            .perform(get("/time").accept(MediaType.APPLICATION_JSON)) // Revisamos el JSON
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.time").value("2026-10-06T15:30:00"))
    }
}
