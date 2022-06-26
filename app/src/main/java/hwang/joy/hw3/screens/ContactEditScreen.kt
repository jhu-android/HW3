package hwang.joy.hw3.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import hwang.joy.hw3.R
import hwang.joy.hw3.components.ContactEditBody
import hwang.joy.hw3.data.ContactWithAddresses
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ContactEditScreen(
    scope: CoroutineScope,
    contact: ContactWithAddresses?,
    onClickAbout: () -> Unit,
//    currentScreen: Screen,
//    onScreenSelect: (Screen) -> Unit,
//    onContactEditAddress: suspend (ContactWithAddresses) -> Unit,
) {

    Scaffold(
        topBar = {
            if (contact == null) {
                TopAppBar(
                    title = { Text(text = "New Contact") },
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
            } else {
                TopAppBar(
                    title = { Text(text = "${contact?.contact?.firstName} ${contact?.contact?.lastName}") },
                )
            }

        },
        bottomBar = {
            BottomAppBar(
                content = { Text(text = "Bottom App Bar") }
            )
        }
    ) {
        ContactEditBody(
            scope = scope,
            contact = contact,
        )
    }

}