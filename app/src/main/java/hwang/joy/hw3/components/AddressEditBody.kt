// Attribution: Scott Stanfield, Android Summer 2022
// Source code: https://gitlab.com/605-686/android-summer-2022/-/tree/main/Movies3

package hwang.joy.hw3.components

import hwang.joy.hw3.data.AddressEntity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hwang.joy.hw3.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun AddressEditBody(
    scope: CoroutineScope,
    address: AddressEntity?,
    contactId: String,
    onAddressAdd: suspend(AddressEntity) -> Unit,
    onAddressChange: suspend (AddressEntity) -> Unit,
) {
    var addressFormData by remember { mutableStateOf(AddressFormData(address)) }
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        OutlinedTextField(
            value = addressFormData.type ?: "",
            label = { Text(text = stringResource(id = R.string.label_addressType)) },
            modifier = Modifier.fillMaxWidth().padding(8.dp, 4.dp, 8.dp, 4.dp),
            onValueChange = {
                addressFormData.type = it
                if (address != null) {
                    scope.launch {
                        onAddressChange(address.copy(type = it))
                    }
                } else {
                    scope.launch {
                        onAddressAdd(createNewAddress(contactId).copy(type = it))
                    }
                }
            },
        )
        OutlinedTextField(
            value = addressFormData.street ?: "",
            label = { Text(text = stringResource(id = R.string.label_street)) },
            modifier = Modifier.fillMaxWidth().padding(8.dp, 4.dp, 8.dp, 4.dp),
            onValueChange = {
                addressFormData.street = it
                if(address != null) {
                    scope.launch {
                        onAddressChange(address.copy(street = it))
                    }
                } else {
                    scope.launch {
                        onAddressAdd(createNewAddress(contactId).copy(street = it))
                    }
                }
            },
        )
        OutlinedTextField(
            value = addressFormData.city ?: "",
            label = { Text(text = stringResource(id = R.string.label_city)) },
            modifier = Modifier.fillMaxWidth().padding(8.dp, 4.dp, 8.dp, 4.dp),
            onValueChange = {
                addressFormData.city = it
                if(address != null) {
                    scope.launch {
                        onAddressChange(address.copy(city = it))
                    }
                } else {
                    scope.launch {
                        onAddressAdd(createNewAddress(contactId).copy(city = it))
                    }
                }
            },
        )
        OutlinedTextField(
            value = addressFormData.state ?: "",
            label = { Text(text = stringResource(id = R.string.label_state)) },
            modifier = Modifier.fillMaxWidth().padding(8.dp, 4.dp, 8.dp, 4.dp),
            onValueChange = {
                addressFormData.state = it
                if(address != null) {
                    scope.launch {
                        onAddressChange(address.copy(state = it))
                    }
                } else {
                    scope.launch {
                        onAddressAdd(createNewAddress(contactId).copy(state = it))
                    }
                }
            },
        )
        OutlinedTextField(
            value = addressFormData.zip ?: "",
            label = { Text(text = stringResource(id = R.string.label_zip)) },
            modifier = Modifier.fillMaxWidth().padding(8.dp, 4.dp, 8.dp, 4.dp),
            onValueChange = {
                addressFormData.zip = it
                if(address != null) {
                    scope.launch {
                        onAddressChange(address.copy(zip = it))
                    }
                } else {
                    scope.launch {
                        onAddressAdd(createNewAddress(contactId).copy(zip = it))
                    }
                }
            },
        )
    }
}

