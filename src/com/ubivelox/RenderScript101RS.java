package com.ubivelox;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Float4;
import android.renderscript.Matrix4f;
import android.renderscript.ProgramFragmentFixedFunction;
import android.renderscript.ProgramStore;
import android.renderscript.ProgramVertexFixedFunction;
import android.renderscript.RenderScriptGL;
import android.renderscript.Sampler;

public class RenderScript101RS {
    private ScriptC_RenderScript101 script;
    private ProgramStore programStoreBlendNone;
    private RenderScriptGL renderScriptGL;
    private Sampler linearClamp;
    private ProgramFragmentFixedFunction singleTextureFragmentProcess;
    private ProgramVertexFixedFunction programVertex;

    public RenderScript101RS(RenderScriptGL rs, Resources res, int resId) {
        renderScriptGL = rs;
        script = new ScriptC_RenderScript101(rs, res, resId);
        initProgramStore();
        initSampler();
        initProgramFragment();
        initProgramVertex();
        rs.bindRootScript(script);
    }

    private void initProgramVertex() {
        ProgramVertexFixedFunction.Builder pvBuilder =
                new ProgramVertexFixedFunction.Builder(renderScriptGL);
        programVertex = pvBuilder.create();
        ProgramVertexFixedFunction.Constants pvConstants =
                new ProgramVertexFixedFunction.Constants(renderScriptGL);
        programVertex.bindConstants(pvConstants);
        
        Matrix4f projection = new Matrix4f();
        projection.loadProjectionNormalized(1, 1);
        pvConstants.setProjection(projection);
        script.set_programVertex(programVertex);
    }

    private void initProgramFragment() {
        final ProgramFragmentFixedFunction.Builder pfBuilder =
                new ProgramFragmentFixedFunction.Builder(renderScriptGL);
        
        pfBuilder.setTexture(
                ProgramFragmentFixedFunction.Builder.EnvMode.REPLACE,
                ProgramFragmentFixedFunction.Builder.Format.RGBA, 0);
        singleTextureFragmentProcess = pfBuilder.create();
        script.set_singleTextureFragmentProgram(singleTextureFragmentProcess);
    }

    private void initSampler() {
        linearClamp = Sampler.CLAMP_LINEAR(renderScriptGL);
        script.set_linearClamp(linearClamp);
    }

    private void initProgramStore() {
        programStoreBlendNone =
                ProgramStore.BLEND_NONE_DEPTH_NONE(renderScriptGL);
        script.set_programStoreBlendNone(programStoreBlendNone);
    }

    public void setBackgroundColor(Float4 color) {
        script.set_bgColor(color);
    }

    public void setBackgroundBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        final Allocation bitmapAllocation =
                Allocation.createFromBitmap(renderScriptGL, bitmap,
                        Allocation.MipmapControl.MIPMAP_NONE,
                        Allocation.USAGE_GRAPHICS_TEXTURE);
        script.set_bgImage(bitmapAllocation);
    }
}