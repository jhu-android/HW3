package hwang.joy.hw3.components

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Immutable
data class ImmutableList<T>(val list: List<T>): List<T> by list
fun <T> immutableListOf(vararg items: T) =
    ImmutableList(listOf(*items))
fun <T> emptyImmutableList() = ImmutableList<T>(emptyList())

@Immutable
data class ImmutableSet<T>(val set: Set<T>): Set<T> by set {
    operator fun plus(item: T) = ImmutableSet(set + item)
    operator fun minus(item: T) = ImmutableSet(set - item)
}
fun <T> emptyImmutableSet() = ImmutableSet<T>(emptySet())

@Immutable
data class TopAction(
    val icon: ImageVector,
    @StringRes val contentDescriptionId: Int,
    val onClick: () -> Unit,
)


@Composable
fun SimpleButton(
    text: String,
    onClick: () -> Unit,
) = Button(onClick = onClick) { Text(text = text) }


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


