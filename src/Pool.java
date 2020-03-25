public class Pool {
    private String poolName;
    private String fileName;
    private int weight;

    public Pool(String poolName, String fileName, int weight) {
        this.poolName = poolName;
        this.fileName = fileName;
        this.weight = weight;
    }

    public String getPoolName() {
        return poolName;
    }

    public String getFileName() {
        return fileName;
    }

    public int getWeight() {
        return weight;
    }
}
