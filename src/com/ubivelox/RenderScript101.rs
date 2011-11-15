#pragma version(1)
#pragma rs java_package_name(com.ubivelox);

#include "rs_graphics.rsh"

float4 bgColor;
rs_allocation bgImage;
rs_program_fragment singleTextureFragmentProgram;
rs_program_vertex programVertex;
rs_program_store programStoreBlendNone;
rs_sampler linearClamp;

static const float3 bgVertices[4] = { { -1.0, -1.0, -1.0 }, { 1.0, -1.0,
	-1.0 }, { 1.0, 1.0, -1.0 }, { -1.0, 1.0, -1.0 } };

static void drawBackground() {
    if (bgImage.p != 0) {
	rs_matrix4x4 projection, model;
	rsMatrixLoadOrtho(&projection, -1.0f, 1.0f, -1.0f, 1.0f, 0.0f,
		1.0f);
	rsgProgramVertexLoadProjectionMatrix(&projection);

	rsMatrixLoadIdentity(&model);
	rsgProgramVertexLoadModelMatrix(&model);

	rsgBindTexture(singleTextureFragmentProgram, 0, bgImage);

	rsgDrawQuad(bgVertices[0].x, bgVertices[0].y, bgVertices[0].z,
		bgVertices[1].x, bgVertices[1].y, bgVertices[1].z,
		bgVertices[2].x, bgVertices[2].y, bgVertices[2].z,
		bgVertices[3].x, bgVertices[3].y, bgVertices[3].z);
    } else {
	rsgClearColor(bgColor.x, bgColor.y, bgColor.z, bgColor.w);
    }
}

void init() {
    bgColor = (float4) {0.0f, 0.0f, 1.0f, 1.0f};
	    rsDebug("Called init", rsUptimeMillis());
	} // this indentation is a bug of a CDT. How stupid it is.

int root() {
    rsgBindProgramVertex(programVertex);
    rsgBindProgramFragment(singleTextureFragmentProgram);
    rsgBindProgramStore(programStoreBlendNone);

    drawBackground();
    return 16;
}
