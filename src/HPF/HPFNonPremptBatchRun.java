package HPF;

public class HPFNonPremptBatchRun extends HPFBatchRun {
    
    public HPFNonPremptBatchRun(int times) {
        super(times);
    }

    @Override
    public HPF getHPFProcess(int seed) {
        return new HPFNonPrempt(seed);
    }
}
