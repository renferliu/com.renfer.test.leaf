package com.renfer.test.leaf.factory;

import android.util.Log;

import com.renfer.test.leaf.product.AbstractShapeProduct;

/**
 * 生成各种形状的工厂
 * @author renferliu contact:renfer163@163.com
 * @version 2016-07-06
 */
public class ShapGeneratedFactory extends AbstractShapGeneratedFactory{

	private static final String TAG = "ShapGeneratedFactory";

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractShapeProduct> T createShape(Class<T> c) {
		AbstractShapeProduct shape = null;
		try {
			shape = (T) Class.forName(c.getName()).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			Log.e(TAG, "形状生成错误！");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			Log.e(TAG, "形状生成错误！");
		} catch (ClassNotFoundException e) {
			Log.e(TAG, "形状生成错误！");
			e.printStackTrace();
		}
		return (T) shape;
	}

}
