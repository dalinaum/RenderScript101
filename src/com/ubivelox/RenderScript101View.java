package com.ubivelox;

//import java.util.Random;
import android.content.Context;
import android.graphics.BitmapFactory;
//import android.renderscript.Float4;
import android.renderscript.RSSurfaceView;
import android.renderscript.RenderScriptGL;
import android.view.MotionEvent;

public class RenderScript101View extends RSSurfaceView {
    private Context context;
    private RenderScript101RS script;
    private RenderScriptGL rs;

    public RenderScript101View(Context context) {
        super(context);
        this.context = context;
        ensureRenderScript();
    }

    private void ensureRenderScript() {
        if (rs == null) {
            final RenderScriptGL.SurfaceConfig sc =
                    new RenderScriptGL.SurfaceConfig();
            rs = createRenderScriptGL(sc);
        }
        if (script == null) {
            script =
                    new RenderScript101RS(rs, context.getResources(),
                            R.raw.renderscript101);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        if (script == null) {
            return true;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            final Random random = new Random();
//            final Float4 newColor =
//                    new Float4(random.nextFloat(), random.nextFloat(),
//                            random.nextFloat(), 1.0f);
//            script.setBackgroundColor(newColor);
            script.setBackgroundBitmap(BitmapFactory.decodeResource(
                    getResources(), R.drawable.ic_launcher));
        }

        return true;
    }
}