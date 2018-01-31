package com.findlowcost;

/**
 * Created by trinay on 1/31/2018.
 */
import java.util.Iterator;

public class RowVisitor
{
    private int row;
    private Grid grid;
    private PathStateCollector pathCollector;

    public RowVisitor(int startRow, Grid grid, PathStateCollector collector) {
        if (grid == null)
            throw new IllegalArgumentException("A visitor requires a grid");
        if (collector == null)
            throw new IllegalArgumentException("A visitor requires a collector");
        if ((startRow <= 0) || (startRow > grid.getRowCount())) {
            throw new IllegalArgumentException("Cannot visit a row outside of grid boundaries");
        }

        row = startRow;
        this.grid = grid;
        pathCollector = collector;
    }

    public PathState getBestPathForRow() {
        PathState initialPath = new PathState(grid.getColumnCount());

        visitPathsForRow(row, initialPath);

        return pathCollector.getBestPath();
    }

    private void visitPathsForRow(int row, PathState path) {
        if (canVisitRowOnPath(row, path)) {
            visitRowOnPath(row, path);
        }

        java.util.List<Integer> adjacentRows = grid.getRowsAdjacentTo(row);
        boolean currentPathAdded = false;

        for (Iterator localIterator = adjacentRows.iterator(); localIterator.hasNext();) { int adjacentRow = ((Integer)localIterator.next()).intValue();
            if (canVisitRowOnPath(adjacentRow, path)) {
                PathState pathCopy = new PathState(path);
                visitPathsForRow(adjacentRow, pathCopy);
            } else if (!currentPathAdded) {
                pathCollector.addPath(path);
                currentPathAdded = true;
            }
        }
    }

    private boolean canVisitRowOnPath(int row, PathState path) {
        return (!path.isComplete()) && (!nextVisitOnPathWouldExceedMaximumCost(row, path));
    }

    private void visitRowOnPath(int row, PathState path) {
        int columnToVisit = path.getPathLength() + 1;
        path.addRowWithCost(row, grid.getValueForRowAndColumn(row, columnToVisit));
    }

    private boolean nextVisitOnPathWouldExceedMaximumCost(int row, PathState path) {
        int nextColumn = path.getPathLength() + 1;
        return path.getTotalCost() + grid.getValueForRowAndColumn(row, nextColumn) > PathState.MAXIMUM_COST;
    }
}