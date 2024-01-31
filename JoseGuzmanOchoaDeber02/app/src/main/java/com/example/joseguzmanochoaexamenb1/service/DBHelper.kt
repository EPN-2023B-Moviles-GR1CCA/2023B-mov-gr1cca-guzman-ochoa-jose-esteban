import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "ComputadoresDB"

        private const val TABLE_MARCAS = "marcas"
        private const val COLUMN_MARCA_NOMBRE = "nombre"
        private const val COLUMN_MARCA_PAIS = "paisOrigen"

        private const val TABLE_COMPUTADORES = "computadores"
        private const val COLUMN_COMPUTADOR_MODELO = "modelo"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createMarcasTable = ("CREATE TABLE $TABLE_MARCAS (" +
                "$COLUMN_MARCA_NOMBRE TEXT PRIMARY KEY," +
                "$COLUMN_MARCA_PAIS TEXT," +
                ")")
        db.execSQL(createMarcasTable)

        val createComputadoresTable = ("CREATE TABLE $TABLE_COMPUTADORES (" +
                "$COLUMN_COMPUTADOR_MODELO TEXT PRIMARY KEY," +
                ")")
        db.execSQL(createComputadoresTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MARCAS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_COMPUTADORES")
        onCreate(db)
    }
}
