import java.io.Serializable;

public class IlegalObject implements Serializable {
    private NaiveObject naiveObject;

    public IlegalObject(int num) {
        this.naiveObject = new NaiveObject(num);
    }
}
