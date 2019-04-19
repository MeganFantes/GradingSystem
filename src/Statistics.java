import java.util.HashMap;

public class Statistics {
    // note that this class only called to generate object that connect to a leaf node during runtime
    // thus don't need to implement serializable
    private LeafNode leafNode;

    public Statistics(LeafNode leafNode){
        this.leafNode = leafNode;
    }

    public HashMap<String, Float> computeStatistics(){
        // all statistics represented in percentage
        float[] allScores = leafNode.getAllLeafValue();

        float sum = 0f, sqrsum = 0f;
        float minScore = 101, maxScore = 0;

        for(float score : allScores) {
            // convert score to percentage
            float percent = 0;
            switch (leafNode.getInputType()){
                case PERCENTAGE:
                    percent = score * 100;
                    break;
                case DEDUCTION:
                    percent = (leafNode.getTotalScore()-score) / leafNode.getTotalScore() * 100;
                    break;
                case RAW:
                    percent = score / leafNode.getTotalScore() * 100;
                    break;
                default:
                    assert(false);
            }

            sum += percent;
            sqrsum += percent*percent;
            minScore = Math.min(minScore, percent);
            maxScore = Math.max(maxScore, percent);
        }

        float avg = sum / allScores.length;
        float sqravg = sqrsum / allScores.length;
        float stddev = (float)Math.sqrt(sqravg - avg*avg);

        HashMap<String, Float> retMap = new HashMap<>();
        retMap.put("min", minScore);
        retMap.put("max", maxScore);
        retMap.put("avg", avg);
        retMap.put("stddev", stddev);

        return retMap;
    }

    public String toString(){
        return "obtain Statistics";
    }
}
