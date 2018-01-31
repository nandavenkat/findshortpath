package com.findlowcost;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by trinay on 1/31/2018.
 */

public class GridVisitor
{
    private Grid grid;
    private PathStateComparator pathComparator;

    public GridVisitor(Grid grid)
    {
        if (grid == null) {
            throw new IllegalArgumentException("A visitor requires a grid");
        }

        this.grid = grid;
        pathComparator = new PathStateComparator();
    }

    public PathState getBestPathForGrid() {
        List<PathState> allPaths = new ArrayList();
        for (int row = 1; row <= grid.getRowCount(); row++) {
            RowVisitor visitor = new RowVisitor(row, grid, new PathStateCollector());
            allPaths.add(visitor.getBestPathForRow());
        }

        Collections.sort(allPaths, pathComparator);

        return (PathState)allPaths.get(0);
    }
}


