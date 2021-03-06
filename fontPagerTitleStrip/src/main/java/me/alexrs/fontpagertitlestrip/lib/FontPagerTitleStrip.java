package me.alexrs.fontpagertitlestrip.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v4.view.PagerTitleStrip;
import android.util.AttributeSet;
import android.widget.TextView;

public class FontPagerTitleStrip extends PagerTitleStrip {

    private Typeface mTypeface;

    private String fontFamily = "sans-serif";

    public FontPagerTitleStrip(Context context) {
        super(context);
    }

    public FontPagerTitleStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FontPagerTitleStrip);

            int textStyle = a.getInt(R.styleable.FontPagerTitleStrip_textStyle, 0);
            if (a.hasValue(R.styleable.FontPagerTitleStrip_fontFamily))
                fontFamily = a.getString(R.styleable.FontPagerTitleStrip_fontFamily);

            setTypefaceByFontFamily(fontFamily, textStyle);

            a.recycle();
        }
    }

    /**
     * @param fontFamily the font family
     */
    public void setTypefaceByFontFamily(String fontFamily) {
        mTypeface = Typefaces.get(fontFamily);
    }

    /**
     * @param fontFamily the font family
     * @param textStyle  the text style (BOLD, ITALIC...)
     */
    public void setTypefaceByFontFamily(String fontFamily, int textStyle) {
        mTypeface = Typefaces.get(fontFamily, textStyle);
    }

    /**
     * @param typeface your Typeface
     * @param tag      a tag for the cache
     */
    public void setTypeface(Typeface typeface, String tag) {
        mTypeface = Typefaces.get(typeface, tag);
    }

    /**
     * @param assetPath the path where your font is located
     */
    public void setTypefaceFromAssets(String assetPath) {
        mTypeface = Typefaces.get(getContext(), assetPath);
    }


    public Typeface getTypeface() {
        return mTypeface;
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (mTypeface != null) {
            for (int i = 0; i < this.getChildCount(); i++) {
                if (this.getChildAt(i) instanceof TextView) {
                    ((TextView) this.getChildAt(i)).setTypeface(mTypeface);
                }
            }
        }
    }
}