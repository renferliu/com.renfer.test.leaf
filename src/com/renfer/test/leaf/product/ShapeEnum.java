/**
 * FileName:     ShapeEnum.java 形状枚举类
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

/**
 * 形状枚举类
 * @author renferliu contact:renfer163@163.com
 * @version 2016-07-06
 *
 */
public enum ShapeEnum {


	/**
	 * 三角形
	 */
	TRIANGLE(3),
	
	/**
	 * 四边形
	 */
	QUADRILATERAL(4),
	
	/**
	 * 五边形
	 */
	PENTAGON(5);
	
	private int intValue;
	
	private ShapeEnum(int value) {
	 intValue = value;
	}
	
	/**
	 * 获取形状类型
	 * @return
	 */
	public int getIntValue(){
		return intValue;
	}
}
