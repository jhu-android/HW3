package hwang.joy.hw3

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


// Composable - Label
// Composable - Display
// Composable - Common Text
// Composable - Common Text Field

// List logic
// Interface - List
sealed interface ListSelection {
    operator fun contains(id: String): Boolean
}
object NoListSelection: ListSelection {
    override fun contains(id: String) = false
}
data class SingleListSelection(val id: String): ListSelection {
    override fun contains(id: String ) = (this.id == id)
}
data class MultiListSelection(val ids: List<String>): ListSelection {
    override fun contains(id: String) = (id in ids)
}

// Composable - CommonList
@Composable
fun <T> CommonList(
    modifier: Modifier,
    items: List<T>,
    getId: T.() -> String,
    getText: T.() -> String,
    selection: ListSelection,
) {
    Log.d("commonList", "ITEMS: $items")
    LazyColumn(modifier = modifier) {
        items(
            items = items,
            key = { it.getId() }
        ) { item ->
            Log.d("commonList", "item: ${item.getText()}")
            val id = item.getId()
            Text(text = item.getText())
//            val selected = selection.contains(id)
//            Card {
//                Row {
//                    Text(
//                        text = item.getText()
//                    )
//                }
//            }

        }
    }
}


