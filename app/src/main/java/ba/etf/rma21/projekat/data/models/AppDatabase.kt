package ba.etf.rma21.projekat.data.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ba.etf.rma21.projekat.Converters
import ba.etf.rma21.projekat.data.dao.*

@Database(entities = arrayOf(Grupa::class,Predmet::class,Kviz::class, Pitanje::class, Odgovor::class, Account::class, KvizTaken::class, GrupaKviz::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun grupaDao(): GrupaDao
    abstract fun predmetDao(): PredmetDao
    abstract fun kvizDao(): KvizDao
    abstract fun pitanjeDao(): PitanjeDao
    abstract fun odgovorDao(): OdgovorDao
    abstract fun accountDao(): AccountDao
    abstract fun kvizTakenDao(): KvizTakenDao
    abstract fun grupaKvizDao() : GrupaKvizDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun setInstance(appdb:AppDatabase):Unit{
            INSTANCE=appdb
        }

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDB(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "RMA21DB"
                ).build()
    }
}