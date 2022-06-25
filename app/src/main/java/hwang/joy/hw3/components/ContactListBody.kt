package hwang.joy.hw3.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text


@Composable
fun <T> ContactListBody(
    modifier: Modifier,
    contacts: ImmutableList<T>,
    getKey: (T) -> String,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = contacts,
            key = getKey,
        ) {
            item ->
            Text(text = item.toString())
        }
    }
}