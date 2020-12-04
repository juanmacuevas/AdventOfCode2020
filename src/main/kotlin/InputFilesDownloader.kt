import java.io.File
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDate
import java.time.Period

class InputFilesDownloader {
    private val filename = "/day%02d.txt"
    private val LAST_DAY = 25
    private val path = System.getProperty("user.dir")
    private val urlString = "https://adventofcode.com/2020/day/%d"

    fun downloadAll() {
        val toDownload = rangeOfDaysPublished().filter { notDownloadedYet(it) }
        if (toDownload.isEmpty())
            println("Nothing to download")
        toDownload.forEach {
            downloadDay(it)
        }
    }

    private fun rangeOfDaysPublished(): IntRange {
        val competitionStarted = LocalDate.of(2020, 12, 1)
        val today = LocalDate.now()
        var daysSinceStarted = Period.between(competitionStarted, today).days + 1
        daysSinceStarted = Math.min(daysSinceStarted, LAST_DAY)
        return (1..daysSinceStarted)
    }

    private fun notDownloadedYet(i: Int): Boolean {
        return !File(path.plus(filename.format(i))).exists()
    }

    private fun downloadDay(i: Int) {
        val problemUrl = urlString.format(i)
        val inputUrl = URL(problemUrl.plus("/input"))
        with(inputUrl.openConnection() as HttpURLConnection) {
            requestMethod = "GET"

            /**
             *  Unresolved reference: cookie
             *  you need to initialize the cookie to make get requests
             *  log in https://adventofcode.com/ url bar > 🔒 > Cookies > session > Content
             *  val cookie: String = "53616c7465645f5f817cf341edd1a8fc............"
             */
            setRequestProperty("cookie", "session=%s".format(cookie))

            println("Downloading input for: $problemUrl\nResponse Code : $responseCode")
            val file = File(path.plus(filename.format(i)))
            file.copyInputStreamToFile(inputStream)
        }
    }

    fun File.copyInputStreamToFile(inputStream: InputStream) {
        this.outputStream().use { fileOut ->
            inputStream.copyTo(fileOut)
        }
    }


}

