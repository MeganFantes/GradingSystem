import java.util.HashMap;
import java.util.Map;

public class Statistics {
    // note that this class only called to generate object that connect to a leaf node during runtime
    // thus don't need to implement serializable
    private LeafNode leafNode;

    public Statistics(LeafNode leafNode){
        this.leafNode = leafNode;
    }

    public HashMap<String, Float> computeStatistics(){
        float[] allScores = leafNode.getAllLeafValue();
        float sum = 0f, sqrsum = 0f;
        float minScore = allScores[0], maxScore = allScores[0];
        for(int i=0; i<allScores.length; i++) {
            sum += allScores[i];
            sqrsum += allScores[i] * allScores[i];
            minScore = Math.min(minScore, allScores[i]);
            maxScore = Math.max(maxScore, allScores[i]);
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
}
