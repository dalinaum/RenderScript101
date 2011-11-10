package com.ubivelox;

import android.content.Context;
import android.renderscript.RSSurfaceView;
import android.renderscript.RenderScriptGL;

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
}