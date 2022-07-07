// Attribution: Scott Stanfield, Android Summer 2022
// Source code: https://gitlab.com/605-686/android-summer-2022/-/tree/main/Movies3

package hwang.joy.hw3.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Info
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
                        Text(text = stringResource(id = R.string.screen_title_address_add))
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
                            imageVector = Icons.Sharp.Info,
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