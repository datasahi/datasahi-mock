package datasahi.mock.redis;

public class LoadResponse {

    private final LoadRequest request;

    private int generatorMilis;
    private int redisMilis;
    private int sleepMilis;
    private int totalCount;

    public LoadResponse(LoadRequest request) {
        this.request = request;
    }

    public int getRedisMilis() {
        return redisMilis;
    }

    public LoadResponse setRedisMilis(int redisMilis) {
        this.redisMilis = redisMilis;
        return this;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public LoadResponse setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public int getSleepMilis() {
        return sleepMilis;
    }

    public LoadResponse setSleepMilis(int sleepMilis) {
        this.sleepMilis = sleepMilis;
        return this;
    }

    public LoadRequest getRequest() {
        return request;
    }

    public int getGeneratorMilis() {
        return generatorMilis;
    }

    public LoadResponse setGeneratorMilis(int generatorMilis) {
        this.generatorMilis = generatorMilis;
        return this;
    }

    @Override
    public String toString() {
        return "LoadResponse{" +
                "request=" + request +
                ", generatorMilis=" + generatorMilis +
                ", redisMilis=" + redisMilis +
                ", sleepMilis=" + sleepMilis +
                ", totalCount=" + totalCount +
                '}';
    }
}
