package com.example.app_sqlite

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.widget.TextView
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        try {
            val bd = openOrCreateDatabase("app", MODE_PRIVATE, null)

            //Excluir a tabela aluno
            bd.execSQL("DROP TABLE IF EXISTS aluno")


            bd.execSQL("CREATE TABLE IF NOT EXISTS aluno (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(50), dataNascimento DATE, ra VARCHAR(13), curso VARCHAR(40), turma VARCHAR(20), turno VARCHAR(20))")

            //bd.execSQL("DROP TABLE aluno")

            bd.execSQL("INSERT INTO aluno (nome, dataNascimento, ra, curso, turma, turno) VALUES ('José', '2000-03-09', '1234567891011', 'DSM', 'DSM2', 'Manhã')")
            bd.execSQL("INSERT INTO aluno (nome, dataNascimento, ra, curso, turma, turno) VALUES ('Pedro Costa', '2003-04-11', '1110987654321', 'DSM', 'DSM1', 'Noite')")
            bd.execSQL("INSERT INTO aluno (nome, dataNascimento, ra, curso, turma, turno) VALUES ('Maria Oliveira', '2002-05-10', '1234567891011', 'DSM', 'DSM2', 'Manhã')")
            bd.execSQL("INSERT INTO aluno (nome, dataNascimento, ra, curso, turma, turno) VALUES ('João Santos', '2001-06-12', '4002892270707', 'DSM', 'DSM1', 'Noite')")
            bd.execSQL("INSERT INTO aluno (nome, dataNascimento, ra, curso, turma, turno) VALUES ('Ana Souza', '2004-07-13', '0800707070707', 'DSM', 'DSM2', 'Manhã')")
            bd.execSQL("INSERT INTO aluno (nome, dataNascimento, ra, curso, turma, turno) VALUES ('Carlos Pereira', '2005-08-14', '5963269872014', 'DSM', 'DSM1', 'Noite')")
            bd.execSQL("INSERT INTO aluno (nome, dataNascimento, ra, curso, turma, turno) VALUES ('Marta', '2006-09-15', '6354793200651', 'DSM', 'DSM2', 'Manhã')")
            bd.execSQL("INSERT INTO aluno (nome, dataNascimento, ra, curso, turma, turno) VALUES ('Ricardo Santos', '2002-10-16', '1234567891011', 'DSM', 'DSM1', 'Noite')")
            bd.execSQL("INSERT INTO aluno (nome, dataNascimento, ra, curso, turma, turno) VALUES ('Henrique Bonifácio', '2000-11-17', '1110987654321', 'DSM', 'DSM2', 'Manhã')")
            bd.execSQL("INSERT INTO aluno (nome, dataNascimento, ra, curso, turma, turno) VALUES ('Angela Lopes', '1999-12-18', '1234567891011', 'DSM', 'DSM5', 'Noite')")

            bd.execSQL("UPDATE aluno SET nome = 'José da Silva' WHERE nome = 'José'")
            bd.execSQL("UPDATE aluno SET nome = 'Marta Ribeiro' WHERE nome = 'Marta'")
            bd.execSQL("UPDATE aluno SET curso = 'ADS', turma = 'ADS2' WHERE nome = 'Henrique Bonifácio'")

            val outputTextView = findViewById<TextView>(R.id.bancoDeDados)
            val stringBuilder = StringBuilder()

            //val cursor = bd.rawQuery("SELECT id, nome, idade FROM pessoa ORDER BY nome", null)
            val cursor = bd.rawQuery("SELECT * FROM aluno ORDER BY nome", null)

            val indiceId = cursor.getColumnIndex("id")
            val indiceNome = cursor!!.getColumnIndex("nome")
            val indiceCurso = cursor!!.getColumnIndex("curso")

            cursor.moveToFirst()
            while(!cursor.isAfterLast) {
                val id = cursor.getInt(indiceId)
                val nome = cursor.getString(indiceNome)
                val curso = cursor.getString(indiceCurso)
                stringBuilder.append("ID: $id, Nome: $nome, Curso: $curso\n")
                cursor.moveToNext()
            }

            outputTextView.text = stringBuilder.toString()

            cursor.close()
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