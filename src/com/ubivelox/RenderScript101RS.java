package com.ubivelox;

import android.content.res.Resources;
import android.renderscript.Float4;
import android.renderscript.RenderScriptGL;

public class RenderScript101RS {
    private ScriptC_RenderScript101 script;

    public RenderScript101RS(RenderScriptGL rs, Resources res, int resId) {
        script = new ScriptC_RenderScript101(rs, res, resId);
        rs.bindRootScript(script);
    }

    public void setBackgroundColor(Float4 color) {
        script.set_bgColor(color);
    }   
}