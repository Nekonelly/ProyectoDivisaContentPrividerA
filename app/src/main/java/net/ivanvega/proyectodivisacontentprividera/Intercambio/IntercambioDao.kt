package net.ivanvega.proyectodivisacontentprividera.Intercambio

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IntercambioDao {

    @Insert
    suspend fun insert(exchange: Intercambio)

    @Query("select * from Intercambio")
    fun getAll(): kotlinx.coroutines.flow.Flow<List<Intercambio>>

    @Query("DELETE FROM Intercambio")
    fun deleteAll() : Int

    @Query("select * from Intercambio order by CodigoCambioDivisa")
    fun getAllCursor(): Cursor


}