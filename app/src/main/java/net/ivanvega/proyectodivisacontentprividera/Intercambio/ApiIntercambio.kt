package net.ivanvega.proyectodivisacontentprividera.Intercambio

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
private const val BASE_URL = "https://open.er-api.com/v6/latest/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()
data class respuesta (
    @Json(name="time_last_update_utc") val time_last_update_utc: String,
    @Json(name = "rates") val rates: Map<String, Double>
)
interface servicioApi { @GET("MXN") suspend fun obtenerApi(): respuesta
}
object monedaApi {
    val retrofitService : servicioApi by lazy {
        retrofit.create(servicioApi::class.java) }
}