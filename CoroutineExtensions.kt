
/**
 * **How to use it**
 * //In Activity or Fragment
 * @code
   private val mainScope = MainScope()
   val btnClick: Button = findViewById(R.id.btn)
   btnClick.clicks()
            .onEach {
                //DO STUFF ON CLICK
            }
            .launchIn(mainScope)
 */
@ExperimentalCoroutinesApi
fun View.clicks(): Flow<Unit> = callbackFlow {
  val listener = View.OnClickListener { safeOffer(Unit) }
  setOnClickListener(listener)
  awaitClose {
    setOnClickListener(null)
  }
}

/**
 * Flow conversion of edittext text changes
 * `````Usage``````
 *  val editText = EditText
 *  editText
 *    .textChanges()
 *    .collect { charSeq ->
 *    
 *    }
 */
@ExperimentalCoroutinesApi
@CheckResult
fun EditText.textChanges(): Flow<CharSequence?> {
  return callbackFlow<CharSequence?> {
    val listener = object : TextWatcher {
      override fun afterTextChanged(s: Editable?) = Unit
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        safeOffer(s)
      }
    }
    addTextChangedListener(listener)
    awaitClose { removeTextChangedListener(listener) }
  }.onStart { emit(text) }
}


/**
 * To add FLOW click listener on multiple views at once.
 * Rather having code like this
 * @code
 *    view1.clicks()
        .onEach {
          //View 1 Stuffs
        }
        .launchIn(mainScope)

      view2.clicks()
        .onEach {
          //View 1 Stuffs
        }
        .launchIn(mainScope)

      view3.clicks()
        .onEach {
          //View 1 Stuffs
      }
      .launchIn(mainScope)
 **************************
 * Having this |
 * @code
 *    clicks(view1, view2, view3, mainScope) { clickedViewId ->
        when(clickedViewId) {
          R.id.view1 -> {}
          R.id.view2 -> {}
          R.id.view3 -> {}
        }
 *    }
 *******************************
 *
 * The code becomes much concise
 * 
 * Has dependency on Flow clicks() method 
 * @link https://github.com/eco4ndly/useful_kotlin_extension_functions/blob/master/CoroutineExtensions.kt
 */
fun Any.clicks(vararg views: View, mainScope: CoroutineScope, onClick: (viewId: Int) -> Unit) {

    views.forEach {view ->
        view.clicks() // Flow Clicks method
            .onEach {
                onClick.invoke(view.id)
            }.launchIn(mainScope)
    }
   
   
   @ExperimentalCoroutinesApi
   fun <E> SendChannel<E>.safeOffer(value: E) = !isClosedForSend && try {
   offer(value)
   } catch (t: Throwable) {
   // Ignore all
   false
   }

}
