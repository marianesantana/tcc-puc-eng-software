package com.example.examine_ai.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.examine_ai.data.converters.ConsultaTypeConverter
import com.example.examine_ai.data.converters.DateTypeConverter
import com.example.examine_ai.data.converters.DiagnosticoTypeConverter
import com.example.examine_ai.data.converters.ExameTypeConverter
import com.example.examine_ai.data.converters.HistoricoMedicoTypeConverter
import com.example.examine_ai.data.converters.MedicamentoTypeConverter
import com.example.examine_ai.data.converters.MedicoTypeConverter
import com.example.examine_ai.data.converters.PacienteTypeConverter
import com.example.examine_ai.data.converters.TipoExameTypeConverter
import com.example.examine_ai.data.dao.ExameDao
import com.example.examine_ai.data.dao.PacienteDao
import com.example.examine_ai.data.dao.UserDao
import com.example.examine_ai.data.model.Consulta
import com.example.examine_ai.data.model.Diagnostico
import com.example.examine_ai.data.model.Exame
import com.example.examine_ai.data.model.HistoricoMedico
import com.example.examine_ai.data.model.Medicamento
import com.example.examine_ai.data.model.Medico
import com.example.examine_ai.data.model.Paciente
import com.example.examine_ai.data.model.Receita
import com.example.examine_ai.data.model.TipoExame
import com.example.examine_ai.data.model.User

@Database (entities = [Paciente::class,
                        Medico::class,
                        Medicamento::class,
                        User::class,
                        Diagnostico::class,
                        TipoExame::class,
                        Consulta::class,
                        HistoricoMedico::class,
                        Receita::class,
                        Exame::class], version = 5)
@TypeConverters(HistoricoMedicoTypeConverter::class, MedicamentoTypeConverter::class,
    ConsultaTypeConverter::class, DiagnosticoTypeConverter::class, ExameTypeConverter::class,
    PacienteTypeConverter::class, MedicoTypeConverter::class, DateTypeConverter::class, TipoExameTypeConverter::class)
abstract class AppDatabase: RoomDatabase(){
    companion object {
        @Volatile
        var INSTANCE: AppDatabase ? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "examine_ai_db").fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }



    abstract fun userDao(): UserDao
    abstract fun pacienteDao(): PacienteDao
    abstract fun exameDao(): ExameDao



}