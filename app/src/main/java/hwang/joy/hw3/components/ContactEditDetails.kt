// Attribution: Scott Stanfield, Android Summer 2022
// Source code: https://gitlab.com/605-686/android-summer-2022/-/tree/main/Movies3

package hwang.joy.hw3.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hwang.joy.hw3.R
import hwang.joy.hw3.data.ContactEntity
import hwang.joy.hw3.data.ContactWithAddresses
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ContactEditDetails (
    scope: CoroutineScope,
    contactWithAddresses: ContactWithAddresses?,
    onContactChange: suspend (ContactEntity) -> Unit,
    onContactAdd: suspend (ContactEntity) -> Unit,
){
    var contactFormData by remember { mutableStateOf(ContactFormData(contactWithAddresses?.contact)) }

    OutlinedTextField(
        value = contactFormData.firstName ?: "" ,
        label = { Text(text = stringResource(id = R.string.label_firstName)) },
        modifier = Modifier.fillMaxWidth().padding(8.dp, 4.dp, 8.dp, 4.dp),
        onValueChange = {
            contactFormData.firstName = it
            if (contactWithAddresses != null) {
                scope.launch {
                    onContactChange(contactWithAddresses.contact.copy(firstName = it))
                }
            } else {
                scope.launch {
                    onContactAdd(createNewContact().copy(firstName = it))
                }
            }
        }
    )
    OutlinedTextField(
        value = contactFormData.lastName ?: "" ,
        label = { Text(text = stringResource(id = R.string.label_lastName)) },
        modifier = Modifier.fillMaxWidth().padding(8.dp, 4.dp, 8.dp, 4.dp),
        onValueChange = {
            contactFormData.lastName = it
            if (contactWithAddresses != null) {
                scope.launch {
                    onContactChange(contactWithAddresses.contact.copy(lastName = it))
                }
            } else {
                scope.launch {
                    onContactAdd(createNewContact().copy(lastName = it))
                }
            }
        }
    )
    OutlinedTextField(
        value = contactFormData.homePhone ?: "" ,
        label = { Text(text = stringResource(id = R.string.label_homePhone)) },
        modifier = Modifier.fillMaxWidth().padding(8.dp, 4.dp, 8.dp, 4.dp),
        onValueChange = {
            contactFormData.homePhone = it
            if (contactWithAddresses != null) {
                scope.launch {
                    onContactChange(contactWithAddresses.contact.copy(homePhone = it))
                }
            } else {
                scope.launch {
                    onContactAdd(createNewContact().copy(homePhone = it))
                }
            }
        }
    )
    OutlinedTextField(
        value = contactFormData.workPhone ?: "" ,
        label = { Text(text = stringResource(id = R.string.label_workPhone)) },
        modifier = Modifier.fillMaxWidth().padding(8.dp, 4.dp, 8.dp, 4.dp),
        onValueChange = {
            contactFormData.workPhone = it
            if (contactWithAddresses != null) {
                scope.launch {
                    onContactChange(contactWithAddresses.contact.copy(workPhone = it))
                }
            } else {
                scope.launch {
                    onContactAdd(createNewContact().copy(workPhone = it))
                }
            }
        }
    )
    OutlinedTextField(
        value = contactFormData.mobilePhone ?: "" ,
        label = { Text(text = stringResource(id = R.string.label_mobilePhone)) },
        modifier = Modifier.fillMaxWidth().padding(8.dp, 4.dp, 8.dp, 4.dp),
        onValueChange = {
            contactFormData.mobilePhone = it
            if (contactWithAddresses != null) {
                scope.launch {
                    onContactChange(contactWithAddresses.contact.copy(mobilePhone = it))
                }
            } else {
                scope.launch {
                    onContactAdd(createNewContact().copy(mobilePhone = it))
                }
            }
        }
    )
    OutlinedTextField(
        value = contactFormData.emailAddress ?: "" ,
        label = { Text(text = stringResource(id = R.string.label_emailAddress)) },
        modifier = Modifier.fillMaxWidth().padding(8.dp, 4.dp, 8.dp, 4.dp),
        onValueChange = {
            contactFormData.emailAddress = it
            if (contactWithAddresses != null) {
                scope.launch {
                    onContactChange(contactWithAddresses.contact.copy(emailAddress = it))
                }
            } else {
                scope.launch {
                    onContactAdd(createNewContact().copy(emailAddress = it))
                }
            }
        }
    )

}

