package application;

import java.util.*;


/**
 * Created by Talal on 27.11.17.
 */
public class FeatureService {

    // List with all inserted point
    static ArrayList<Point> pointsList;


    public FeatureService() {
        pointsList = new ArrayList<>();
    }

    /**
     * add a point to the pointList
     */
    public void addPoint(Point point) {
        //add only if the point is not in the list
        if (!pointsList.contains(point)) {
            pointsList.add(point);
        }
    }

    /**
     * return all points in the list
     */
    public List<JSONPoint> getSpacePoints() {
        return pointListToJSONPointList(pointsList);
    }


    /**
     * compute all n collinear points and return a List that contains each set of collinear points
     */
    public List<List<JSONPoint>> getLineSegments(int n) {

        List<List<JSONPoint>> result = new ArrayList<>();

        Point[] arrayPointList = pointsList.toArray(new Point[pointsList.size()]);

        // clone the array because we will order it.
        Point[] clonedArray = arrayPointList.clone();

        //for each point check the collinear points
        for (Point pivot : arrayPointList) {

            // sort the cloned array by their slope with the pivot
            Arrays.sort(clonedArray, pivot.SLOPE_ORDER);


            double previousSlope = 0.0;
            List<Point> collinear = new ArrayList<>();


            for (int i = 0; i < clonedArray.length; i++) {
                // if the slope is not equal to previous slope it means that the points are collinear
                if (clonedArray[i].getSlope(pivot) != previousSlope) {
                    //if the collinear list contains n-1 ore more elements, add the pivot to the collinear list
                    // than sort the collinear and check if the pivot is the first element in the list
                    // if it is the case add the collinear list to the result otherwise don't since it wil be redundant
                    if (collinear.size() >= n - 1) {
                        collinear.add(pivot);
                        Collections.sort(collinear);
                        if (pivot == collinear.get(0)) {
                            result.add(pointListToJSONPointList(collinear));
                        }
                    }
                    //clear the collinear list
                    collinear = new ArrayList<>();
                }
                collinear.add(clonedArray[i]);
                //update the previousSlope variable
                previousSlope = clonedArray[i].getSlope(pivot);
            }
            //check at the end of the loop if collinear is >= n-1
            if (collinear.size() >= n - 1) {
                collinear.add(pivot);
                Collections.sort(collinear);
                if (pivot == collinear.get(0)) {
                    result.add(pointListToJSONPointList(collinear));
                }
            }
        }

        return result;
    }


    /**
     * transform a List<Point> to a List<JSONPoint>
     */
    private List<JSONPoint> pointListToJSONPointList(List<Point> pointList) {
        List<JSONPoint> JSONList = new ArrayList<>();
        for (Point point : pointList
                ) {
            JSONPoint jsonPoint = new JSONPoint();
            jsonPoint.setX(point.getX());
            jsonPoint.setY(point.getY());
            JSONList.add(jsonPoint);
        }
        return JSONList;
    }


}
