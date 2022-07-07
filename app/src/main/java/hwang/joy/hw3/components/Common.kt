// Attribution: Scott Stanfield, Android Summer 2022
// Source code: https://gitlab.com/605-686/android-summer-2022/-/tree/main/Movies3

package hwang.joy.hw3.components

import androidx.compose.runtime.Immutable
import hwang.joy.hw3.data.AddressEntity
import hwang.joy.hw3.data.ContactEntity

// Immutables logic
@Immutable
data class ImmutableList<T>(val list: List<T>): List<T> by list
fun <T> emptyImmutableList() = ImmutableList<T>(emptyList())

@Immutable
data class ImmutableSet<T>(val set: Set<T>): Set<T> by set {
    operator fun plus(item: T) = ImmutableSet(set + item)
    operator fun minus(item: T) = ImmutableSet(set - item)
}
fun <T> emptyImmutableSet() = ImmutableSet<T>(emptySet())

// Ported over Kotlin comparator function here due to resolution ambiguity error
// Error: https://youtrack.jetbrains.com/issue/KTIJ-9051
// Attribution: Kotlin source code
// https://github.com/JetBrains/kotlin/blob/master/libraries/stdlib/src/kotlin/comparisons/Comparisons.kt
fun <T> compareValuesByImpl(a: T, b: T, selectors: Array<out (T) -> Comparable<*>?>): Int {
    for (fn in selectors) {
        val v1 = fn(a)
        val v2 = fn(b)
        val diff = compareValues(v1, v2)
        if (diff != 0) return diff
    }
    return 0
}

// Cannot actually overload compareBy function, so renaming
fun <T> compareByOver(vararg selectors: (T) -> Comparable<*>?): Comparator<T> {
    require(selectors.isNotEmpty())
    return Comparator { a, b -> compareValuesByImpl(a, b, selectors) }
}


// Form data logic
class ContactFormData(incomingContact: ContactEntity? = ContactEntity("", "", "", "", "", "", "")) {
    var firstName = incomingContact?.firstName
    var lastName = incomingContact?.lastName
    var homePhone = incomingContact?.homePhone
    var workPhone = incomingContact?.workPhone
    var mobilePhone = incomingContact?.mobilePhone
    var emailAddress = incomingContact?.emailAddress
}

fun createNewContact(): ContactEntity {
    return ContactEntity(
        firstName = "",
        lastName = "",
        homePhone = "",
        workPhone = "",
        mobilePhone = "",
        emailAddress = "",
    )
}

class AddressFormData(incomingAddress: AddressEntity? = AddressEntity("","","","","","","")) {
    var type = incomingAddress?.type
    var street = incomingAddress?.street
    var city = incomingAddress?.city
    var state = incomingAddress?.state
    var zip = incomingAddress?.zip
}

fun createNewAddress(contactId: String): AddressEntity {
    return AddressEntity(
        type = "",
        street = "",
        city = "",
        state = "",
        zip = "",
        contactId = contactId,
    )
}
