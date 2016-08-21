package com.renfer.test.leaf.factory;

import com.renfer.test.leaf.product.AbstractShapeProduct;

public abstract class AbstractShapeFactory {
	
	/**
	 *  生产形状
	 * @param shapeEnumValue
	 * @return
	 */
	public abstract  AbstractShapeProduct createShape(int shapeEnumValue,int parentWidth,int parentHeight);
}
