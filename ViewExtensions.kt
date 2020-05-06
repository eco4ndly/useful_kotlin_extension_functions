/**
 * Extension method to make a view gone
 */
fun View.hide() {
  visibility = View.GONE
}

/**
 * Extension method to make a view visible.
 */
fun View.show() {
  visibility = View.VISIBLE
}

/**
 * Extension method to make a view invisible
 */
fun View.invisible() {
  visibility = View.INVISIBLE
}

/**
* Show depending upon a condition
*/
inline fun <T : View> T.showIf(condition: (T) -> Boolean): T {
    if (condition(this)) {
        show()
    } else {
        hide()
    }

    return this
}

/**
* Hide depending upon a condition
*/
inline fun <T : View> T.hideIf(condition: (T) -> Boolean): T {
    if (condition(this)) {
        hide()
    } else {
        show()
    }

    return this
}

/**
* Extension method to inflate a layout file
* ~BASIC USAGE~
* val view = parent.inflate(R.layout.layout_file)
*/
fun ViewGroup.inflate(@LayoutRes resource: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(resource, this, attachToRoot)

/**
 * Extension method to show keyboard.
 */
fun Activity.showKeyboard(view: View) {
    val imm: InputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

/**
 * Extension method to hide keyboard.
 */
fun Activity.hideSoftKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager = getSystemService(Context
            .INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}
