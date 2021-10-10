package HPF;

public class HPFNonPremptBatchRun extends HPFBatchRun {
    
    public HPFNonPremptBatchRun(int times, boolean aging) {
        super(times, aging);
    }

    @Override
    public HPF getHPFProcess(int seed, boolean aging) {
        return new HPFNonPrempt(seed, aging);
    }
}
