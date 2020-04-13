
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

@ExperimentalCoroutinesApi
fun <E> SendChannel<E>.safeOffer(value: E) = !isClosedForSend && try {
  offer(value)
} catch (t: Throwable) {
  // Ignore all
  false
}
