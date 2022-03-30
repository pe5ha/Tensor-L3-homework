import java.util.Objects;


enum Suits {
    HEARTS, SPADES, DIAMONDS, CLUBS
}

public class Card {
    private Suits suit;
    private Rank rank;

    public Card(Suits suit, Rank rank) {
        this.rank = rank;
        this.suit = suit;
    }

    public boolean isBetterThen(Card card) {
        return suit == card.suit && rank.getRankValue() > card.rank.getRankValue();
    }

    public int compare(Card card) { // >0 if this card better, =0 if equal, <0 if this card weaker
        if (suit == card.suit) return rank.getRankValue() - card.rank.getRankValue();
        else {
            for (Suits s : Suits.values()) {
                if (s == suit) return 1;
                if (s == card.suit) return -1;
            }
        }
        return -1000;
    }

    public static int compare(Card card, Card card1) { // >0 if card better then card1, =0 if equal, <0 otherwise
        return card.compare(card1);
    }

    @Override
    public String toString() {
        return suit + "-" + rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && Objects.equals(suit, card.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Suits getSuit() {
        return suit;
    }

    public void setSuit(Suits suit) {
        this.suit = suit;
    }

}
