package hwang.joy.hw3.components

import android.util.Log
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
    contactId: String?,
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
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            onValueChange = {
                addressFormData.type = it
                if (address != null) {
                    scope.launch {
                        onAddressChange(address.copy(type = it))
                    }
                } else {
                    scope.launch {
                        onAddressAdd(createNewAddress(contactId ?: "").copy(type = it)) // TODO this should never be null
                    }
                }
            },
        )
        OutlinedTextField(
            value = addressFormData.street ?: "",
            label = { Text(text = stringResource(id = R.string.label_street)) },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            onValueChange = {
                addressFormData.street = it
                if(address != null) {
                    scope.launch {
                        onAddressChange(address.copy(street = it))
                    }
                } else {
                    scope.launch {
                        onAddressAdd(createNewAddress(contactId ?: "").copy(street = it)) // TODO this should never be null
                    }
                }
            },
        )

    }


}

