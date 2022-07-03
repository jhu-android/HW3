package hwang.joy.hw3.screens

import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import hwang.joy.hw3.ContactEditScreen
import hwang.joy.hw3.R
import hwang.joy.hw3.components.ContactAddBody
import hwang.joy.hw3.components.ContactEditBody
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
//    currentScreen: Screen,
//    onScreenSelect: (Screen) -> Unit,
//    onContactEditAddress: suspend (ContactWithAddresses) -> Unit,
) {

    Log.d("jhw!", "Contact Edit Screen: $contact")
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (contact == null) {
                        Text(text = "New Contact")
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
                            imageVector = Icons.Default.Info,
                            contentDescription = stringResource(id = R.string.icon_description_about),
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                content = { Text(text = "Bottom App Bar") }
            )
        }
    ) {
        ContactEditBody(
            scope = scope,
            contactWithAddresses = contact,
            onContactChange = onContactChange,
            onContactAdd = onContactAdd,
        )

    }

}