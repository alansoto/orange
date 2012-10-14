package com.qut.spc.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * @see Professional Android 2 Application Development, chap 14
 */
public class CompassView extends View {

	private Paint markerPaint;
	private Paint textPaint;
	private Paint textPaintBold;
	private Paint circlePaint;
	private String northString;
	private String eastString;
	private String southString;
	private String westString;
	private int textHeight;

	private RectF rollOval = new RectF();
	private RectF pitchOval = new RectF();
	
	private float bearing;
	float pitch = 0;
	float roll = 0;

	public void setBearing(float _bearing) {
		bearing = _bearing;
	}

	public float getBearing() {
		return bearing;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getRoll() {
		return roll;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}

	public CompassView(Context context) {
		super(context);
		initCompassView();
	}

	public CompassView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initCompassView();
	}

	public CompassView(Context context, AttributeSet ats, int defaultStyle) {
		super(context, ats, defaultStyle);
		initCompassView();
	}

	protected void initCompassView() {
		setFocusable(true);
		
		int bgColor = Color.rgb(255, 160, 0);
		int fgColor = Color.BLACK;

		circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		circlePaint.setColor(bgColor);
		circlePaint.setStrokeWidth(1);
		circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);

		northString = "N";
		eastString = "E";
		southString = "S";
		westString = "W";

		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setColor(fgColor);
		
		textPaintBold = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaintBold.setColor(fgColor);
		Typeface bold = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
		textPaintBold.setTypeface(bold);

		textHeight = (int) textPaintBold.measureText("yY");
		
		markerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		markerPaint.setColor(fgColor);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// The compass is a circle that fills as much space as possible.
		// Set the measured dimensions by figuring out the shortest boundary,
		// height or width.
		int measuredWidth = measure(widthMeasureSpec);
		int measuredHeight = measure(heightMeasureSpec);

		int d = Math.min(measuredWidth, measuredHeight);

		setMeasuredDimension(d, d);
	}

	private int measure(int measureSpec) {
		int result = 0;

		// Decode the measurement specifications.
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.UNSPECIFIED) {
			// Return a default size of 200 if no bounds are specified.
			result = 200;
		} else {
			// As you want to fill the available space
			// always return the full available bounds.
			result = specSize;
		}
		return result;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int mMeasuredWidth = getMeasuredWidth();
		int mMeasuredHeight = getMeasuredWidth();

		int px = getMeasuredWidth() / 2;
		int py = getMeasuredHeight() / 2;

		int radius = Math.min(px, py);
		// Draw the background
		canvas.drawCircle(px, py, radius, circlePaint);
		// Rotate our perspective so that the "top" is
		// facing the current bearing.
		canvas.save();
		canvas.rotate(-bearing, px, py);
		int textWidth = (int) textPaint.measureText("W");
		int cardinalX = px - textWidth / 2;
		int cardinalY = py - radius + textHeight;

		// Draw the marker every 15 degrees and text every 45.
		for (int i = 0; i < 24; i++) {
			// Draw a marker.
			canvas.drawLine(px, py - radius, px, py - radius + 10, markerPaint);

			canvas.save();
			canvas.translate(0, textHeight);

			// Draw the cardinal points
			if (i % 6 == 0) {
				String dirString = "";
				switch (i) {
				case (0): {
					dirString = northString;
					int arrowY = 2 * textHeight;
					canvas.drawLine(px, arrowY, px - 5, 3 * textHeight,
							markerPaint);
					canvas.drawLine(px, arrowY, px + 5, 3 * textHeight,
							markerPaint);
					break;
				}
				case (6):
					dirString = eastString;
					break;
				case (12):
					dirString = southString;
					break;
				case (18):
					dirString = westString;
					break;
				}
				canvas.drawText(dirString, cardinalX, cardinalY, textPaintBold);
			}

			else if (i % 3 == 0) {
				// Draw the text every alternate 45deg
				String angle = String.valueOf(i * 15);
				float angleTextWidth = textPaint.measureText(angle);

				int angleTextX = (int) (px - angleTextWidth / 2);
				int angleTextY = py - radius + textHeight;
				canvas.drawText(angle, angleTextX, angleTextY, textPaint);
			}
			canvas.restore();

			canvas.rotate(15, px, py);
		}
		canvas.restore();

		rollOval.set((mMeasuredWidth / 3) - mMeasuredWidth / 7,
				(mMeasuredHeight / 2) - mMeasuredWidth / 7,
				(mMeasuredWidth / 3) + mMeasuredWidth / 7,
				(mMeasuredHeight / 2) + mMeasuredWidth / 7);
		markerPaint.setStyle(Paint.Style.STROKE);
		canvas.drawOval(rollOval, markerPaint);
		markerPaint.setStyle(Paint.Style.FILL);
		canvas.save();
		canvas.rotate(roll, mMeasuredWidth / 3, mMeasuredHeight / 2);
		canvas.drawArc(rollOval, 0, 180, false, markerPaint);
		String degree = String.format("%.1f", roll);
		canvas.drawText(degree, (mMeasuredWidth / 3) - mMeasuredWidth / 21,
				(mMeasuredHeight / 2) + mMeasuredWidth / 7 + textHeight, textPaint);

		canvas.restore();
		pitchOval.set((2 * mMeasuredWidth / 3) - mMeasuredWidth
				/ 7, (mMeasuredHeight / 2) - mMeasuredWidth / 7,
				(2 * mMeasuredWidth / 3) + mMeasuredWidth / 7,
				(mMeasuredHeight / 2) + mMeasuredWidth / 7);
		markerPaint.setStyle(Paint.Style.STROKE);
		canvas.drawOval(pitchOval, markerPaint);
		markerPaint.setStyle(Paint.Style.FILL);
		canvas.drawArc(pitchOval, 0 - pitch / 2, 180 + (pitch), false,
				markerPaint);
		degree = String.format("%.1f", pitch);
		canvas.drawText(degree, (2 * mMeasuredWidth / 3) - mMeasuredWidth / 21,
				(mMeasuredHeight / 2) + mMeasuredWidth / 7 + textHeight, textPaint);
		
		markerPaint.setStyle(Paint.Style.STROKE);
	}
}
