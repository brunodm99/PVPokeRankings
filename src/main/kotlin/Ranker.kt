import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.IllegalArgumentException
import java.lang.StringBuilder
import java.net.URL
import java.nio.charset.StandardCharsets
import java.nio.file.Path
import java.util.*


object Ranker {
    private fun getJson(web: String): String{
        var jsonDoc = ""
        Scanner(
            URL(web).openStream(),
            StandardCharsets.UTF_8.toString()
        ).use { scanner ->
            scanner.useDelimiter("\\A")

            while(scanner.hasNext()){
                jsonDoc += scanner.next()
            }
        }

        return jsonDoc
    }

    fun ranking(ranking: String): List<Ranking>{
        val doc = getJson(ranking)

        val listType = object : TypeToken<ArrayList<Ranking?>?>() {}.type

        return Gson().fromJson(doc, listType)
    }

    fun csv(ranking: String, delimiter: Char = ';'): String{
        if(delimiter == ',') throw IllegalArgumentException(", is not a valid delimiter.")
        val list = ranking(ranking)
        val builder = StringBuilder()

        list.forEach { builder.append(it.toCSV(delimiter)).append("\n") }

        return builder.toString()
    }
}

object Rankings{
    const val RANKING_SUPER = "https://raw.githubusercontent.com/pvpoke/pvpoke/master/src/data/rankings/all/overall/rankings-1500.json"
    const val RANKING_ULTRA = "https://raw.githubusercontent.com/pvpoke/pvpoke/master/src/data/rankings/all/overall/rankings-2500.json"
    const val RANKING_MASTER = "https://raw.githubusercontent.com/pvpoke/pvpoke/master/src/data/rankings/all/overall/rankings-10000.json"
    const val RANKING_RETRO = "https://raw.githubusercontent.com/pvpoke/pvpoke/master/src/data/rankings/retro/overall/rankings-1500.json"
    const val RANKING_PREMIER_ULTRA = "https://raw.githubusercontent.com/pvpoke/pvpoke/master/src/data/rankings/premier/overall/rankings-2500.json"

}