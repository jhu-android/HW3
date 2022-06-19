package hwang.joy.hw3.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Update
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ContactDAO {

    @Insert
    abstract fun insert(vararg contacts: ContactEntity)
    @Insert
    abstract fun insert(vararg addresses: AddressEntity)

    @Update
    abstract fun update(vararg contacts: ContactEntity)
    @Update
    abstract fun update(vararg addresses: AddressEntity)

    @Delete
    abstract fun delete(vararg contacts: ContactEntity)
    @Delete
    abstract fun delete(vararg addresses: AddressEntity)

    @Query("SELECT * FROM ContactEntity")
    abstract fun getContactsFlow(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM AddressEntity")
    abstract fun getAddressesFlow(): Flow<List<AddressEntity>>

    @Transaction
    @Query("SELECT * FROM ContactEntity WHERE id = :id")
    abstract fun getContact(id: String): ContactEntity

    @Transaction
    @Query("SELECT * FROM AddressEntity WHERE id = :id")
    abstract fun getAddress(id: String): AddressEntity

    @Transaction
    @Query("SELECT * FROM ContactEntity WHERE id = :id")
    abstract fun getContactWithAddresses(id: String): ContactWithAddresses

    @Query("DELETE FROM ContactEntity")
    abstract fun clearContacts()

    @Query("DELETE FROM AddressEntity")
    abstract fun clearAddresses()

    @Transaction
    open fun resetDatabase() {
        clearContacts()
        clearAddresses()

        insert(
            ContactEntity("c1", "Muffin", "Man", "555-123-4567", "555-223-5000", "555-222-1234", "mman@gmail.com"),
            ContactEntity("c2", "Red", "Ridinghood", "300-555-2444", "300-555-3333", "244-555-2222", "littlerr@gmail.com"),
            ContactEntity("c3", "Rapunzel", "Golden", "422-555-6788", "422-555-6788", "422-555-8755", "lonelygirl@gmail.com")
        )

        insert(
            AddressEntity("a1", "Home", "13 Drury Lane", "London", "MN", "12345", "c1"),
            AddressEntity("a2", "Work", "1 High Street", "London", "MN", "12346", "c1"),
            AddressEntity("a3", "Home", "8 Forest Lane", "Greenwood", "Maine", "03345", "c2"),
            AddressEntity("a4", "Work", "22 Textile Way", "Portland", "Maine", "02344", "c2"),
            AddressEntity("a5", "Home", "1 Tower Road Suite 99A", "Gardenville", "Oregon", "58749", "c3"),
            AddressEntity("a6", "Work", "2 Tower Road Suite 99B", "Gardenville", "Oregon", "58749", "c3")
        )
    }
}
