package net.ivanvega.proyectodivisacontentprividera.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.ivanvega.proyectodivisacontentprividera.Intercambio.Intercambio
import net.ivanvega.proyectodivisacontentprividera.Intercambio.IntercambioDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = arrayOf(Moneda::class,Intercambio::class), version = 1)
abstract class MiDbMonedas : RoomDatabase() {
    abstract fun monedaDao(): MonedaDao
    abstract fun intercambioDao(): IntercambioDao

    private class ExchangeDatabaseCallback(private val scope: CoroutineScope)
        : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->
                scope.launch (Dispatchers.IO){
                    var monedaDao = database.monedaDao()
                    monedaDao.deleteAll()

                }
            }

        }
    }

    companion object{
        @Volatile
        private var INSTANCE: MiDbMonedas?=null

        val databaseexecutor :
                ExecutorService =
            Executors.newFixedThreadPool(4)

        fun getDatabase(context: Context, scope: CoroutineScope): MiDbMonedas {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context, MiDbMonedas::class.java,
                    "midbmonedas"
                ).addCallback(ExchangeDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}