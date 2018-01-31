package com.findlowcost;

/**
 * Created by trinay on 1/31/2018.
 */

public class PathStateCollector
{
    private PathState bestPath;
    private PathStateComparator comparator;

    public PathStateCollector() {
        comparator = new PathStateComparator();
    }

    public PathState getBestPath() {
        return bestPath;
    }

    public void addPath(PathState newPath) {
        if (comparator.compare(newPath, bestPath) < 0) {
            bestPath = newPath;
        }
    }
}
