// Attribution: Scott Stanfield, Android Summer 2022
// Source code: https://gitlab.com/605-686/android-summer-2022/-/tree/main/Movies3

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