package hwang.joy.hw3

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import hwang.joy.hw3.data.AddressEntity
import hwang.joy.hw3.data.ContactEntity
import hwang.joy.hw3.data.ContactWithAddresses

sealed interface Screen
data class ContactListScreen(val selection: ListSelection): Screen
data class ContactDisplayScreen(val selection: ListSelection): Screen
data class ContactEditScreen(val selection: ListSelection): Screen
data class AddressEditScreen(val selection: ListSelection): Screen
//object AboutScreen: Screen

class ContactViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ContactRepository = ContactDatabaseRepository(application)

    var contact by mutableStateOf<ContactWithAddresses?>(null)
        private set
    var address by mutableStateOf<AddressEntity?>(null)
        private set
    var screen by mutableStateOf<Screen?>(ContactListScreen(NoListSelection))
        private set

    private var screenStack = listOf<Screen>(ContactListScreen(NoListSelection))
        set(value) {
            field = value
            screen = value.lastOrNull()
        }

    fun push(screen: Screen) { screenStack = screenStack + screen }
    fun pop() { screenStack = screenStack.dropLast(1)}
    // TODO peekOneBack and replaceTop may be needed for stack manipulation

    val contactsFlow = repository.contactsFlow
    val addressesFlow = repository.addressesFlow

    suspend fun select(contact: ContactEntity) {
        this.contact = repository.getEntity(contact)
    }
    suspend fun select(address: AddressEntity) {
        this.address = repository.getEntity(address)
    }

    suspend fun deleteAddresses(addresses: AddressEntity) {
        repository.delete(addresses)
    }
    suspend fun deleteContacts(contacts: ContactEntity) {
        repository.delete(contacts)
    }

    suspend fun insertAddresses(addresses: AddressEntity) {
        repository.insert(addresses)
    }
    suspend fun insertContacts(contacts: ContactEntity) {
        repository.insert(contacts)
    }

    suspend fun updateAddresses(addresses: AddressEntity) {
        repository.update(addresses)
    }
    suspend fun updateContacts(contacts: ContactEntity) {
        repository.update(contacts)
    }

    suspend fun resetDatabase() = repository.resetDatabase()


}