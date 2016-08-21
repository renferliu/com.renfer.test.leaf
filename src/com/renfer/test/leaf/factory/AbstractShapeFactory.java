/**
 * FileName:     ShapeFactory.java 抽象的生产形状工厂
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
/**
 * 抽象的形状产品父类
 * 
 * @author renferliu contact:renfer163@163.com
 * 
 * @date 2016-08-17
 * 
 * @version 1.0
 * 
 */
public abstract class AbstractShapeFactory {
	
	/**
	 * 三角形
	 */
	protected final static int TRIANGLE = 3;
	/**
	 * 四边形
	 */
	protected final static int QUADRILATERAL = 4;
	
	/**
	 * 五边形
	 */
	protected final static int PENTAGON = 5;
	
	/**
	 *  生产形状
	 * @param shapeEnumValue
	 * @return
	 */
	public abstract  AbstractShapeProduct createShape(int shapeEnumValue,int parentWidth,int parentHeight);
}
