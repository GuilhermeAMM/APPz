package com.example.appz

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Atividade::class], version = 1)
abstract class AppZDatabase : RoomDatabase(){

    abstract fun atividadeDao() : AtividadeDAO

    companion object {

        private var database: AppZDatabase? = null

        private val DATABASE = "AppZDB"

        fun getInstance(context: Context): AppZDatabase? {

            if (database == null)
                return criaBanco(context)
            else
                return database

        }

        private fun criaBanco(context: Context): AppZDatabase{
            return Room.databaseBuilder(context, AppZDatabase::class.java, DATABASE)
                .allowMainThreadQueries()
                .build()


        }

    }




}