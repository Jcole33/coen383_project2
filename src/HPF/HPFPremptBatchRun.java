package HPF;

public class HPFPremptBatchRun extends HPFBatchRun {
    
    public HPFPremptBatchRun(int times, boolean aging) {
        super(times, aging);
    }

    @Override
    public HPF getHPFProcess(int seed, boolean aging) {
        return new HPFPrempt(seed, aging);
    }
}
