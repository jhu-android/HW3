package hwang.joy.hw3

import android.app.Application
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.AndroidViewModel
import hwang.joy.hw3.components.*
import hwang.joy.hw3.data.AddressEntity
import hwang.joy.hw3.data.ContactEntity
import hwang.joy.hw3.data.ContactWithAddresses
import kotlinx.coroutines.flow.map

sealed class Screen(
    @StringRes val titleId: Int,
)
object ContactListScreen: Screen(
    titleId = R.string.screen_title_contacts,
)

object ContactDisplayScreen: Screen(
    titleId = R.string.screen_title_contacts,
)

object ContactEditScreen: Screen(
    titleId = R.string.screen_title_contacts, //TODO
)

object AboutScreen: Screen(
    titleId = R.string.screen_title_about,
)

object AddressEditScreen: Screen(
    titleId = R.string.screen_title_address_edit,
)

// object other screens

val screenTargets = immutableListOf( // add other screens
    ContactListScreen,
    ContactDisplayScreen,
    ContactEditScreen,
    AboutScreen,
    AddressEditScreen
)

class ContactViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ContactRepository = ContactDatabaseRepository(application)

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

    fun selectListScreen(screen: Screen) {
        screenStack = listOf(screen)
    }


    val contactsFlow = repository.contactsFlow.map {
        ImmutableList(it)
    }
    val addressesFlow = repository.addressesFlow.map {
        ImmutableList(it)
    }

    suspend fun select(contact: ContactEntity) {
        this.contact = repository.getEntity(contact)
        Log.d("jhw&", "selecting contact! ${this.contact}")
    }

    suspend fun select(address: AddressEntity) {
        this.address = repository.getEntity(address)
        Log.d("jhw&", "selecting address! ${this.address}")
    }

    fun unselectContact() {
        this.contact = null
    }

    fun unselectAddress() {
        this.address = null
    }

    private fun ImmutableSet<String>.toggleSelectionId(id: String): ImmutableSet<String> = // ???
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

    suspend fun insertContact(contact: ContactEntity) {
        repository.insert(contact)
        select(contact)
    }

    suspend fun updateAddress(address: AddressEntity) {
        repository.update(address)
        select(address)
        this.contact?.contact?.let { select(it) }
    }

    suspend fun insertAddress(address: AddressEntity) {
        repository.insert(address)
        select(address)
        this.contact?.contact?.let { select(it) }

    }

//    suspend fun deleteSelectedContacts() {
//        repository. TODO
//    }

//    suspend fun select(address: AddressEntity) {
//        this.address = repository.getEntity(address)
//    }

//    suspend fun deleteAddresses(addresses: AddressEntity) {
//        repository.delete(addresses)
//    }
//    suspend fun deleteContacts(contacts: ContactEntity) {
//        repository.delete(contacts)
//    }
//


//
//    suspend fun updateAddresses(addresses: AddressEntity) {
//        repository.update(addresses)
//    }


    suspend fun resetDatabase() = repository.resetDatabase()


}