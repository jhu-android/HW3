// Attribution: Scott Stanfield, Android Summer 2022
// Source code: https://gitlab.com/605-686/android-summer-2022/-/tree/main/Movies3

package hwang.joy.hw3

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import hwang.joy.hw3.components.*
import hwang.joy.hw3.data.AddressEntity
import hwang.joy.hw3.data.ContactEntity
import hwang.joy.hw3.data.ContactWithAddresses
import kotlinx.coroutines.flow.map

sealed class Screen
object ContactListScreen: Screen()
object ContactDisplayScreen: Screen()
object ContactEditScreen: Screen()
object AboutScreen: Screen()
object AddressEditScreen: Screen()

class ContactViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ContactRepository = ContactDatabaseRepository(application)
    val contactsFlow = repository.contactsFlow.map {
        ImmutableList(it)
    }

    var contact by mutableStateOf<ContactWithAddresses?>(null)
        private set
    var address by mutableStateOf<AddressEntity?>(null)
        private set
    var selectedContactIds by mutableStateOf<ImmutableSet<String>>(emptyImmutableSet())

    var screen by mutableStateOf<Screen?>(ContactListScreen)
        private set
    private var screenStack = listOf<Screen>(ContactListScreen)
        set(value) {
            field = value
            screen = value.lastOrNull()
        }
    fun push(screen: Screen) { screenStack = screenStack + screen }
    fun pop() { screenStack = screenStack.dropLast(1)}


    suspend fun select(contact: ContactEntity) {
        this.contact = repository.getEntity(contact)
    }
    suspend fun select(address: AddressEntity) {
        this.address = repository.getEntity(address)
    }

    fun unselectContact() {
        this.contact = null
    }
    fun unselectAddress() {
        this.address = null
    }

    private fun ImmutableSet<String>.toggleSelectionId(id: String): ImmutableSet<String> =
        if (id in this)
            this - id
        else
            this + id

    fun toggleSelectedContactId(id: String) {
        selectedContactIds = selectedContactIds.toggleSelectionId(id)
    }
    fun clearSelectedContactIds() {
        selectedContactIds = emptyImmutableSet()
    }

    suspend fun deleteSelectedContacts() {
        repository.deleteContactsByIds(selectedContactIds.toList())
        clearSelectedContactIds()
    }
    suspend fun deleteSelectedAddress(id: String) {
        repository.deleteAddressById(id)
        this.contact?.contact?.let { select(it) }
    }

    suspend fun updateContact(contact: ContactEntity) {
        repository.update(contact)
        select(contact)
    }
    suspend fun updateAddress(address: AddressEntity) {
        repository.update(address)
        select(address)
        this.contact?.contact?.let { select(it) }
    }

    suspend fun insertContact(contact: ContactEntity) {
        repository.insert(contact)
        select(contact)
    }
    suspend fun insertAddress(address: AddressEntity) {
        repository.insert(address)
        select(address)
        this.contact?.contact?.let { select(it) }
    }

    suspend fun resetDatabase() = repository.resetDatabase()

}