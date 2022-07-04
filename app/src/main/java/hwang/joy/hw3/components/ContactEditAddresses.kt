package hwang.joy.hw3.components
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hwang.joy.hw3.data.AddressEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ContactEditAddresses(
    scope: CoroutineScope,
    addresses: List<AddressEntity>?,
    getKey: (AddressEntity) -> String,
    onAddressDelete: suspend (String) -> Unit,
    onAddressEdit: suspend (AddressEntity) -> Unit,
) {
    Log.d("jhw%", addresses?.size.toString())
    Column {
        addresses?.forEach { address ->
            Log.d("jhw%", address.street)
            val key = getKey(address)
            Card(
                elevation = 4.dp,
                backgroundColor = MaterialTheme.colors.surface,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable {
                        scope.launch {
                            onAddressEdit(address)
                        }
                    }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Log.d("jhw%", "row here should say ${address.street}")
                    Text(text = address.street, modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            scope.launch(Dispatchers.IO) {
                                onAddressDelete(key)
                            }
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete",
                        )
                    }
                }
            }
        }
    }
}