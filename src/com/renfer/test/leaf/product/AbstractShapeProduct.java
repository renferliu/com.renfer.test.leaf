/**
 * FileName:     AbstractShapeProduct.java 形状产品父类
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

import java.util.Random;

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;

/**
 * 形状产品父类
 * 
 * @author renferliu contact:renfer163@163.com
 * 
 * @date 2016-08-17
 * 
 * @version 1.0
 * 
 */
public abstract class AbstractShapeProduct {

	@SuppressWarnings("unused")
	private static final String TAG = "AbstractShapeProduct";
	private int color;
	/**
	 * 图形起始点数组
	 */
	protected Point[] startPoints;

	/**
	 * 图形当前各个顶点数组
	 */
	protected Point[] curPoints;
	/**
	 * 贝塞尔曲线终点
	 */
	private Point endPoint;

	/**
	 * 图形起始重心点，即贝塞尔曲线起点
	 */
	protected Point startWeightPoint;

	/**
	 * 图形当前重心点，即贝塞尔曲线当前点
	 */
	protected Point curWeightPoint;
	/**
	 * 贝塞尔第二个控制点
	 */
	private Point bezierC2 = new Point();
	/**
	 * 贝塞尔第一个控制点
	 */
	private Point bezierC1 = new Point();
	/**
	 * 贝塞尔曲线因子
	 */
	private final static float BEZIER_DADIUS = 0.0001f;

	protected Random random = new Random();

	protected Point tmpPoint = new Point();
	private float bezierT = 0.01f;

	private int parentWidth;
	private int parentHeight;

	private Path path;

	/**
	 * 
	 * @param parentWidth
	 *            容器的宽
	 * @param parentHeight容器的高
	 */
	public AbstractShapeProduct(int parentWidth, int parentHeight) {
		path = new Path();

		this.parentWidth = parentWidth;
		this.parentHeight = parentHeight;

		curPoints = new Point[getShape().getIntValue()];
		for (int i = 0; i < curPoints.length; i++) {
			curPoints[i] = new Point();
		}

		startPoints = getShapePoints();
		curWeightPoint = startWeightPoint = getStartWeightPoint();
		endPoint = reValueEndPoint(new Point(parentWidth, parentHeight));
		getBezierContrlPoint1(startWeightPoint, endPoint);
		getBezierContrlPoint2(startWeightPoint, endPoint);
	}

	/**
	 * 获取图形的各顶点
	 * 
	 * @return
	 */
	public abstract Point[] getShapePoints();

	/**
	 * 获取图形的一开始的各顶点
	 * 
	 * @return
	 */
	public Point[] getStartShapePoints() {
		return startPoints;
	}

	/**
	 * 获取图形的一开始的重心点
	 * 
	 * @return
	 */
	public abstract Point getStartWeightPoint();

	/**
	 * 获取图形路径
	 * 
	 * @return
	 */
	public Path getPath() {
		path.reset();
		for (int i = 0; i < curPoints.length; i++) {
			if (i == 0) {
				path.moveTo(curPoints[i].x, curPoints[i].y);
			} else {
				path.lineTo(curPoints[i].x, curPoints[i].y);
			}

		}
		path.close();

		return path;
	}

	/**
	 * 获取颜色
	 * 
	 * @return
	 */
	public int getColor() {
		if (color == 0) {
			String r, g, b;
			r = Integer.toHexString(random.nextInt(256)).toUpperCase();
			g = Integer.toHexString(random.nextInt(256)).toUpperCase();
			b = Integer.toHexString(random.nextInt(256)).toUpperCase();

			r = r.length() == 1 ? "0" + r : r;
			g = g.length() == 1 ? "0" + g : g;
			b = b.length() == 1 ? "0" + b : b;
			color = Color.parseColor("#88" + r + g + b);
		}
		return color;
	}

	/**
	 * 重新打散终点值，飘落终点都在同个地方
	 * 
	 * @param endPoint
	 * @return
	 */
	public Point reValueEndPoint(Point endPoint) {
		endPoint.x = random.nextInt(endPoint.x);
		return endPoint;
	}

	/**
	 * 获取两点的边长
	 * 
	 * @param point1
	 * @param point2
	 * @return
	 */
	public double getSideLength(Point point1, Point point2) {
		int sideX = 0, sideY = 0;
		sideX = Math.abs(point1.x - point2.x);
		sideY = Math.abs(point1.y - point2.y);
		double length = Math.sqrt(sideX * sideX + sideY * sideY);
		return length;
	}

	/**
	 * 形状是否符合
	 * 
	 * @return
	 */
	public boolean isShapeRight(Point[] points) {
		return false;
	}

	/**
	 * 随机生成一个点
	 * 
	 * @return
	 */
	protected Point getRandomPoint() {
		int x = parentWidth - random.nextInt(1 + parentWidth);
		int y = -200 + random.nextInt(100);
		tmpPoint.set(x, y);
		return tmpPoint;
	}

	/**
	 * 获取图形当前重心点（便于计算贝塞尔曲线的各点）
	 * 
	 * @return
	 */
	protected Point getCurWeightPoint() {

		curWeightPoint = getBezierPoint();
		return curWeightPoint;
	}

	/**
	 * 获取图形当前重心点并漂移（便于计算贝塞尔曲线的各点），可使形状达到旋转效果
	 */
	protected Point getDriftCurWeightPoint() {
		Point point = getBezierPoint();
		int driftNum = -5 + random.nextInt(5);
		point.x += driftNum;
		point.y += driftNum;
		curWeightPoint = point;
		return point;
	}

	/**
	 * 随机生成贝塞尔第一个控制点,X范围在起始点和终点之间,Y范围在起始点和中间点之间
	 * 
	 * @param startPoint
	 * @param endPoint
	 * @return
	 */
	private void getBezierContrlPoint1(Point startPoint, Point endPoint) {

		int x = 0;
		if (startWeightPoint.x < endPoint.x) {
			x = startWeightPoint.x
					+ random.nextInt(1 + endPoint.x - startWeightPoint.x);
		} else {
			x = endPoint.x
					+ random.nextInt(1 + startWeightPoint.x - endPoint.x);
		}
		int y = 200 + startPoint.y + random.nextInt((endPoint.y / 2));

		bezierC1.set(x, y);
	}

	/**
	 * 随机生成贝塞尔第二个控制点,X,在起始和终点之间，Y在中间部位和末尾之间
	 * 
	 * @param startPoint
	 * @param endPoint
	 * @return
	 */
	private void getBezierContrlPoint2(Point startPoint, Point endPoint) {
		int x = 0;
		if (bezierC1.x > parentWidth / 2) {
			if (startWeightPoint.x < endPoint.x) {
				x = startWeightPoint.x
						+ random.nextInt(1 + endPoint.x - startWeightPoint.x);
			} else {
				x = endPoint.x
						+ random.nextInt(1 + startWeightPoint.x - endPoint.x);
			}
		} else {
			if (startWeightPoint.x < endPoint.x) {
				x = startWeightPoint.x
						+ random.nextInt(1 + endPoint.x - startWeightPoint.x);
			} else {
				x = endPoint.x
						+ random.nextInt(1 + startWeightPoint.x - endPoint.x);
			}
		}
		int y = endPoint.y / 2 + random.nextInt(1 + (endPoint.y / 2));
		bezierC2.set(x, y);
	}

	/**
	 * 
	 * 获取贝塞尔曲线的点
	 * 
	 * @param startPoint
	 *            起始点
	 * @param endPoint
	 *            终点
	 * @return
	 */
	private Point getBezierPoint() {
		bezierT = BEZIER_DADIUS + bezierT;
		tmpPoint.x = (int) (startWeightPoint.x * Math.pow(1 - bezierT, 3) + 3
				* bezierC1.x * bezierT * Math.pow(1 - bezierT, 2) + 3
				* bezierC2.x * Math.pow(bezierT, 2) * (1 - bezierT) + endPoint.x
				* Math.pow(bezierT, 3));
		tmpPoint.y = (int) (startWeightPoint.y * Math.pow(1 - bezierT, 3) + 3
				* bezierC1.y * bezierT * Math.pow(1 - bezierT, 2) + 3
				* bezierC2.y * Math.pow(bezierT, 2) * (1 - bezierT) + endPoint.y
				* Math.pow(bezierT, 3));
		return tmpPoint;
	}

	/**
	 * 图形根据贝塞尔曲线运动轨迹获取刷新当前点
	 * 
	 */
	public abstract void invalidatePoint();

	/**
	 * 获取最新位置坐标
	 * 
	 * @return
	 */
	protected abstract Point[] refreshCurPoints();

	/**
	 * 获取形状
	 * 
	 * @return
	 */
	protected abstract ShapeEnum getShape();

	/**
	 * 当前形状是否在最底部
	 * 
	 * @return
	 */
	public boolean isFinish() {

		int disappearY = parentHeight / 2
				+ random.nextInt(1 + parentHeight / 2);
		for (Point point : curPoints) {
			if (point.y >= disappearY)
				return true;
		}
		return false;
	}

	/**
	 * 将图形位置重置
	 */
	public void reset() {
		startPoints = getShapePoints();
		bezierT = 0.01f;
	}
}
