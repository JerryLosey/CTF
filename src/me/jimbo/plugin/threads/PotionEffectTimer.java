package me.jimbo.plugin.threads;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.jimbo.plugin.CTF;

public class PotionEffectTimer implements Runnable {
	
	private CTF plugin;

	public PotionEffectTimer(CTF plugin){
		this.plugin = plugin;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (Player p : this.plugin.getServer().getOnlinePlayers()){
			// Do something
			if(p.hasPermission("ctf.class.berserker")){
				p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 6000, 1) );
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 1) );
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 6000, 1) );
			}else{
				for(PotionEffect effect : p.getActivePotionEffects()){
					p.removePotionEffect(effect.getType());
				}
			}
		}
		
	}

}
