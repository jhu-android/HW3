package hwang.joy.hw3.components

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hwang.joy.hw3.data.AddressEntity
import hwang.joy.hw3.data.ContactEntity

// Immutables logic
@Immutable
data class ImmutableList<T>(val list: List<T>): List<T> by list
fun <T> immutableListOf(vararg items: T) =
    ImmutableList(listOf(*items))
fun <T> emptyImmutableList() = ImmutableList<T>(emptyList())

@Immutable
data class ImmutableSet<T>(val set: Set<T>): Set<T> by set {
    operator fun plus(item: T) = ImmutableSet(set + item)
    operator fun minus(item: T) = ImmutableSet(set - item)
}
fun <T> emptyImmutableSet() = ImmutableSet<T>(emptySet())


// List logic
// Interface - List
sealed interface ListSelection {
    operator fun contains(id: String): Boolean
}
object NoListSelection: ListSelection {
    override fun contains(id: String) = false
}
data class SingleListSelection(val id: String): ListSelection {
    override fun contains(id: String ) = (this.id == id)
}
data class MultiListSelection(val ids: List<String>): ListSelection {
    override fun contains(id: String) = (id in ids)
}

// Form data logic
class ContactFormData(incomingContact: ContactEntity? = ContactEntity("", "", "", "", "", "", "")) {
    var firstName = incomingContact?.firstName
    var lastName = incomingContact?.lastName
    var homePhone = incomingContact?.homePhone
    var workPhone = incomingContact?.workPhone
    var mobilePhone = incomingContact?.mobilePhone
    var emailAddress = incomingContact?.emailAddress
}

fun createNewContact(): ContactEntity {
    return ContactEntity(
        firstName = "",
        lastName = "",
        homePhone = "",
        workPhone = "",
        mobilePhone = "",
        emailAddress = "",
    )
}

class AddressFormData(incomingAddress: AddressEntity? = AddressEntity("","","","","","","")) {
    var type = incomingAddress?.type
    var street = incomingAddress?.street
    var city = incomingAddress?.city
    var state = incomingAddress?.state
    var zip = incomingAddress?.zip
}

fun createNewAddress(contactId: String): AddressEntity {
    return AddressEntity(
        type = "",
        street = "",
        city = "",
        state = "",
        zip = "",
        contactId = contactId,
    )
}

// Composable - Common Text Field
@Composable
fun TextField(
    @StringRes labelId: Int,
    @StringRes placeholderId: Int,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) =
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = stringResource(id = labelId))
        },
        placeholder = {
            Text(text = stringResource(id = placeholderId))
        },
        modifier = modifier.fillMaxWidth().padding(8.dp)
    )

// Composable - Label
// Composable - Display
// Composable - Common Text


// TO DELETE?
@Immutable
data class TopAction(
    val icon: ImageVector,
    @StringRes val contentDescriptionId: Int,
    val onClick: () -> Unit,
)

@Composable
fun SimpleButton(
    text: String,
    onClick: () -> Unit,
) = Button(onClick = onClick) { Text(text = text) }


