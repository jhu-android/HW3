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
import hwang.joy.hw3.components.ContactEditBody
import hwang.joy.hw3.data.AddressEntity
import hwang.joy.hw3.data.ContactEntity
import hwang.joy.hw3.data.ContactWithAddresses
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ContactEditScreen(
    scope: CoroutineScope,
    contact: ContactWithAddresses?,
    onClickAbout: () -> Unit,
    onContactAdd: suspend(ContactEntity) -> Unit,
    onContactChange: suspend (ContactEntity) -> Unit,
    onAddressDelete: suspend (String) -> Unit,
    onAddAddressClick: suspend (String) -> Unit,
    onAddAddressClickNewContact: suspend (ContactEntity) -> Unit,
    onAddressEdit: suspend (AddressEntity) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (contact == null) {
                        Text(text = stringResource(id = R.string.screen_title_new_contact))
                    } else {
                        Text(text = "${contact.contact.firstName} ${contact.contact.lastName}")
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
        ContactEditBody(
            scope = scope,
            contactWithAddresses = contact,
            onContactChange = onContactChange,
            onContactAdd = onContactAdd,
            onAddressDelete = onAddressDelete,
            onAddAddressClick = onAddAddressClick,
            onAddAddressClickNewContact = onAddAddressClickNewContact,
            onAddressEdit = onAddressEdit,
        )

    }

}