package net.ivanvega.proyectodivisacontentprividera.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import net.ivanvega.proyectodivisacontentprividera.MiApplication
import net.ivanvega.proyectodivisacontentprividera.db.MiDbMonedas

private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {

    addURI("net.ivanvega.proyectodivisacontentprividera", "monedas", 1)
    addURI("net.ivanvega.proyectodivisacontentprividera", "monedas/#", 2)
    addURI("net.ivanvega.proyectodivisacontentprividera", "monedas/*", 3)
}

class MiProveedorDeContenido : ContentProvider() {
    private lateinit var db: MiDbMonedas

    override fun onCreate(): Boolean {
        db = (context as MiApplication).database
        return true
    }
    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        var cursor: Cursor? = null
        when( sUriMatcher.match(p0)){

            1 -> {
                cursor =  db.intercambioDao().getAllCursor()
            }
            2 -> {
            }
            3 -> {
            }
            else -> {
            }
        }
        return cursor
    }

    override fun getType(p0: Uri): String? {
        var typeMime: String = "vnd.android.cursor.dir/vnd.net.ivanvega.provider.monedas"
        when( sUriMatcher.match(p0)){

            1 -> {
                typeMime = "vnd.android.cursor.dir/vnd.net.ivanvega.provider.monedas"
            }
            2 -> {
                typeMime = "vnd.android.cursor.item/vnd.net.ivanvega.provider.monedas"
            }
            3 -> {
                typeMime = "vnd.android.cursor.item/vnd.net.ivanvega.provider.monedas"
            }
            else -> {
            }
        }
        return  typeMime
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        //TODO("Not yet implemented")
        when( sUriMatcher.match(p0)){
            1 -> {
            }
            2 -> {
            }
            3 -> {
            }
            else -> {
            }
        }
        return null
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        when( sUriMatcher.match(p0)){
            1 -> {
            }
            2 -> {
            }
            3 -> {
            }
            else -> {
            }
        }
        return  0
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        when( sUriMatcher.match(p0)){
            1 -> {
            }
            2 -> {
            }
            3 -> {
            }
            else -> {
            }
        }
        return  0
    }
}