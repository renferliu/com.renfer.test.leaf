package com.renfer.test.leaf.product;

import android.graphics.Point;

/**
 * 三角形
 * 
 * @author renferliu contact:renfer163@163.com
 * 
 */
public class TriangleShape extends AbstractShapeProduct {
	
	public TriangleShape(int parentWidth, int parentHeight) {
		super(parentWidth, parentHeight);

	}

	@Override
	public Point[] getShapePoints() {
		Point point = getRandomPoint();
		int step = 5+random.nextInt(20);
		int nextStep = 1+random.nextInt(3);
		curPoints[0].set(point.x - step, point.y);
		curPoints[1].set(point.x + step, point.y - step/nextStep);
		curPoints[2].set(point.x,point.y - step);
		return curPoints;
	}
	
	


	@Override
	public Point getStartWeightPoint() {
		if (curPoints.length != 3) {
			return null;
		}
		if (startWeightPoint == null) {
			startWeightPoint = new Point();
			startWeightPoint.x = ((startPoints[0].x + startPoints[1].x + startPoints[2].x) / 3);
			startWeightPoint.y = ((startPoints[0].y + startPoints[1].y + startPoints[2].y) / 3);
		}
		return startWeightPoint;
	}

	@Override
	public Point[] refreshCurPoints() {
		Point relativePoint = getDriftCurWeightPoint();
		int relativeX = relativePoint.x - getStartWeightPoint().x;
		int relativeY = relativePoint.y - getStartWeightPoint().y;
		if (curPoints == null) {
			return null;
		}
		for (int i = 0; i < curPoints.length; i++) {
			curPoints[i].x += relativeX;
			curPoints[i].y += relativeY;
		}
		return curPoints;
	}



	@Override
	public boolean isShapeRight(Point[] points) {
		if (points.length != 3) {
			return false;
		}
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null)
				return false;
		}
		double lineOne = getSideLength(points[0], points[1]);
		double lineTwo = getSideLength(points[1], points[2]);
		double lineThree = getSideLength(points[0], points[2]);
		if (lineOne + lineTwo <= lineThree) {
			return false;
		} else if (lineOne + lineThree <= lineTwo) {
			return false;
		} else if (lineTwo + lineThree <= lineOne) {
			return false;
		} else if (lineOne - lineTwo >= lineThree) {
			return false;
		} else if (lineOne - lineThree >= lineTwo) {
			return false;
		} else if (lineTwo - lineThree >= lineOne) {
			return false;
		} else {
			return true;
		}
	}



	@Override
	public void invalidatePoint() {
		refreshCurPoints();
	}

	@Override
	protected ShapeEnum getShape() {
		return ShapeEnum.TRIANGLE;
	}


}
