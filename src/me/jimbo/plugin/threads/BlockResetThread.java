package me.jimbo.plugin.threads;

import org.bukkit.block.Block;

public class BlockResetThread implements Runnable {

	private Block b;

	public BlockResetThread(Block block){
		this.b = block;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.b.setTypeId(0);
	}

}
