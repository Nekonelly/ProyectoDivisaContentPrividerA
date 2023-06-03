package net.ivanvega.proyectodivisacontentprividera.Intercambio

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Intercambio (
    @PrimaryKey(autoGenerate = true)
    val  _ID: Int,
    val CodigoCambioDivisa: String,
    val Divisa: String,
    val FechaActualizacion: String,
    val FechaConsulta: String

)