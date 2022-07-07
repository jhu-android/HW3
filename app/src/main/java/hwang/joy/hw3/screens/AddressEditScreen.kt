package hwang.joy.hw3.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import hwang.joy.hw3.R
import hwang.joy.hw3.components.AddressEditBody
import hwang.joy.hw3.data.AddressEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AddressEditScreen(
    scope: CoroutineScope,
    address: AddressEntity?,
    contactId: String?,
    onClickAbout: () -> Unit,
    onAddressAdd: suspend(AddressEntity) -> Unit,
    onAddressChange: suspend (AddressEntity) -> Unit,
) {
    requireNotNull(contactId)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (address == null) {
                        Text(text = "New Address")
                    } else {
                        Text(text = "Edit ${address.type} Address")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            scope.launch(Dispatchers.IO) {
                                onClickAbout()
                            }
                        }
                    ) {
                        Icon(
                            tint = Color.LightGray,
                            imageVector = Icons.Default.Info,
                            contentDescription = stringResource(id = R.string.icon_description_about),
                        )
                    }
                }
            )
        },
    ) {
        AddressEditBody(
            scope = scope,
            address = address,
            contactId = contactId,
            onAddressAdd = onAddressAdd,
            onAddressChange = onAddressChange,
        )
    }
}