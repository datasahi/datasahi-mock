package datasahi.mock.redis;

public class LoadRequest {

    private int batchSize;
    private int times;
    private int sleepMillis;

    public int getBatchSize() {
        return batchSize;
    }

    public LoadRequest setBatchSize(int batchSize) {
        this.batchSize = batchSize;
        return this;
    }

    public int getTimes() {
        return times;
    }

    public LoadRequest setTimes(int times) {
        this.times = times;
        return this;
    }

    public int getSleepMillis() {
        return sleepMillis;
    }

    public LoadRequest setSleepMillis(int sleepMillis) {
        this.sleepMillis = sleepMillis;
        return this;
    }

    @Override
    public String toString() {
        return "LoadRequest{" +
                "batchSize=" + batchSize +
                ", times=" + times +
                ", sleepMillis=" + sleepMillis +
                '}';
    }
}
