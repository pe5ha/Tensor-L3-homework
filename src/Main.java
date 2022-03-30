import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Тест задания 2a: перечисления в java
//        testTask2a();

        // Тест задания 2b: класс Card на kotlin
//        testTask2b();

        // Тест задания 3: File System на kotlin
        testTask5();

    }

    public static void testTask5() {
        File file = new File("reg.lam.ent.pdf", null);
        File file2 = new File("text.txt", null);
        File file3 = new File("text2.txt", null);
        ArrayList<FileSystemNode> files = new ArrayList<>();
        files.add(file);
        files.add(file2);
        files.add(file3);
        Folder folder = new Folder("myFolder", null, files);
        System.out.println("Расширение файла file: " + ((File) folder.getChildElements().get(0)).getExtension());

        files = new ArrayList<>();
        files.add(new File("img.png", null));
        Folder folder2 = new Folder("downloads", null, files);
        File file1 = new File("code.kt", folder2);

        Folder emptyFolder = new Folder("trash", folder, null);
        File fileInFolder = new File("meme.jpg", emptyFolder);
        File fileInFolder2 = new File("meme2.jpg", emptyFolder);

        files = new ArrayList<>();
        files.add(folder);
        files.add(folder2);
        files.add(new File("code.java", null));
        Folder rootFolder = new Folder("root", null, files);

        System.out.println("Путь к файлу file: " + file.getPath());
        System.out.println("Дерево папок\n" + rootFolder);
    }

    public static void testTask2b() {
        CardKt card = new CardKt(SuitsKt.CLUBS, RankKt.FIVE);
        CardKt card1 = new CardKt(SuitsKt.CLUBS, RankKt.FOUR);
        CardKt card2 = new CardKt(SuitsKt.DIAMONDS, RankKt.FOUR);
        System.out.println("card: " + card);
        System.out.println("card is better then card1: " + card.isBetterThen(card1));
        System.out.println("card2 > card1: " + card1.compare(card2));
        System.out.println("card > card1: " + card.compare(card1));

    }

    public static void testTask2a() {
        Card card = new Card(Suits.CLUBS, Rank.FIVE);
        Card card1 = new Card(Suits.CLUBS, Rank.FOUR);
        Card card2 = new Card(Suits.DIAMONDS, Rank.FOUR);
        System.out.println("card: " + card);
        System.out.println("card is better then card1: " + card.isBetterThen(card1));
        System.out.println("card2 > card1: " + card1.compare(card2));
        System.out.println("card > card1: " + card.compare(card1));

    }
}



