package com.renfer.test.leaf.factory;

import com.renfer.test.leaf.product.AbstractShapeProduct;
import com.renfer.test.leaf.product.QuadrilateralShape;
import com.renfer.test.leaf.product.TriangleShape;

public class ShapeFactory extends AbstractShapeFactory {
	/**
	 * 三角形
	 */
	private final static int TRIANGLE = 3;
	/**
	 * 四边形
	 */
	private final static int QUADRILATERAL = 4;
	
	/**
	 * 五边形
	 */
	@SuppressWarnings("unused")
	private final static int PENTAGON = 5;
	
	@Override
	public AbstractShapeProduct createShape(int shapeEnumValue,int parentWidth,int parentHeight) {
		AbstractShapeProduct shape = null;
		switch (shapeEnumValue) {
		case TRIANGLE:
			shape = new TriangleShape(parentWidth, parentHeight);
			break;

		case QUADRILATERAL:
			shape = new QuadrilateralShape(parentWidth, parentHeight);
			break;
			
		default:
			shape = new TriangleShape(parentWidth, parentHeight);
			break;
		}
		return shape;
	}

}
