package alephinfinity1.forgeblock.client;

import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

import org.apache.logging.log4j.Level;

import alephinfinity1.forgeblock.ForgeBlock;
import net.minecraft.client.particle.CritParticle;
import net.minecraft.client.particle.EmitterParticle;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ParticleRenderHandler {

	/*
	 * A wonky workaround to damage_indicator particles creating lag spikes when dealing large amounts of damage.
	 */
	@SubscribeEvent
	public static void onParticleRender(TickEvent.RenderTickEvent event) {
		try {
			Map<IParticleRenderType, Queue<Particle>> particles = ForgeBlock.MINECRAFT.particles.byType;
			Queue<EmitterParticle> emitters = ForgeBlock.MINECRAFT.particles.particleEmitters;
			for(Map.Entry<IParticleRenderType, Queue<Particle>> entry : particles.entrySet()) {
				Queue<Particle> particlesList = entry.getValue();
				Iterator<Particle> iter = particlesList.iterator();
				while(iter.hasNext()) {
					Particle particle = iter.next();
					if(particle instanceof CritParticle && particle.getMaxAge() == 20) { //If the particle is a damage indicator particle. getMaxAge() == 20 to prevent crit and magic particles from being accidentally targetted.
						iter.remove();
					}
				}
			}
			for(EmitterParticle emitter : emitters) {
				if(emitter.particleTypes.getType().equals(ParticleTypes.DAMAGE_INDICATOR)) {
					emitters.remove(emitter);
				}
			}
		} catch(NullPointerException e) {
			ForgeBlock.LOGGER.log(Level.WARN, "Caught NullPointerException: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
