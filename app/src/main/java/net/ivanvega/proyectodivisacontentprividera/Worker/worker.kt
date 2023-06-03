package net.ivanvega.proyectodivisacontentprividera.Worker
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.Worker
import androidx.work.WorkerParameters
import net.ivanvega.proyectodivisacontentprividera.MiApplication
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.ivanvega.proyectodivisacontentprividera.Intercambio.Intercambio
import net.ivanvega.proyectodivisacontentprividera.Intercambio.monedaApi
import net.ivanvega.proyectodivisacontentprividera.db.MiDbMonedas
import java.time.LocalDateTime


val TAG ="WORKER"
private lateinit var db: MiDbMonedas
@RequiresApi(Build.VERSION_CODES.O)
val currentDate = LocalDateTime.now()
class worker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork(): Result {

        return try {
            GlobalScope.launch {
                db = (applicationContext as MiApplication).database
                try {
                    val response = monedaApi.retrofitService.obtenerApi()

                    for ((key, value) in response.rates) {
                        db.intercambioDao().insert(
                            Intercambio(0,key,value.toString(), response.time_last_update_utc,
                            currentDate.toString())
                        )
                        println("Clave: $key Valor: $value")
                    }
                    println("Ultima actualización: ${response.time_last_update_utc}")
                } catch (e: Exception) {

                }
            }

            Result.success()
        } catch (throwable: Throwable) {
            Log.e(TAG, "Error applying blur")
            throwable.printStackTrace()
            Result.failure()
        }
    }
}