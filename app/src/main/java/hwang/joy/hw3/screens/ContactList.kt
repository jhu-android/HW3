package hwang.joy.hw3.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button // TEMP
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
) { Column {
    Text(text = "hello im a contact list", style = MaterialTheme.typography.h4)

    SimpleButton(text = "Reset DB") {
        onReset()
    }

    CommonList(
        modifier = modifier,
        items = contacts,
        getId = { id },
        getText = { firstName },
        selection = selection
    )
}

}