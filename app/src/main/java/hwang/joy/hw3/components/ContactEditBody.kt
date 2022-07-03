package hwang.joy.hw3.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hwang.joy.hw3.data.ContactWithAddresses
import hwang.joy.hw3.R
import hwang.joy.hw3.data.ContactEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ContactEditBody(
    scope: CoroutineScope,
    contactWithAddresses: ContactWithAddresses?,
    onContactChange: suspend (ContactEntity) -> Unit,
    onContactAdd: suspend (ContactEntity) -> Unit,
    ) {

    Log.d("jhw!", "Contact Edit Body: $contactWithAddresses")

    var formData by remember { mutableStateOf(FormData(contactWithAddresses?.contact)) }

    Column {
        OutlinedTextField(
            value = formData.firstName ?: "" ,
            label = { Text(text = stringResource(id = R.string.label_firstName)) },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            onValueChange = {
                formData.firstName = it
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

    }
}

class FormData(incomingContact: ContactEntity? = ContactEntity("","","","","","", "")) {
    var firstName = incomingContact?.firstName
    var lastName = incomingContact?.lastName
    var homePhone = incomingContact?.homePhone
    var workPhone = incomingContact?.workPhone
    var mobilePhone = incomingContact?.mobilePhone
    var emailAddress = incomingContact?.emailAddress
}


