// Attribution: Scott Stanfield, Android Summer 2022
// Source code: https://gitlab.com/605-686/android-summer-2022/-/tree/main/Movies3

package hwang.joy.hw3.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.material.icons.sharp.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import hwang.joy.hw3.R
import hwang.joy.hw3.components.ContactDisplayBody
import hwang.joy.hw3.data.ContactWithAddresses
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ContactDisplayScreen(
    scope: CoroutineScope,
    contact: ContactWithAddresses?,
    onEdit: () -> Unit,
    onClickAbout: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "${contact?.contact?.firstName} ${contact?.contact?.lastName}") },
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

                    IconButton(
                        onClick = {
                            scope.launch(Dispatchers.IO) {
                                onEdit()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.Edit,
                            contentDescription = stringResource(id = R.string.icon_description_tap_to_edit),
                        )

                    }

                }
            )

        },
    ) {
        ContactDisplayBody(
            contact = contact,
        )
    }


}