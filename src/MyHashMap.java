import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MyHashMap {

    private ArrayList<LinkedList<MultiSet>> myMap = new ArrayList<>(0);
    private int size;
    public MyHashMap(String filename) {
        List<Integer> myList = sieveOfEratosthenes(lineCount(filename));
        size = myList.get(myList.size()-1);
        System.out.println(size);

        for (int i = 0; i < size ; i++) {
            myMap.add( new LinkedList<>());
        }

        read(filename);

    }

    public ArrayList<LinkedList<MultiSet>> getMyMap() {
        return myMap;
    }

    public void add(MultiSet m){
            for ( MultiSet multiTemp : myMap.get(m.hashCode()%size)) {
                if(m.equals(multiTemp))return;
            }
        myMap.get(m.hashCode()%size).add(m);
    }



    /**
     * retourne la liste des nombres premiers jussqu'à n
     * @param n
     * @return a List with the prime numbers until n
     */
    private List<Integer> sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i]= false;
                }
            }
        }
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    public int lineCount(String filename){
        int counter = 0;
        try {

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            ++counter;
        }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }
    public void read(String filename){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                this.add(new MultiSet(line));
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> search2Somme(String ensemble){
        ArrayList<String> myCouple = new ArrayList<>(0);
        MultiSet multiSetOrigin = new MultiSet(ensemble);
        for (LinkedList<MultiSet> l : myMap) {
            for (MultiSet m : l) {
                MultiSet myComplementary = multiSetOrigin.complementary(m);
                MultiSet researchComplementary = searchComplementary(myComplementary);
                if(researchComplementary != null){
                    myCouple.add(m.getWord());
                    myCouple.add(researchComplementary.getWord());
                    return myCouple;
                }
            }
        }
        return myCouple;
    }
    public MultiSet searchComplementary(MultiSet myComp){
        int myCode = myComp.hashCode()%size;
        if(!myMap.get(myCode).isEmpty()){
            for (MultiSet m: myMap.get(myCode)) {
                if(m.equals(myComp))return m;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("MyHashMap{" +
                ", size=" + size + "\n");
        for (int i = 0; i <  size; i++) {
            if(myMap.get(i).size()!=0){
                str.append(i).append(" : ").append(myMap.get(i)).append("\n");
            }
        }
        return str.toString();
    }
}
