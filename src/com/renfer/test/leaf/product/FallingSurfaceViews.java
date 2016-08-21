package com.renfer.test.leaf.product;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import com.renfer.test.leaf.factory.ShapeFactory;

/**
 * 满屏飘形状仿落叶效果的view，铺满屏幕
 * 
 * @author renferliu contact
 * 
 */
public class FallingSurfaceViews extends SurfaceView implements
		SurfaceHolder.Callback, Runnable {

	private static final long ANIMATION_DELY = 50;

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

	private boolean isRunning = true;

	public FallingSurfaceViews(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public FallingSurfaceViews(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FallingSurfaceViews(Context context) {
		this(context, null);
		init();
	}

	/**
	 * 初始化工具
	 */
	private void init() {
		this.getHolder().addCallback(this);

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
		Display display = ((WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE)).getDefaultDisplay();
		Point point = getDisplaySize(display);
		shapeFactory = new ShapeFactory();
		shapeProducts = new AbstractShapeProduct[num];
		for (int i = 0; i < shapeProducts.length; i++) {
			shapeProducts[i] = shapeFactory.createShape(getRandomShape()
					.getIntValue(), point.x, point.y);
		}
	}

	private static Point getDisplaySize(final Display display) {
		final Point point = new Point();
		try {
			display.getSize(point);
		} catch (java.lang.NoSuchMethodError ignore) { // Older device
			point.x = display.getWidth();
			point.y = display.getHeight();
		}
		return point;
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

	// @Override
	// protected void onDraw(Canvas canvas) {
	//
	// super.onDraw(canvas);
	// for (int i = 0; i < shapeProducts.length; i++) {
	// path = shapeProducts[i].getPath();
	// paintColor = shapeProducts[i].getColor();
	//
	// if (path != null) {
	// paint.setColor(paintColor);
	// canvas.drawPath(path, paint);
	// }
	// }
	// playAnimation();
	// }

	@Override
	public void run() {
		while (isRunning) {
			
		for (int i = 0; i < shapeProducts.length; i++) {
			shapeProducts[i].invalidatePoint();
			if (shapeProducts[i].isFinish()) {
				shapeProducts[i].reset();
			}
		}
		
			Canvas canvas = this.getHolder().lockCanvas();// 获取画布
			
			Paint paint = new Paint();    
			paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));    
			canvas.drawPaint(paint);    
			paint.setXfermode(new PorterDuffXfermode(Mode.SRC));  
			canvas.drawColor(Color.WHITE);
			for (int i = 0; i < shapeProducts.length; i++) {
				path = shapeProducts[i].getPath();
				paintColor = shapeProducts[i].getColor();

				if (path != null) {
					paint.setColor(paintColor);
					canvas.drawPath(path, paint);
				}
			}
			this.getHolder().unlockCanvasAndPost(canvas);// 解锁画布，提交画好的图像
			try {
				Thread.sleep(50);

			} catch (Exception e) {
			}
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		isRunning = true;
		new Thread(this).start();
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		isRunning = false;

	}

}
