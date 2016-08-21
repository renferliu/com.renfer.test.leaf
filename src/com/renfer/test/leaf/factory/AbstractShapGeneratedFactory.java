package com.renfer.test.leaf.factory;

import com.renfer.test.leaf.product.AbstractShapeProduct;
/**
 * 抽象形状创建工厂
 * @author renferliu contact:renfer163@163.com
 * @version 2016-07-06
 */
public abstract class AbstractShapGeneratedFactory {
	
	/**
	 * 生产形状
	 * @param c 类型
	 * @return
	 */
	public abstract <T extends AbstractShapeProduct> T createShape(Class <T> c);

}
