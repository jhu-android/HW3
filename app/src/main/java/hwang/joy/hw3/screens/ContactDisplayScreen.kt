package hwang.joy.hw3.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import hwang.joy.hw3.R
import hwang.joy.hw3.Screen
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
                            imageVector = Icons.Default.Info,
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
                            tint = Color.Yellow,
                            imageVector = Icons.Default.Edit ,
                            contentDescription = stringResource(id = R.string.icon_description_tap_to_edit),
                        )

                    }

                }
            )

        },
    ) {
        ContactDisplayBody(
            scope = scope,
            contact = contact,
        )
    }


}