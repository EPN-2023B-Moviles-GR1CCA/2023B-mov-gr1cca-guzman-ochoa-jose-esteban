import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.joseguzmanochoaexamenb1.models.Computador
import com.example.joseguzmanochoaexamenb1.models.MarcaComputador

class CRUDService(private val context: Context) {
    private val dbHelper = DBHelper(context)

    fun addMarca(marca: MarcaComputador) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombre", marca.nombre)
            put("paisOrigen", marca.paisOrigen)
        }
        db.insert("marcas", null, values)
        db.close()
    }

    fun leerMarcas(): List<MarcaComputador> {
        val marcas = mutableListOf<MarcaComputador>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM marcas", null)
        if (cursor.moveToFirst()) {
            do {
                val marca = MarcaComputador(
                    nombre = cursor.getString(cursor.getColumnIndex("nombre")),
                    paisOrigen = cursor.getString(cursor.getColumnIndex("paisOrigen")),
                )
                marcas.add(marca)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return marcas
    }

    fun actualizarMarca(nombre: String, nuevaMarca: MarcaComputador) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("paisOrigen", nuevaMarca.paisOrigen)
        }
        db.update("marcas", values, "nombre = ?", arrayOf(nombre))
        db.close()
    }

    fun eliminarMarca(nombre: String) {
        val db = dbHelper.writableDatabase
        db.delete("marcas", "nombre = ?", arrayOf(nombre))
        db.close()
    }

    fun addComputador(computador: Computador) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("modelo", computador.modelo)
        }
        db.insert("computadores", null, values)
        db.close()
    }

    fun leerComputadores(): List<Computador> {
        val computadores = mutableListOf<Computador>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM computadores", null)
        if (cursor.moveToFirst()) {
            do {
                val computador = Computador(
                    modelo = cursor.getString(cursor.getColumnIndex("modelo")),
                )
                computadores.add(computador)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return computadores
    }

    fun actualizarComputador(modelo: String, nuevoComputador: Computador) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
        }
        db.update("computadores", values, "modelo = ?", arrayOf(modelo))
        db.close()
    }

    fun eliminarComputador(modelo: String) {
        val db = dbHelper.writableDatabase
        db.delete("computadores", "modelo = ?", arrayOf(modelo))
        db.close()
    }
}
