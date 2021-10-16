import java.io.Serializable;

public class NaiveObject implements Serializable {
    private int myInt;

    public NaiveObject(int anInt) {
        this.myInt = anInt;
    }

    public int getMyInt() {
        return myInt;
    }

    @Override
    public String toString() {
        return "NaiveObject withholds int " + myInt;
    }
}
