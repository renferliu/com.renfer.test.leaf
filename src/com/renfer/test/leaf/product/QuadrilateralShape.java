/**
 * FileName:     TriangleShape.java 
 * All rights Reserved, Designed By renferliu
 * Copyright:    Copyright(C) 2016
 * Company       
 * @author:      renferliu
 * @version      V1.0 
 * Createdate:   2016-08-20
 *
 * Modification  History:
 * Date          Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 
 * Why & What is modified: 
 */
package com.renfer.test.leaf.product;

import android.graphics.Point;


/**
 * 四边形
 * 
 * @author renferliu contact:renfer163@163.com
 * 
 * @date 2016-08-17
 * 
 * @version 1.0
 */
public class QuadrilateralShape extends AbstractShapeProduct {

	/**
	 * 四边形
	 * 
	 * @param parentWidth
	 *            容器的宽
	 * @param parentHeight
	 *            容器的高
	 */
	public QuadrilateralShape(int parentWidth, int parentHeight) {
		super(parentWidth, parentHeight);
	}

	@Override
	public Point[] getShapePoints() {
		Point point = getRandomPoint();

		int step = 1 + random.nextInt(8);
		int nextStep = 1 + random.nextInt(10);
		curPoints[0].set(point.x - step - nextStep, point.y - step - nextStep);
		curPoints[1].set(point.x + step, point.y - step);
		curPoints[2].set(point.x + step+nextStep, point.y + step + nextStep );
		curPoints[3].set(point.x - step, point.y + step);
		return curPoints;
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
	public Point getStartWeightPoint() {
		if (curPoints.length != 4) {
			return null;
		}
		if (startWeightPoint == null) {
			startWeightPoint = new Point();
			startWeightPoint.x = ((startPoints[0].x + startPoints[1].x + startPoints[2].x + startPoints[3].x) / 3);
			startWeightPoint.y = ((startPoints[0].y + startPoints[1].y + startPoints[2].y + startPoints[3].y) / 3);
		}
		return startWeightPoint;
	}

	@Override
	public boolean isShapeRight(Point[] points) {
		return false;
	}

	@Override
	public void invalidatePoint() {
		refreshCurPoints();
	}


	@Override
	protected ShapeEnum getShape() {
		return ShapeEnum.QUADRILATERAL;
	}
}
