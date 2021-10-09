package HPF;

public class HPFPremptBatchRun extends HPFBatchRun {
    
    public HPFPremptBatchRun(int times) {
        super(times);
    }

    @Override
    public HPF getHPFProcess(int seed) {
        return new HPFPrempt(seed);
    }
}
