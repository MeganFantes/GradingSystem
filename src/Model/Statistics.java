package Model;

import Model.Leaf;
import Model.LeafNode;

import java.util.HashMap;
import java.util.Map;

public class Statistics {
    // note that this class only called to generate object that connect to a leaf node during runtime
    // thus don't need to implement serializable
    private float totalScore;
    private HashMap<String, Float> allPercentageScore;

    public Statistics(LeafNode leafNode){
        totalScore = 100; // [%]
        allPercentageScore = new HashMap<>();
        for (Map.Entry<String, Leaf> entry : leafNode.getAllLeaf().entrySet()) {
            Float trueScore = 0f;
            Float currScore = entry.getValue().getValue();
            switch (leafNode.getInputType()){
                case PERCENTAGE:
                    trueScore = currScore;
                    break;
                case DEDUCTION:
                    trueScore = (leafNode.getTotalScore()-currScore) / leafNode.getTotalScore() * 100;
                    break;
                case RAW:
                    trueScore = currScore / leafNode.getTotalScore() * 100;
                    break;
                default:
                    assert(false);
            }
            allPercentageScore.put(entry.getKey(), trueScore);
        }
    }

    public Statistics(HashMap<String, Float> scoreMap){
        // scoreMap is from aggregation (where range in [0~100] %)
        allPercentageScore = scoreMap;
        totalScore = 100;
    }

    public HashMap<String, Float> computeStatistics(){
        // all statistics represented in percentage

        float sum = 0f, sqrsum = 0f;
        float minScore = 101, maxScore = 0;

        for (Map.Entry<String, Float> entry : allPercentageScore.entrySet()) {
            // convert score to percentage
            float percent = entry.getValue();

            sum += percent;
            sqrsum += percent*percent;
            minScore = Math.min(minScore, percent);
            maxScore = Math.max(maxScore, percent);
        }

        float avg = sum / allPercentageScore.size();
        float sqravg = sqrsum / allPercentageScore.size();
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
