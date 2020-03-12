import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class MultiSet {
    private String word;
    private ArrayList<Character> mySet = new ArrayList<>(0);

    public MultiSet(String word) {
        this.word = word;
        for (int i = 0; i < word.length() ; i++) {
            mySet.add(word.charAt(i));
        }
        Collections.sort(mySet);
    }

    public ArrayList<Character> getMySet() {
        return mySet;
    }

    public void setMySet(ArrayList<Character> mySet) {
        this.mySet = mySet;
    }

    public MultiSet complementary(MultiSet m ){
        ArrayList<Character> myArrayTemp = new ArrayList<>(mySet);
        for ( Character c: m.getMySet()) {
            for (Character elemChar: myArrayTemp) {
                if(c.equals(elemChar)){
                    myArrayTemp.remove(elemChar);
                    break;
                }
            }
        }
        MultiSet myMultiset  = new MultiSet("");
        myMultiset.setMySet(myArrayTemp);
        return myMultiset;
    }

    public String getWord() {
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiSet multiSet = (MultiSet) o;
        if(mySet.size() != multiSet.getMySet().size())return false;
        for (int i = 0; i < multiSet.getMySet().size() && i < mySet.size(); i++) {
            if(!mySet.get(i).equals(multiSet.getMySet().get(i)))return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        int myCode=0;
        for (Character c: mySet) {
            myCode+=c-96;
        }
        if(myCode<0) myCode*=-1;
        return myCode;
    }

    @Override
    public String toString() {
        String myStr="";
        for (Character c: mySet
             ) {
            myStr += c +" ";
        }
        return myStr;
    }
}
