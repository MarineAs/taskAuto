package com.example.taskauto.model.room

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [ModelEntity::class, CarEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ModelsConverter::class)
abstract class CarDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao?
    abstract fun modelDao(): ModelDao?

    companion object {
        private var instance: CarDatabase? = null

        fun getInstance(context: Context): CarDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarDatabase::class.java,
                    "car"
                ).fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }
            return instance!!
        }

        private var roomCallback: Callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateBdAsyncTask(
                    instance
                ).execute()
            }
        }

        class PopulateBdAsyncTask(db: CarDatabase?) : AsyncTask<Void, Void, Void>() {
            private val carDao: CarDao = db?.carDao()!!
            private val modelDao: ModelDao = db?.modelDao()!!

            override fun doInBackground(vararg params: Void?): Void? {
                carDao.insert(
                    CarEntity(
                        null,
                        "BMW", "E60",
                        1990,
                        2500
                    )
                )
                carDao.insert(
                    CarEntity(
                        null, "Tesla", "S",
                        1995,
                        1000
                    )
                )
                carDao.insert(
                    CarEntity(
                        null, "Mercedes", "Maybach",
                        2000,
                        100000
                    )
                )
                carDao.insert(
                    CarEntity(
                        null, "Mercedes", "S-560",
                        1990,
                        50000
                    )
                )
                carDao.insert(
                    CarEntity(
                        null, "Mercedes", "AMG",
                        1990,
                        50000
                    )
                )
                modelDao.insertAll(
                    ModelEntity(1, "BMW", arrayListOf("E60", "X3", "X5", "i4")),
                    ModelEntity(2, "Tesla", arrayListOf("X", "S", "3", "P100D")),
                    ModelEntity(
                        3, "Mercedes",
                        arrayListOf("AMG", "CLA", "Maybach", "S-560", "S-Class", "G", "E")
                    ),
                    ModelEntity(
                        4, "Toyota",
                        arrayListOf(
                            "Yaris",
                            "Fortuner",
                            "Corolla Altis",
                            "Camry",
                            "Prius",
                            "Etios Cross",
                            "Vellfire",
                            "Corolla 2020"
                        )
                    ),
                    ModelEntity(
                        5, "Ford",
                        arrayListOf(
                            " EcoSport",
                            "Endeavour",
                            "Mustang",
                            "Figo",
                            "Aspire",
                            " EcoSport 2020"
                        )
                    ),
                    ModelEntity(7, "yy", arrayListOf("X", "S", "3", "P100D")),
                    ModelEntity(
                        8, "iii",
                        arrayListOf("AMG", "CLA", "Maybach", "S-560", "S-Class", "G", "E")
                    ),
                    ModelEntity(
                        9, "yyy",
                        arrayListOf(
                            "Yaris",
                            "Fortuner",
                            "Corolla Altis",
                            "Camry",
                            "Prius",
                            "Etios Cross",
                            "Vellfire",
                            "Corolla 2020"
                        )
                    ),
                    ModelEntity(
                        10, "OiO",
                        arrayListOf(
                            " EcoSport",
                            "Endeavour",
                            "Mustang",
                            "Figo",
                            "Aspire",
                            " EcoSport 2020"
                        )
                    ),
                    ModelEntity(
                        11, "Tiko",
                        arrayListOf(
                            "Yaris",
                            "Fortuner",
                            "Corolla Altis",
                            "Camry",
                            "Prius",
                            "Etios Cross",
                            "Vellfire",
                            "Corolla 2020"
                        )
                    ),
                    ModelEntity(
                        12, "KKK",
                        arrayListOf(
                            " EcoSport",
                            "Endeavour",
                            "Mustang",
                            "Figo",
                            "Aspire",
                            " EcoSport 2020"
                        )
                    ),
                    ModelEntity(14, "LLL", arrayListOf("X", "S", "3", "P100D")),
                    ModelEntity(
                        15, "FFF",
                        arrayListOf("AMG", "CLA", "Maybach", "S-560", "S-Class", "G", "E")
                    ),
                    ModelEntity(
                        16, "OOO",
                        arrayListOf(
                            "Yaris",
                            "Fortuner",
                            "Corolla Altis",
                            "Camry",
                            "Prius",
                            "Etios Cross",
                            "Vellfire",
                            "Corolla 2020"
                        )
                    ),
                    ModelEntity(
                        17, "KU",
                        arrayListOf(
                            " EcoSport",
                            "Endeavour",
                            "Mustang",
                            "Figo",
                            "Aspire",
                            " EcoSport 2020"
                        )
                    )
                )

                return null
            }
        }
    }
}

