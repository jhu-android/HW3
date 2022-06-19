package hwang.joy.hw3.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hwang.joy.hw3.CommonList
import hwang.joy.hw3.ListSelection
import hwang.joy.hw3.data.ContactEntity

// TEMP
@Composable
fun SimpleButton(
    text: String,
    onClick: () -> Unit,
) = Button(onClick = onClick) { Text(text = text) }

@Composable
fun ContactList(
    contacts: List<ContactEntity>,
    selection: ListSelection,
    modifier: Modifier,
    onReset: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar( // need custom wrapper around top app bar
                content = {
                    SimpleButton(text = "Reset DB") {
                        onReset()
                    }
                }
            )
        },
        content = {
            Column {
                Text(text = "hello im a contact list", style = MaterialTheme.typography.h4)

                CommonList(
                    modifier = modifier,
                    items = contacts,
                    getId = { id },
                    getText = { firstName },
                    selection = selection
                )
            }
        }
    )
}