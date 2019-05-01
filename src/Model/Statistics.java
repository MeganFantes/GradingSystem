/*
   this file includes the class which is used to compute statistics
 */

package Model;

import Model.Leaf;
import Model.LeafNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Statistics {
    // note that this class only called to generate object that connect to a leaf node during runtime
    // thus don't need to implement serializable
    private float totalScore;
    private HashMap<String, Float> allPercentageScore;
    private LeafNode leafNode;

    // =========  constructors ===========
    public Statistics(LeafNode leafNode){
        this.leafNode = leafNode;
        allPercentageScore = null;
        totalScore = 100; // [%]
    }

    public Statistics(HashMap<String, Float> aggregationScore){
        // scoreMap is from aggregation (where range in [0~100] %)
        leafNode = null;
        allPercentageScore = aggregationScore;
        totalScore = 100;
    }
    // ===================================

    public HashMap<String, Float> computeStatistics(){
        if (leafNode != null){
            // from LeafNode, socre might be updated ( assignment view)
            // so we need to recompute to percentage every time

            // convert all score in LeafNode to percentage
            ArrayList<Float> currLeafScores = new ArrayList<>();
            for (Map.Entry<String, Leaf> entry : leafNode.getAllLeaf().entrySet()) {
                Float trueScore = 0f;
                Float currScore = entry.getValue().getValue();
                switch (leafNode.getInputType()){
                    case Percentage:
                        trueScore = currScore;
                        break;
                    case Deduction:
                        trueScore = (leafNode.getTotalScore()-currScore) / leafNode.getTotalScore() * 100;
                        break;
                    case Raw:
                        trueScore = currScore / leafNode.getTotalScore() * 100;
                        break;
                    default:
                        assert(false);
                }
                if (!trueScore.isNaN())
                    currLeafScores.add(trueScore);
            }

            // all statistics represented in percentage
            float sum = 0f, sqrsum = 0f;
            float minScore = 101, maxScore = 0;

            for (Float percent : currLeafScores) {
                sum += percent;
                sqrsum += percent*percent;
                minScore = Math.min(minScore, percent);
                maxScore = Math.max(maxScore, percent);
            }

            float avg = sum / currLeafScores.size();
            float sqravg = sqrsum / currLeafScores.size();
            float stddev = (float)Math.sqrt(sqravg - avg*avg);

            if (minScore==101)
                minScore = Float.NaN;
            if (maxScore==0)
                maxScore = Float.NaN;

            HashMap<String, Float> retMap = new HashMap<>();
            retMap.put("min", minScore);
            retMap.put("max", maxScore);
            retMap.put("avg", avg);
            retMap.put("stddev", stddev);

            return retMap;
        }

        // this is where summarization view statistics is calculated
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

        if (minScore==101)
            minScore = Float.NaN;
        if (maxScore==0)
            maxScore = Float.NaN;

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
