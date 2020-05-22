
/**
 * This extension property will be used in when statements to exhaust all of its branches.
 *
 * This property has a custom getter which returns the object itself,
 * so if we use it on a when block, it’s treated as an expression and the compiler will
 * force us to specify all cases.
 *
 * https://proandroiddev.com/til-when-is-when-exhaustive-31d69f630a8b
 */
val <T> T.exhaustive: T
  get() = this


/**
* Showing a toast is never been easier
* ``` Usage `````
* //In your activity/fragment class, just call
* toast("This is a toast")
*/
fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, text, duration).show()


/**
 * Extension function to do shared pref stuffs in a DSLish way
 * 
 * ````` Usage ``````
        preferences.edit {
            putString(TOKEN, tokenValue)
        }
 * ```````````````````
 */
@SuppressLint("ApplySharedPref")
inline fun SharedPreferences.edit(commit: Boolean = false, action: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    action(editor)
    if (commit) editor.commit()
    else editor.apply()
}
