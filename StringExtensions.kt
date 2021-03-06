
/**
* Returns the last item of a string
*/
fun String.tail() = this.substring(1)

/**
* Returns the first item of a string
*/
fun String.head() = this.substring(0, 1)

/**
* Very Very Very Very simple way to parse a string to date
* You'll not use this in production, pinky promise
*/
fun String.toDate(format: String): Date = SimpleDateFormat(format).parse(this)

/**
* Normalizes a string
*/
fun String.normalizeString() =
    Normalizer.normalize(this, Normalizer.Form.NFD)
        .toLowerCase()
        .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")


/**
* URL Encode a string
*/
fun String.urlEncode() = URLEncoder.encode(this, "UTF-8")


/**
* println("14q36w25e".ascending())  // output "123456eqw"
*/
fun String.ascending() = String(toCharArray().sortedArray())
