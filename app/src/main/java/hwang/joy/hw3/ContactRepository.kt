// Attribution: Scott Stanfield, Android Summer 2022
// Source code: https://gitlab.com/605-686/android-summer-2022/-/tree/main/Movies3

package hwang.joy.hw3

import hwang.joy.hw3.data.AddressEntity
import hwang.joy.hw3.data.ContactEntity
import hwang.joy.hw3.data.ContactWithAddresses
import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    val contactsFlow: Flow<List<ContactEntity>>
    val addressesFlow: Flow<List<AddressEntity>>

    suspend fun getEntity(contact: ContactEntity): ContactWithAddresses
    suspend fun getEntity(address: AddressEntity): AddressEntity
    suspend fun insert(vararg contacts: ContactEntity)
    suspend fun insert(vararg addresses: AddressEntity)
    suspend fun update(vararg contacts: ContactEntity)
    suspend fun update(vararg addresses: AddressEntity)
    suspend fun delete(vararg contacts: ContactEntity)
    suspend fun delete(vararg addresses: AddressEntity)

    suspend fun deleteContactsByIds(ids: List<String>)
    suspend fun deleteAddressById(id: String)

    suspend fun resetDatabase()
}