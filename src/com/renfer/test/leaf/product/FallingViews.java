/**
 * FileName:     FallingViews.java 满屏飘形状仿落叶效果的view，铺满屏幕
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

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.renfer.test.leaf.factory.ShapeFactory;

/**
 * 满屏飘形状仿落叶效果的view，铺满屏幕
 * 
 * @author renferliu contact:renfer163@163.com
 * 
 * @date 2016-08-17
 * 
 * @version 1.0
 */
public class FallingViews extends View {
	protected Random random = new Random();

	private static final long ANIMATION_DELY = 50;

	private int rRange;
	private int gRange;
	private int bRange;
	@SuppressWarnings("unused")
	private boolean isSettingRange;
	/**
	 * 画笔
	 */
	private Paint paint;
	/**
	 * 画笔颜色，默认绿色
	 */
	private int paintColor = Color.GREEN;

	/**
	 * 默认的画笔宽度
	 */
	private final int DEFAULT_STROKE_WIDTH = 3;
	
	private int screenW;

	private int screenH;
	/**
	 * 画笔路径
	 */
	private Path path;

	/**
	 * 生产各种形状的工厂
	 */
	private ShapeFactory shapeFactory;

	/**
	 * 形状数组
	 */
	private AbstractShapeProduct shapeProducts[];

	/**
	 * 用于播放动画的handler
	 */
	private Handler animationHandler = new Handler(getContext().getMainLooper());

	/**
	 * 用于播放动画的runnable
	 */
	private Runnable animationRunnable = new Runnable() {

		@Override
		public void run() {
			for (int i = 0; i < shapeProducts.length; i++) {
				shapeProducts[i].invalidatePoint();
				if (shapeProducts[i].isFinish()) {
					shapeProducts[i].reset();
				}
			}
			invalidate();
		}
	};



	public FallingViews(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public FallingViews(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FallingViews(Context context) {
		this(context, null);
		init();
	}

	/**
	 * 初始化工具
	 */
	private void init() {
		getScreenWH();
		gnenerateMutiShape(100);
		paint = new Paint();
		/* 去锯齿 */
		paint.setAntiAlias(true);
		/* 设置paint的　style　为FILL：填满 */
		paint.setStyle(Paint.Style.FILL);
		/* 设置paint的外框宽度 */
		paint.setStrokeWidth(DEFAULT_STROKE_WIDTH);
		
	}

	/**
	 * 随机生成多个形状
	 * 
	 * @param num
	 *            数目
	 */
	private void gnenerateMutiShape(int num) {

		shapeFactory = new ShapeFactory();
		shapeProducts = new AbstractShapeProduct[num];
		for (int i = 0; i < shapeProducts.length; i++) {
			shapeProducts[i] = shapeFactory.createShape(getRandomShape()
					.getIntValue(), screenW, screenH);
		}
	}

	@SuppressWarnings("deprecation")
	private  void getScreenWH() {
		Display display = ((WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE)).getDefaultDisplay();
		final Point point = new Point();
		try {
			display.getSize(point);
		} catch (java.lang.NoSuchMethodError ignore) { // Older device
			point.x = display.getWidth();
			point.y = display.getHeight();
		}
		 screenW = point.x;
		 screenH = point.y;
	}

	/**
	 * 根据随机数产生形状
	 * 
	 * @return
	 */
	private ShapeEnum getRandomShape() {
		Random random = new Random();
		int randomNum = 1 + random.nextInt(99);
		if (randomNum % 5 == 0) {
			return ShapeEnum.TRIANGLE;
		} else {
			return ShapeEnum.QUADRILATERAL;
		}
	}

	/**
	 * 启动动画重复执行
	 */
	private void playAnimation() {

		animationHandler.postDelayed(animationRunnable, ANIMATION_DELY);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		for (int i = 0; i < shapeProducts.length; i++) {
			path = shapeProducts[i].getPath();
			paintColor = shapeProducts[i].getColor();

			if (path != null) {
				paint.setColor(paintColor);
				canvas.drawPath(path, paint);
			}
		}
		playAnimation();
	}

	/**
	 * 设置范围
	 * @param rRange
	 * @param gRange
	 * @param bRange
	 */
	public void settingColor(int rRange, int gRange, int bRange) {
		isSettingRange = true;
		this.rRange = rRange;
		this.gRange = gRange;
		this.bRange = bRange;
	}

	/**
	 * 获取颜色
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private void getColor() {
		String r, g, b;
		r = Integer.toHexString(rRange + random.nextInt(256 - rRange))
				.toUpperCase();
		g = Integer.toHexString(gRange + random.nextInt(256 - gRange))
				.toUpperCase();
		b = Integer.toHexString(bRange + random.nextInt(256 - bRange))
				.toUpperCase();

		r = r.length() == 1 ? "0" + r : r;
		g = g.length() == 1 ? "0" + g : g;
		b = b.length() == 1 ? "0" + b : b;
		paintColor = Color.parseColor("#EE" + r + g + b);
		return;
	}

}
