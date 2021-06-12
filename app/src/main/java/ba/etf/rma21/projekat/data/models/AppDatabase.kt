package ba.etf.rma21.projekat.data.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Grupa::class,Predmet::class,Kviz::class, Pitanje::class, Odgovor::class, Account::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun grupaDao(): GrupaDao
    abstract fun predmetDao(): PredmetDao
    abstract fun kvizDao(): KvizDao
    abstract fun pitanjeDao(): PitanjeDao
    abstract fun odgovorDao(): OdgovorDao
    abstract fun accountDao(): AccountDao

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