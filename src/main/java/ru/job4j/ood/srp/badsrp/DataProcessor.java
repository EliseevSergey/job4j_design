package ru.job4j.ood.srp.badsrp;

public class DataProcessor {
    private String path;
    private int size;

    public DataProcessor(String path, int size) {
        this.path = path;
        this.size = size;
    }

    public DataProcessor createDataProccessor(String path, int size) {
        DataProcessor dataProcessor = new DataProcessor(path, size);
        return dataProcessor;
    }

    public void process(String data) {
        /*data processing*/
    }

    public boolean validation(String data) {
        /*data validation*/
        return false;
    }
}
