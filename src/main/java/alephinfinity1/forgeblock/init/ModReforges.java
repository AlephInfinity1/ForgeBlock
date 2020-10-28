package alephinfinity1.forgeblock.init;

import alephinfinity1.forgeblock.ForgeBlock;
import alephinfinity1.forgeblock.attribute.ModifierHelper;
import alephinfinity1.forgeblock.misc.FBItemType;
import alephinfinity1.forgeblock.misc.reforge.Reforge;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class ModReforges {
	
	public static final DeferredRegister<Reforge> REFORGES = DeferredRegister.create(Reforge.class, ForgeBlock.MOD_ID);
	
	public static final RegistryObject<Reforge> GENTLE = REFORGES.register("gentle", () -> new Reforge(new FBItemType[] {FBItemType.SWORD}, false, 
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 3, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, uuid), 
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 5, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, uuid), 
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 7, 0, 0, 15, 0, 0, 0, 0, 0, 0, 0, 0, uuid), 
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 10, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, uuid), 
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 15, 0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 0, uuid), 
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 20, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 25, 0, 0, 35, 0, 0, 0, 0, 0, 0, 0, 0, uuid)));
	
	public static final RegistryObject<Reforge> ODD = REFORGES.register("odd", () -> new Reforge(new FBItemType[] {FBItemType.SWORD}, false, 
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 12, 10, 0, 0, 0, 0, 0, -5, 0, 0, 0, uuid), 
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 15, 15, 0, 0, 0, 0, 0, -10, 0, 0, 0, uuid), 
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 15, 15, 0, 0, 0, 0, 0, -18, 0, 0, 0, uuid), 
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 25, 30, 0, 0, 0, 0, 0, -24, 0, 0, 0, uuid), 
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 25, 30, 0, 0, 0, 0, 0, -50, 0, 0, 0, uuid), 
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 35, 45, 0, 0, 0, 0, 0, -75, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 35, 45, 0, 0, 0, 0, 0, -100, 0, 0, 0, uuid)));
	
	public static final RegistryObject<Reforge> FAST = REFORGES.register("fast", () -> new Reforge(new FBItemType[] {FBItemType.SWORD}, false, 
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 60, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 70, 0, 0, 0, 0, 0, 0, 0, 0, uuid)));
	
	public static final RegistryObject<Reforge> FAIR = REFORGES.register("fair", () -> new Reforge(new FBItemType[] {FBItemType.SWORD}, false,
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 2, 2, 2, 2, 0, 0, 0, 0, 2, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 3, 3, 3, 3, 0, 0, 0, 0, 3, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 4, 4, 4, 4, 0, 0, 0, 0, 4, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 7, 7, 7, 7, 0, 0, 0, 0, 7, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 10, 10, 10, 10, 0, 0, 0, 0, 10, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 14, 14, 14, 14, 0, 0, 0, 0, 14, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 20, 20, 20, 20, 0, 0, 0, 0, 20, 0, 0, 0, uuid)));
	
	public static final RegistryObject<Reforge> EPIC = REFORGES.register("epic", () -> new Reforge(new FBItemType[] {FBItemType.SWORD}, false,
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 15, 0, 10, 1, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 20, 0, 15, 2, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 25, 0, 20, 4, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 32, 0, 27, 7, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 40, 0, 35, 10, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 49, 0, 45, 14, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 59, 0, 57, 19, 0, 0, 0, 0, 0, 0, 0, 0, uuid)));
	
	public static final RegistryObject<Reforge> SHARP = REFORGES.register("sharp", () -> new Reforge(new FBItemType[] {FBItemType.SWORD}, false,
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 10, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 12, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 14, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 17, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 20, 75, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 25, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 31, 130, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid)));
	
	public static final RegistryObject<Reforge> HEROIC = REFORGES.register("heroic", () -> new Reforge(new FBItemType[] {FBItemType.SWORD}, false,
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 15, 0, 0, 1, 0, 0, 0, 0, 40, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 20, 0, 0, 2, 0, 0, 0, 0, 50, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 25, 0, 0, 2, 0, 0, 0, 0, 65, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 32, 0, 0, 3, 0, 0, 0, 0, 80, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 40, 0, 0, 5, 0, 0, 0, 0, 100, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 49, 0, 0, 7, 0, 0, 0, 0, 125, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 59, 0, 0, 10, 0, 0, 0, 0, 160, 0, 0, 0, uuid)));
	
	public static final RegistryObject<Reforge> SPICY = REFORGES.register("spicy", () -> new Reforge(new FBItemType[] {FBItemType.SWORD}, false,
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 2, 1, 25, 1, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 3, 1, 35, 2, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 4, 1, 45, 4, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 7, 1, 60, 7, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 10, 1, 80, 10, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 14, 1, 105, 14, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 20, 1, 130, 19, 0, 0, 0, 0, 0, 0, 0, 0, uuid)));
	
	public static final RegistryObject<Reforge> LEGENDARY = REFORGES.register("legendary", () -> new Reforge(new FBItemType[] {FBItemType.SWORD}, false,
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 3, 5, 5, 2, 0, 0, 0, 0, 5, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 7, 7, 10, 3, 0, 0, 0, 0, 8, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 12, 9, 15, 5, 0, 0, 0, 0, 12, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 18, 12, 22, 7, 0, 0, 0, 0, 18, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 25, 15, 28, 10, 0, 0, 0, 0, 25, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 33, 20, 35, 14, 0, 0, 0, 0, 33, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 40, 25, 45, 20, 0, 0, 0, 0, 40, 0, 0, 0, uuid)));
	
	public static final RegistryObject<Reforge> CLEAN = REFORGES.register("clean", () -> new Reforge(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, false,
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 2, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 4, 0, 0, 0, 7, 7, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 6, 0, 0, 0, 10, 10, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 8, 0, 0, 0, 15, 15, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 10, 0, 0, 0, 20, 20, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 12, 0, 0, 0, 27, 27, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 14, 0, 0, 0, 35, 35, 0, 0, 0, 0, 0, uuid)));
	
	public static final RegistryObject<Reforge> FIERCE = REFORGES.register("fierce", () -> new Reforge(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, false,
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 2, 2, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 4, 3, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 6, 4, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 8, 5, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 10, 6, 18, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 12, 7, 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 14, 8, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, uuid)));
	
	public static final RegistryObject<Reforge> HEAVY = REFORGES.register("heavy", () -> new Reforge(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, false,
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, -1, 0, 0, 0, 25, -1, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, -2, 0, 0, 0, 35, -1, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, -2, 0, 0, 0, 50, -1, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, -3, 0, 0, 0, 65, -1, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, -5, 0, 0, 0, 80, -1, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, -7, 0, 0, 0, 100, -1, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, -10, 0, 0, 0, 130, -1, 0, 0, 0, 0, uuid)));
	
	public static final RegistryObject<Reforge> LIGHT = REFORGES.register("light", () -> new Reforge(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, false,
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 1, 1, 1, 0, 5, 1, 1, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 1, 2, 2, 0, 7, 2, 2, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 2, 3, 3, 0, 10, 3, 3, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 2, 4, 4, 0, 15, 4, 4, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 3, 5, 5, 0, 20, 5, 5, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 3, 6, 6, 0, 27, 6, 6, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 4, 7, 7, 0, 36, 7, 7, 0, 0, 0, 0, uuid)));
	
	public static final RegistryObject<Reforge> MYTHIC = REFORGES.register("mythic", () -> new Reforge(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, false,
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 2, 1, 0, 0, 0, 2, 2, 2, 20, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 4, 2, 0, 0, 0, 4, 4, 2, 25, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 6, 3, 0, 0, 0, 6, 6, 2, 30, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 8, 4, 0, 0, 0, 8, 8, 2, 40, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 10, 5, 0, 0, 0, 10, 10, 2, 50, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 12, 6, 0, 0, 0, 12, 12, 2, 65, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 14, 7, 0, 0, 0, 14, 14, 2, 85, 0, 0, 0, uuid)));
	
	public static final RegistryObject<Reforge> PURE = REFORGES.register("pure", () -> new Reforge(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, false,
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 2, 2, 2, 1, 0, 2, 2, 1, 2, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 3, 4, 3, 1, 0, 3, 3, 1, 3, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 4, 6, 4, 2, 0, 4, 4, 1, 4, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 6, 8, 6, 3, 0, 6, 6, 1, 6, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 8, 10, 8, 4, 0, 8, 8, 1, 8, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 10, 12, 10, 5, 0, 10, 10, 1, 10, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 13, 14, 13, 7, 0, 13, 13, 1, 13, 0, 0, 0, uuid)));
	
	public static final RegistryObject<Reforge> SMART = REFORGES.register("smart", () -> new Reforge(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, false,
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 4, 4, 0, 20, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 6, 6, 0, 40, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 9, 9, 0, 60, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 12, 12, 0, 80, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 15, 15, 0, 100, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 20, 20, 0, 120, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 27, 27, 0, 150, 0, 0, 0, uuid)));
	
	public static final RegistryObject<Reforge> TITANIC = REFORGES.register("titanic", () -> new Reforge(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, false,
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 10, 10, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 15, 15, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 20, 20, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 25, 25, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 35, 35, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 50, 50, 0, 0, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 70, 70, 0, 0, 0, 0, 0, uuid)));
	
	public static final RegistryObject<Reforge> WISE = REFORGES.register("wise", () -> new Reforge(new FBItemType[] {FBItemType.HELMET, FBItemType.CHESTPLATE, FBItemType.LEGGINGS, FBItemType.BOOTS}, false,
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 6, 0, 1, 25, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 8, 0, 1, 50, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 10, 0, 1, 75, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 12, 0, 2, 100, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 15, 0, 2, 125, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 20, 0, 2, 150, 0, 0, 0, uuid),
			(uuid) -> ModifierHelper.modifierMapFromDoubles(0, 0, 0, 0, 0, 0, 27, 0, 3, 200, 0, 0, 0, uuid)));

}
