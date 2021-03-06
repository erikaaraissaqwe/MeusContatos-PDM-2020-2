import ContatoSqlite.Constantes.CONTATO_TABLE
import ContatoSqlite.Constantes.CREATE_CONTATO_TABLE_STATEMENT
import ContatoSqlite.Constantes.EMAIL_COLUMN
import ContatoSqlite.Constantes.LISTA_CONTATOS_DATABASE
import ContatoSqlite.Constantes.NOME_COLUMN
import ContatoSqlite.Constantes.TELEFONE_COLUMN
import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.meuscontatos.R
import com.example.meuscontatos.model.Contato
import com.example.meuscontatos.model.ContatoDao

class ContatoSqlite(context: Context): ContatoDao {
    object Constantes {
        val LISTA_CONTATOS_DATABASE = "listaContatos"
        val CONTATO_TABLE   = "contato"
        val NOME_COLUMN     = "nome"
        val TELEFONE_COLUMN = "telefone"
        val EMAIL_COLUMN    = "email"

        /* Statement que será usado na primeira vez para criar a tabela. Em uma única li
        nha executada uma única vez a concatenação de String não fará diferença no desempe
        nho, além de ser mais didático */
        val CREATE_CONTATO_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS ${CONTATO_TABLE} (" +
                "${NOME_COLUMN} TEXT NOT NULL PRIMARY KEY, " +
                "${TELEFONE_COLUMN} TEXT NOT NULL, " +
                "${EMAIL_COLUMN} TEXT NOT NULL );"
    }

    // Referência para o banco de dados do aplicativo
    val listaContatosDb: SQLiteDatabase
    init{
        /* Criando (ou abrindo) e conectando-se ao Banco de Dados a partir do contexto tal qual um
        Shared Preferences já que o listaContatos.bd estará na área de arquivos do aplicativo. */
        listaContatosDb = context.openOrCreateDatabase(LISTA_CONTATOS_DATABASE, MODE_PRIVATE, null)

        // Criando a tabela
        try{
            listaContatosDb.execSQL(CREATE_CONTATO_TABLE_STATEMENT)
        }
        catch (se: SQLException) {
            Log.e(context.getString(R.string.app_name), se.toString())
        }
    }

    override fun createContato(contato: Contato) {
        // Mapeamento atributo-valor
        val values = ContentValues()
        values.put(NOME_COLUMN, contato.nome)
        values.put(TELEFONE_COLUMN, contato.telefone)
        values.put(EMAIL_COLUMN, contato.email)

        // Insert
        listaContatosDb.insert(CONTATO_TABLE, null, values)
    }

    override fun readContato(nome: String): Contato {
        // Consulta usando a função query
        val contatoCursor = listaContatosDb.query(
            true,          // distinct
            CONTATO_TABLE, // tabela
            null,          // columns
            "${NOME_COLUMN} = ?",  // selection
            arrayOf("${nome}"),    // selectionArgs
            null, // groupBy
            null, // having
            null, // orderBy
            null // limit
        )

        // Retorna o contato encontrado ou um contato vazio
        return if (contatoCursor.moveToFirst())
            Contato(
                contatoCursor.getString(contatoCursor.getColumnIndex(NOME_COLUMN)),
                contatoCursor.getString(contatoCursor.getColumnIndex(TELEFONE_COLUMN)),
                contatoCursor.getString(contatoCursor.getColumnIndex(EMAIL_COLUMN))
            )
        else
            Contato()
    }

    override fun readContatos(): MutableList<Contato> {
        val contatosList: MutableList<Contato> = mutableListOf()

        // Consulta usando raw query
        val contatoQuery = "SELECT * FROM ${CONTATO_TABLE};"
        val contatosCursor = listaContatosDb.rawQuery(contatoQuery, null)

        while (contatosCursor.moveToNext()) {
            contatosList.add(
                Contato(
                    contatosCursor.getString(contatosCursor.getColumnIndex(NOME_COLUMN)),
                    contatosCursor.getString(contatosCursor.getColumnIndex(TELEFONE_COLUMN)),
                    contatosCursor.getString(contatosCursor.getColumnIndex(EMAIL_COLUMN))
                )
            )
        }

        return contatosList
    }

    override fun updateContato(contato: Contato) {
        // Mapeamento atributo-valor
        val values = ContentValues()
        values.put(TELEFONE_COLUMN, contato.telefone)
        values.put(EMAIL_COLUMN, contato.email)

        // Update
        listaContatosDb.update(
            CONTATO_TABLE, // table
            values,        // values
            "${NOME_COLUMN} = ?", // whereClause
            arrayOf(contato.nome) // whereArgs
        )
    }

    override fun deleteContato(nome: String) {
        listaContatosDb.delete(
            CONTATO_TABLE,        // table
            "${NOME_COLUMN} = ?", // whereClause
            arrayOf(nome)         // whereArgs
        )
    }
}