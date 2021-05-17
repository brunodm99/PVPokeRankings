import com.google.gson.annotations.SerializedName
import java.lang.StringBuilder

class Ranking(
    private val speciesId: String,
    @SerializedName("speciesName")
    val name: String,
    val moveset: List<String>,
    val score: String){

    fun isXL() = !speciesId.endsWith("_xs")

    fun toCSV(delimiter: Char = ';'): String{
        val isXL = !speciesId.endsWith("_xs")

        val builder = StringBuilder(name).append(delimiter)
        builder.append(moveset.splitMoves()).append(delimiter)
        builder.append(score).append(delimiter)
        if(isXL) builder.append("xl$delimiter")

        return builder.toString()
    }

    override fun toString(): String {
        val isXL = !speciesId.endsWith("_xs")

        val builder = StringBuilder(name).append("[")
        builder.append("moveSet=").append(moveset).append(", ")
        builder.append("score=").append(score)

        if(isXL) builder.append(", ").append("XL")

        builder.append("]")


        return builder.toString()
    }
}
