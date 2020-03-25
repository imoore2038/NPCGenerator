import java.util.ArrayList;

public class CompositePool extends Pool{

    private ArrayList<Pool> poolGroup = new ArrayList<>();

    public CompositePool(String poolName, String fileName, int weight, ArrayList<Pool> poolGroup) {
        super(poolName, fileName, weight);
        this.poolGroup = poolGroup;
    }

    public int getSize(){
        return poolGroup.size();
    }
    public ArrayList<Pool> getPoolGroup() {
        return poolGroup;
    }
}
