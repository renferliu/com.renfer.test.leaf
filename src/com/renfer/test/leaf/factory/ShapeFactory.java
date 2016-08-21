/**
 * FileName:     ShapeFactory.java 生产形状工厂
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
package com.renfer.test.leaf.factory;

import com.renfer.test.leaf.product.AbstractShapeProduct;
import com.renfer.test.leaf.product.QuadrilateralShape;
import com.renfer.test.leaf.product.TriangleShape;
/**
 * 生产形状工厂
 * 
 * @author renferliu contact:renfer163@163.com
 * 
 * @date 2016-08-17
 * 
 * @version 1.0
 * 
 */
public class ShapeFactory extends AbstractShapeFactory {

	
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
