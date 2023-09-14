package com.joey736n.glowing.util;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ParticleUtil {
    public static void generateConcentricRings(Vec3 position, Level level, float radius, float radiusIncrement, float maxRadius, int nodes, int nodeIncrement, ParticleOptions particle) {
        if (radius > maxRadius) { return; }
        generateParticleRing(position, level, radius, nodes, particle);
        // Delay Here
        generateConcentricRings(position, level, radius + radiusIncrement, radiusIncrement, maxRadius, nodes + nodeIncrement, nodeIncrement, particle);
    }

    public static void generateParticleRing(Vec3 position, Level level, float radius, int nodes, ParticleOptions particle) {
        double directionIncrement = (2*Math.PI)/nodes;
        double x;
        double z;
        double originY = position.y();
        for (int i = 0; i < nodes; i++) {
            x = Math.cos(directionIncrement * i) * radius + position.x();
            z = Math.sin(directionIncrement * i) * radius + position.z();
            level.addParticle(particle, x, originY, z, 0.0, 0.0, 0.0);
        }
    }
}
