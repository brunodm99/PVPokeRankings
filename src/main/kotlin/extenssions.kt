fun List<String>.splitMoves(): String {
    var moves = toString()

    return moves.replace("[", "").replace("]", "")
}