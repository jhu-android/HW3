package hwang.joy.hw3

import android.app.Application
import androidx.room.Room
import hwang.joy.hw3.data.AddressEntity
import hwang.joy.hw3.data.ContactDatabase
import hwang.joy.hw3.data.ContactEntity
import hwang.joy.hw3.data.ContactWithAddresses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ContactDatabaseRepository(private val application: Application) : ContactRepository {

    private val db =
        Room.databaseBuilder(
            application,
            ContactDatabase::class.java,
            "CONTACTS"
        ).build()

    override val contactsFlow: Flow<List<ContactEntity>> =
        db.dao().getContactsFlow()
    override val addressesFlow: Flow<List<AddressEntity>> =
        db.dao().getAddressesFlow()

    override suspend fun getEntity(address: AddressEntity): AddressEntity =
        withContext(Dispatchers.IO) {
            db.dao().getAddress(address.id)
        }
    override suspend fun getEntity(contact: ContactEntity): ContactWithAddresses =
        withContext(Dispatchers.IO) {
            db.dao().getContactWithAddresses(contact.id)
        }

    override suspend fun insert(vararg contacts: ContactEntity): Unit =
        withContext(Dispatchers.IO) {
            db.dao().insert(*contacts)
        }
    override suspend fun insert(vararg addresses: AddressEntity): Unit =
        withContext(Dispatchers.IO) {
            db.dao().insert(*addresses)
        }

    override suspend fun update(vararg contacts: ContactEntity): Unit =
        withContext(Dispatchers.IO) {
            db.dao().update(*contacts)
        }
    override suspend fun update(vararg addresses: AddressEntity): Unit =
        withContext(Dispatchers.IO) {
            db.dao().update(*addresses)
        }

    override suspend fun delete(vararg contacts: ContactEntity): Unit =
        withContext(Dispatchers.IO) {
            db.dao().delete(*contacts)
        }
    override suspend fun delete(vararg addresses: AddressEntity): Unit =
        withContext(Dispatchers.IO) {
            db.dao().delete(*addresses)
        }

    override suspend fun resetDatabase() =
        withContext(Dispatchers.IO) {
            db.dao().resetDatabase()
        }
}