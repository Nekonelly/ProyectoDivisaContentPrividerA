package net.ivanvega.proyectodivisacontentprividera

import android.app.Application
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
//import net.ivanvega.proyectodivisacontentprividera.Worker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import net.ivanvega.proyectodivisacontentprividera.Worker.worker
import java.util.concurrent.TimeUnit
//import net.ivanvega.proyectodivisacontentprividera.Worker.PeriodicWorkRequestBuilder
import net.ivanvega.proyectodivisacontentprividera.db.MiDbMonedas


class MiApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { MiDbMonedas.getDatabase(this, applicationScope) }
    //val repositoryMoneda by lazy {  MonedaRepository (database.monedaDao()) }

    override fun onCreate() {
        super.onCreate()
        val workRequest = PeriodicWorkRequestBuilder<worker>(1, TimeUnit.DAYS).build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }
}