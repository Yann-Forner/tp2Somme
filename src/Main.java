import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MultiSet m1 = new MultiSet("ae");
        MultiSet m2 = new MultiSet("abcd");
        MultiSet m3 = new MultiSet("abcde");
        //complementaire
        System.out.println(m2.complementary(m1));
        //equals
        System.out.println(m3.equals(m1));
        //hascode
        System.out.println(m1.hashCode());
        System.out.println(m3.hashCode());

        //myHashmap
        MyHashMap myHashMap = new MyHashMap("dico.txt");
//        System.out.println(myHashMap);

        System.out.println(myHashMap.search2Somme("abattageabréviation"));
    }
}
