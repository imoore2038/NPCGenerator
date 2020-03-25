import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class NonPlayerCharacter {
    private String fName;
    private int age;
    private String gender;
    private IdentityData race;
    private String hairstyle;
    private String accessories;

    public NonPlayerCharacter() {
        String raceName = pullFromPool("raceList", "data/pools/raceList.txt", 100);
        this.race = new IdentityData("data/config/" + raceName);
        hairstyle = pullFromPool(race.findPoolByName("hair"));
        fName = pullFromPool(race.findPoolByName("firstNames"));
        accessories = pullFromPool(race.findPoolByName("accessories"));
    }

    public String pullFromPool(Pool pool) {
        Random random = new Random();
        ArrayList<String> poolData= new ArrayList<String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(pool.getFileName()));
            String line;
            while ((line = reader.readLine()) != null) {
                poolData.add(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int upperRange = poolData.size();
        int index = random.nextInt(upperRange);
        StringBuilder data = new StringBuilder(poolData.get(index));
        if (data.toString().contains("[")){
            ArrayList<String> processingArray = new ArrayList<>();
            int stringEndIndex = data.indexOf("$");
            StringBuilder extraDetails = new StringBuilder(data.substring(stringEndIndex+1));
            data.delete(stringEndIndex,data.length());
            int i = 0;
            int j = 0;
            while (data.toString().contains("[")){
                i = 0;
                j = data.indexOf("[");
                processingArray.add(data.substring(i,j));
                processingArray.add(data.substring(j+1,j+2));
                data.delete(i,j+3);
            }
            if (data.length()>0) processingArray.add(data.toString());
            while(extraDetails.toString().contains("{")){
                int k = extraDetails.indexOf("{");
                String detailNumber = extraDetails.substring(k-1,k);
                int bound = Integer.parseInt(extraDetails.substring(k+1,k+2));
                int extraRoll = random.nextInt(bound-1)+1;
                int selector = 1;
                int lowerBound = k+3;
                while (extraRoll!=selector){
                    lowerBound++;
                    if (extraDetails.charAt(lowerBound) == '(') selector++;
                }
                int upperBound = lowerBound;
                while (extraDetails.charAt(upperBound)!=')') upperBound++;
                processingArray.add(processingArray.indexOf(detailNumber),extraDetails.substring(lowerBound+1,upperBound));
                processingArray.remove(detailNumber);
                extraDetails.delete(k-1,extraDetails.indexOf("*")+1);
            }
            data.delete(0,data.length());
            for (String s: processingArray){
                //System.out.print(s+"||");
                data.append(s);
            }
        }
        return data.toString();
    }

    public String pullFromPool(String poolName, String fileName, int weight){
        Pool pool = new Pool(poolName, fileName, weight);
        return pullFromPool(pool);
    }

    public String pullFromMultiplePools(ArrayList<Pool> poolList){
        Random random = new Random();
        Pool chosenPool;
        int upperRollBound = 0;
        for (Pool p : poolList){
            upperRollBound += p.getWeight();
        }
        int roll = random.nextInt(upperRollBound)+1;
        int poolIndex = 0;
        while(roll<= poolList.get(poolIndex).getWeight()){
            poolIndex = (poolIndex+1) % poolList.size();
        }
        return pullFromPool(poolList.get(poolIndex));
    }

    public String getFirstName() {
        return fName;
    }

    public IdentityData getRace() {
        return race;
    }

    public String getHairstyle() {
        return hairstyle;
    }

    public String getAccessories() {
        return accessories;
    }
}
