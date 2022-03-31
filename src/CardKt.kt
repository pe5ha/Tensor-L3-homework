import java.util.Objects

enum class SuitsKt {
    HEARTS, SPADES, DIAMONDS, CLUBS
}

class CardKt(var suit: SuitsKt, var rank: RankKt) {

    fun isBetterThen(card: CardKt): Boolean =
        suit == card.suit && rank.rankValue > card.rank.rankValue


    fun compare(card: CardKt): Int { // >0 if this card better, =0 if equal, <0 if this card weaker
        if (suit == card.suit) return rank.rankValue - card.rank.rankValue else {
            for (s in SuitsKt.values()) {
                if (s == suit) return 1
                if (s == card.suit) return -1
            }
        }
        return -1000
    }
    fun compare2(card: CardKt): Int =
        when{
            suit == card.suit -> rank.rankValue - card.rank.rankValue
            suit.ordinal < card.suit.ordinal -> 1
            else -> -1
        }

    override fun toString(): String =
        "$suit-$rank"


    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val card = o as CardKt
        return rank === card.rank && suit == card.suit
    }

    override fun hashCode(): Int {
        return Objects.hash(suit, rank)
    }

    companion object {
        fun compare(card: CardKt, card1: CardKt): Int { // >0 if card better then card1, =0 if equal, <0 otherwise
            return card.compare(card1)
        }
    }
}