#pragma version(1)
#pragma rs java_package_name(com.ubivelox);

#include "rs_graphics.rsh"

float4 bgColor;

static void drawBackground() {
	rsgClearColor(bgColor.x, bgColor.y, bgColor.z, bgColor.w);
}

void init() {
	bgColor = (float4) { 0.0f, 0.0f, 1.0f, 1.0f };
	rsDebug("Called init", rsUptimeMillis());
}

int root() {
	drawBackground();
	return 16;
}