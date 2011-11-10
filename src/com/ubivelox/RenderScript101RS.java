package com.ubivelox;

import android.content.res.Resources;
import android.renderscript.RenderScriptGL;

public class RenderScript101RS {
    public RenderScript101RS(RenderScriptGL rs, Resources res, int resId) {
        ScriptC_RenderScript101 script = new ScriptC_RenderScript101(rs,
                res, resId);
        rs.bindRootScript(script);
    }
}