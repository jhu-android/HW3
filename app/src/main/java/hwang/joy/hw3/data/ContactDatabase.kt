package hwang.joy.hw3.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        ContactEntity::class,
        AddressEntity::class,
    ],
    exportSchema = false
)

abstract class ContactDatabase: RoomDatabase() {
    abstract fun dao(): ContactDAO
}