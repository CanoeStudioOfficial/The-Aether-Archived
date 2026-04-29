package com.gildedgames.the_aether.api.accessories;

import baubles.api.BaubleType;

public enum AccessoryType
{
	RING("Ring", 11, 3, BaubleType.RING),
	PENDANT("Pendant", 16, 7, BaubleType.AMULET),
	CAPE("Cape", 15, 5, BaubleType.BODY),
	SHIELD("Shield", 13, 0, BaubleType.TRINKET),
	GLOVE("Gloves", 10, 0, BaubleType.TRINKET),
	MISC("Miscellaneous", 10, 0, BaubleType.CHARM);

	private int maxDamage, damagedReduced;

	private String displayName;

	private BaubleType baubleType;

	AccessoryType(String displayName, int maxDamage, int damageReduced, BaubleType baubleType)
	{
		this.displayName = displayName;
		this.maxDamage = maxDamage;
		this.damagedReduced = damageReduced;
		this.baubleType = baubleType;
	}

	public int getMaxDamage()
	{
		return this.maxDamage;
	}

	public int getDamageReduced()
	{
		return this.damagedReduced;
	}

	public String getDisplayName()
	{
		return this.displayName;
	}

	public BaubleType getBaubleType()
	{
		return this.baubleType;
	}

}