import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class IdentityData {
    private String identityName;
    private ArrayList<Pool> pools = new ArrayList<>();

    public IdentityData(String fileName){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            this.identityName = reader.readLine();
            String[] poolInfo= new String[3];
            while ((line = reader.readLine()) != null) {
                poolInfo = line.split(" ");
                pools.add(( new Pool(poolInfo[0], poolInfo[1], Integer.parseInt(poolInfo[2])) ) );
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Pool findPoolByName(String poolName){
        for (Pool p: pools){
            if (p.getPoolName().equals(poolName)){
                return p;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return identityName;
    }

    public String poolsToString(){
        StringBuffer str = new StringBuffer("");
        for (Pool p: pools){
            str.append(p.getPoolName() + " located at " + p.getFileName() + " with weight " + p.getWeight() + ".\n");
        }
        return str.toString();
    }
}
