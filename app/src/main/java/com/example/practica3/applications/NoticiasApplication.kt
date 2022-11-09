package com.example.practica3.applications

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.practica3.databases.NoticiasDatabase

class NoticiasApplication: Application() {

    companion object {

        lateinit var database: NoticiasDatabase

    }

    override fun onCreate() {

        super.onCreate()

        /*val MIGRATION_1_2 = object : Migration(1, 2) {

            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("CREATE TABLE UsuariosEntity (" +
                        "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        "username TEXT NOT NULL DEFAULT '', " +
                        "password TEXT NOT NULL DEFAULT '');")

            }

        }*/

        database = Room.databaseBuilder(
            this,
            NoticiasDatabase::class.java,
            "NoticiasDatabase")
            //.addMigrations(MIGRATION_1_2)
            .build()

    }

}