package com.example.app_sqlite

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        try {
            val bd = openOrCreateDatabase("app", MODE_PRIVATE, null)

            //bd.execSQL("DELETE FROM pessoa")

            bd.execSQL("CREATE TABLE IF NOT EXISTS pessoa (nome VARCHAR, idade INT(3))")


            bd.execSQL("UPDATE pessoa SET nome = 'Marcos Vasconcelos de Oliveira' WHERE nome = 'Marcos'")

            bd.execSQL("DROP TABLE pessoa")
            bd.execSQL("CREATE TABLE IF NOT EXISTS pessoa (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3))")

            bd.execSQL("INSERT INTO pessoa (nome, idade) VALUES ('Marcos', 18)")
            bd.execSQL("INSERT INTO pessoa (nome, idade) VALUES ('Fatec Diadema', 20)")
            bd.execSQL("INSERT INTO pessoa (nome, idade) VALUES ('Maria', 25)")


            //val cursor = bd.rawQuery("SELECT id, nome, idade FROM pessoa ORDER BY nome", null)
            val cursor = bd.rawQuery("SELECT * FROM pessoa ORDER BY nome", null)

            val indiceId = cursor.getColumnIndex("id")
            val indiceNome = cursor!!.getColumnIndex("nome")
            val indiceIdade = cursor!!.getColumnIndex("idade")

            cursor.moveToFirst()
            while(cursor != null) {
                /*

                Log.i("RESULTADO = NOME: ", cursor.getString(0))
                Log.i("RESULTADO = NOME: ", cursor.getString(indiceNome))
                Log.i("RESULTADO = IDADE: ", cursor.getString(indiceIdade))

                 */

                Log.i("RESULTADO - ID: ", cursor.getString(indiceId) + "NOME: " + cursor.getString(indiceNome) + "IDADE: " + cursor.getString(indiceIdade))
                cursor.moveToNext()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}